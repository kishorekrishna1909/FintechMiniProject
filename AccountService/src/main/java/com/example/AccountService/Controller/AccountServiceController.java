package com.example.AccountService.Controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.AccountService.Model.Account;
import com.example.AccountService.Model.AccountResponseWithoutBalance;
import com.example.AccountService.Service.AccountServiceService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class AccountServiceController {
    
    @Autowired
    AccountServiceService accountServiceService;
    
    @PostMapping("/createAccount")
    public AccountResponseWithoutBalance createAccount(@Valid @RequestBody Account acc) {
       return accountServiceService.createAccount(acc);
    }

    @GetMapping("/getAccount/{id}")
    public Account getMethodName(@PathVariable String id) {
      return accountServiceService.getAccountsById(id);
   }
    
   @GetMapping("/getAccounts")
   public Map<String,Object> getAllAccounts() {  
       return accountServiceService.getAllAccounts();
   }
   
   @PutMapping("updateAccounts/{id}")
   public Account updateAccounts(@PathVariable String id, @RequestBody Account acc) {
      return accountServiceService.updateAccounts(id,acc);
   }

   @PostMapping("/blockAccount/{id}")
   public Account blockAccount(@PathVariable String id) {
       return accountServiceService.blockAccount(id);
   }
   
   @PostMapping("/unblockAccount/{id}")
   public Account unblockAccount(@PathVariable String id) {
       return accountServiceService.unblockAccount(id);
   }
   
   @PostMapping("/closeAccount/{id}")
   public Account closeAccount(@PathVariable String id){
    return accountServiceService.closeAccount(id);
   }
}
