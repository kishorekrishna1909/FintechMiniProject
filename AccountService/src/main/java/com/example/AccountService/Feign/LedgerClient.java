package com.example.AccountService.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.AccountService.Model.Account;
import com.example.SharedModule.Model.Ledger;

@FeignClient("LEDGERSERVICE")
public interface LedgerClient {

    @PostMapping("/createLedgerAccount")
   public void createLedgerAccount(@RequestBody Account acc);
   
   @GetMapping("/getLedgerAccount/{id}")
   public Ledger getLedgerAccount(@PathVariable("id") String id);
}
