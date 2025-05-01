package com.example.BookTransaction.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookTransaction.ClientCall.AccountClient;
import com.example.BookTransaction.ClientCall.*;
import com.example.BookTransaction.Model.BookTransaction;
import com.example.BookTransaction.Repository.BookTransactionRepo;
import com.example.SharedModule.Model.Account;
import com.example.SharedModule.Model.Ledger;

import java.lang.*;
import java.time.LocalDateTime;

import com.example.BookTransaction.Enums.Enums;
import com.example.BookTransaction.UtilityClass.Utility;
import com.example.SharedModule.Model.DepositProduct;
import com.example.SharedModule.Enums.Enums.Status;
import com.example.SharedModule.Enums.Enums.AccountType;

@Service
public class BookTransactionService {

    @Autowired
    BookTransactionRepo bookTransactionRepo;
    
    @Autowired
    LedgerClient ledgerClient;

    @Autowired
    AccountClient accountClient;

    @Autowired
    DepositClient depositServiceClient;

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
    
        System.out.println("service="+originator+"==="+originator.getStatus());
        System.out.println(Status.BLOCKED.equals(originator.getStatus()));
        if (Status.BLOCKED.equals(originator.getStatus()) || Status.CLOSED.equals(originator.getStatus())
            || Status.CLOSED.equals(beneficiary.getStatus()) || Status.BLOCKED.equals(beneficiary.getStatus())) {
            throw new IllegalStateException("ACCOUNT_CLOSED_OR_BLOCKED_CONTACT_BANK");
        }
    
        // Get Ledger Info
        Ledger originatorLedger = ledgerClient.getLedgerAccount(originator.getAccountId());
        Ledger beneficiaryLedger = ledgerClient.getLedgerAccount(beneficiary.getAccountId());
    
        // Compute balances after transaction
        float originatorNewBalance = AccountType.CREDIT_NORMAL_ACCOUNT.equals(originatorLedger.getAccountType())
            ? originatorLedger.getBalance() - bookTrans.getAmount()
            : originatorLedger.getBalance() + bookTrans.getAmount();
    
        float beneficiaryNewBalance = AccountType.CREDIT_NORMAL_ACCOUNT.equals(beneficiaryLedger.getAccountType())
            ? beneficiaryLedger.getBalance() + bookTrans.getAmount()
            : beneficiaryLedger.getBalance() - bookTrans.getAmount();
    
        DepositProduct depositProduct=depositServiceClient.getDepositProduct(originator.getDepositProductId());

        if (originatorNewBalance < 0 || beneficiaryNewBalance < 0) {
            bookTrans.setStatus(Enums.Status.FAILED);
            bookTransactionRepo.save(bookTrans);
            throw new IllegalStateException("INSUFFICIENT_FUNDS");
        }else if(originatorNewBalance<depositProduct.getMinBalance()){
            throw new IllegalStateException("MINIMUM_BALANCE_NEED_TO_BE_MAINTAINED");
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
    
    public BookTransaction ListTransaciton(@RequestParam("customerId") String customerId,String status,String order,String originatorAccountId,String beneficiaryAccountId){

    }
}

