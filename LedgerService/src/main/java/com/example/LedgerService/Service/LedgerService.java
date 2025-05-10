package com.example.LedgerService.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LedgerService.Model.Ledger;
import com.example.LedgerService.Repository.LedgerRepo;
import com.example.SharedModule.Model.Account;
import com.example.LedgerService.UtilityClass.Utility;

@Service
public class LedgerService {

    @Autowired
    LedgerRepo ledgerRepo;

    Ledger ledger=null;

    public void createLedgerAccount(Account acc){
        System.out.println("service"+acc);
        Ledger ledger=new Ledger();
        ledger.setAccountId(acc.getAccountId());
        ledger.setLedgerAccountId(Utility.generateId());
        ledger.setCurrency(acc.getCurrency());
        ledger.setAccountType(acc.getAccountType().toString());
        ledger.setBalance(acc.getBalance());
        ledger.setCreatedAt(LocalDateTime.now());
        ledgerRepo.save(ledger);
    }

    public Ledger getLedgerAccount(String id) {
       return ledgerRepo.findByAccountId(id);
    }

    public Ledger updateBalance(Ledger ledger) {
        System.out.println("service=="+ledger);
        Ledger existingLedger=ledgerRepo.findById(ledger.getLedgerAccountId()).orElseThrow();
        existingLedger.setBalance(ledger.getBalance());
        return ledgerRepo.save(existingLedger); 
    }
}
