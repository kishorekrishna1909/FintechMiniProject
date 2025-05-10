package com.example.LedgerService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.LedgerService.Model.Ledger;
import com.example.LedgerService.Service.LedgerService;
import com.example.SharedModule.Model.Account;

@RestController
public class LedgerController {

    @Autowired
    LedgerService ledgerService;

    @PostMapping("/createLedgerAccount")
   public void createLedgerAccount(@RequestBody Account acc){
      System.out.println("controll"+acc);
       ledgerService.createLedgerAccount(acc);
   }

   @GetMapping("/getLedgerAccount/{id}")
   public Ledger getLedgerAccount(@PathVariable("id") String id){
       return ledgerService.getLedgerAccount(id);
   }

   @PutMapping("/updateLedgerAccount")
   public Ledger updateLedgerAccount(@RequestBody Ledger ledger){
    return ledgerService.updateBalance(ledger);
   }

}
