package com.example.BookTransaction.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookTransaction.ClientCall.AccountClient;
import com.example.BookTransaction.ClientCall.LedgerClient;
import com.example.BookTransaction.Model.BookTransaction;
import com.example.BookTransaction.Repository.BookTransactionRepo;
import com.example.SharedModule.Model.Account;
import com.example.SharedModule.Model.Ledger;

import java.lang.*;
import java.time.LocalDateTime;

import com.example.BookTransaction.Enums.Enums;
import com.example.BookTransaction.UtilityClass.Utility;

import jakarta.transaction.Transactional;

@Service
public class BookTransactionService {

    @Autowired
    BookTransactionRepo bookTransactionRepo;
    
    @Autowired
    LedgerClient ledgerClient;

    @Autowired
    AccountClient accountClient;

  //  @Transactional
    public BookTransaction createBookTransaction(BookTransaction bookTrans) {
    
        bookTrans.setTransactionId(Utility.generateId());
        bookTrans.setStatus(Enums.Status.PENDING);
        bookTrans.setTransactionTime(LocalDateTime.now());
        
        //data's save before transaction done bcoz every transaction details are imp
        bookTransactionRepo.save(bookTrans);

        // Validate Accounts
        Account originator = accountClient.getAccount(bookTrans.getOriginatorAccountId());
        Account beneficiary = accountClient.getAccount(bookTrans.getBeneficiaryAccountId());
    
        if ("CLOSED".equals(originator.getStatus()) || "BLOCKED".equals(originator.getStatus())
            || "CLOSED".equals(beneficiary.getStatus()) || "BLOCKED".equals(beneficiary.getStatus())) {
            throw new IllegalStateException("ACCOUNT_CLOSED_OR_BLOCKED_CONTACT_BANK");
        }
    
        // Get Ledger Info
        Ledger originatorLedger = ledgerClient.getLedgerAccount(originator.getAccountId());
        Ledger beneficiaryLedger = ledgerClient.getLedgerAccount(beneficiary.getAccountId());
    
        // Compute balances after transaction
        float originatorNewBalance = "CREDIT_NORMAL_ACCOUNT".equals(originatorLedger.getAccountType())
            ? originatorLedger.getBalance() - bookTrans.getAmount()
            : originatorLedger.getBalance() + bookTrans.getAmount();
    
        float beneficiaryNewBalance = "CREDIT_NORMAL_ACCOUNT".equals(beneficiaryLedger.getAccountType())
            ? beneficiaryLedger.getBalance() + bookTrans.getAmount()
            : beneficiaryLedger.getBalance() - bookTrans.getAmount();
    
        if (originatorNewBalance < 0 || beneficiaryNewBalance < 0) {
            bookTrans.setStatus(Enums.Status.FAILED);
            bookTransactionRepo.save(bookTrans);
            throw new IllegalStateException("INSUFFICIENT_FUNDS");
        }
 
        originatorLedger.setBalance(originatorNewBalance);
        beneficiaryLedger.setBalance(beneficiaryNewBalance);


        ledgerClient.updateLedgerAccount(originatorLedger);
        ledgerClient.updateLedgerAccount(beneficiaryLedger);
        
        bookTrans.setStatus(Enums.Status.SUCCESS);

        return bookTransactionRepo.save(bookTrans);
    }

    public BookTransaction getTransactionById(String id) {
       return bookTransactionRepo.findById(id).orElseThrow();
    }
    
}

