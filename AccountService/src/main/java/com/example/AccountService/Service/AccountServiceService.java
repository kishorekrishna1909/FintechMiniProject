package com.example.AccountService.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AccountService.Enums.Enums;
import com.example.AccountService.Feign.CustomerClient;
import com.example.AccountService.Feign.DepositServiceClient;
import com.example.AccountService.Feign.LedgerClient;
import com.example.AccountService.Model.Account;  
import com.example.AccountService.Model.AccountResponseWithoutBalance;
import com.example.AccountService.Repository.AccountServiceRepo;
import com.example.AccountService.UtilityClass.Utility;
import com.example.SharedModule.Model.Customer;
import com.example.SharedModule.Model.PersonCustomer;
import com.example.SharedModule.Model.DepositProduct;
import com.example.SharedModule.Model.Ledger;

@Service
public class AccountServiceService {

     @Autowired 
     AccountServiceRepo accountServiceRepo;
     
     @Autowired
     CustomerClient customerClient;

     @Autowired
     DepositServiceClient depositServiceClient;

     @Autowired
     LedgerClient ledgerClient;

     public Account createAccount(Account acc) {

        Customer customer = customerClient.getCustomerById(acc.getCustomerId());
        DepositProduct depositProduct = depositServiceClient.getDepositProduct(acc.getDepositProductId());

        boolean matchFound = false;
        List<PersonCustomer> customerPersons = customer.getPersons();
                for (PersonCustomer custPerson : customerPersons) {
                    if (acc.getPersonId().equals(custPerson.getPersonId())) {
                        matchFound = true;
                        break;
                    }
                }
                
    
        if (!matchFound) {
            throw new IllegalArgumentException("No matching personId found between Account and Customer.");
        }
  
        List<Account> listOfAccounts=getAccountsByCustomerAndDeposit(acc.getCustomerId(),depositProduct.getDepositProductId());
        if(listOfAccounts.size()<depositProduct.getNoOfAccountPerCustomer()){
            acc.setAccountId(Utility.generateId());
    
            if (acc.getAccountNumber() == null || acc.getAccountNumber().trim().isEmpty()) {
                acc.setAccountNumber(Utility.generateId());
            }
        
            if(acc.getBalance()==0.0){
            acc.setBalance(depositProduct.getMinBalance());
            }
            
            acc.setCurrency(depositProduct.getCurrency().toString());

            acc.setCreatedAt(LocalDateTime.now());
    
            //create ledger account for all deposit account
            ledgerClient.createLedgerAccount(acc);

            accountServiceRepo.save(acc);
    

        }else{
            throw new IllegalArgumentException("Account per customer limit reached");
        }

        return acc;

    }
    
    public List<Account> getAccountsByCustomerAndDeposit(String customerId,String depositProductId){
        return accountServiceRepo.findByCustomerIdAndDepositProductId(customerId,depositProductId);
    }

    public Account getAccountsById(String id) {
      Ledger ledger=ledgerClient.getLedgerAccount(id);
      Account acc= accountServiceRepo.findById(id).orElseThrow();
      acc.setBalance(ledger.getBalance());
      return acc;
    }

    public Map<String,Object> getAllAccounts() {
        List<Account> acc = accountServiceRepo.findAll();
        List<Account> accs = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        int count = 0;
        
        for (Account res : acc) {
            if (!(res.getStatus().equals(Enums.Status.CLOSED))) {
                Ledger ledger = ledgerClient.getLedgerAccount(res.getAccountId()); 
                res.setBalance(ledger.getBalance());     
                count++;
                accs.add(res);
            }
        }
        response.put("accounts", accs);
        response.put("totalAccounts", count);
        return response;
    }
    

    public Account updateAccounts(String id, Account acc) {
        Account existingAccount = accountServiceRepo.findById(id).orElseThrow();
        existingAccount.setAccountNumber(acc.getAccountNumber());
        existingAccount.setAccountType(acc.getAccountType());
        return accountServiceRepo.save(existingAccount);
    }

    public Account blockAccount(String id) {
        Account existingAccount = accountServiceRepo.findById(id).orElseThrow();
        if(existingAccount.getStatus().equals(Enums.Status.BLOCKED)||existingAccount.getStatus().equals(Enums.Status.CLOSED)){
            throw new IllegalStateException("Account already blocked or closed");
        }else{
            existingAccount.setStatus(Enums.Status.BLOCKED);
        }
        return accountServiceRepo.save(existingAccount);
    }

    public Account unblockAccount(String id) {
       Account unblockAcc = accountServiceRepo.findById(id).orElseThrow();
       if(unblockAcc.getStatus().equals(Enums.Status.BLOCKED)){
        unblockAcc.setStatus(Enums.Status.ACTIVE);
       }else{
        throw new IllegalStateException("Account in active or closed state");
       }
       return accountServiceRepo.save(unblockAcc); 
    }

    public Account closeAccount(String id) {
        Account acc = accountServiceRepo.findById(id).orElseThrow();
        if(acc.getStatus().equals(Enums.Status.CLOSED)){
            throw new IllegalStateException("Account already closed");
        }
        acc.setStatus(Enums.Status.CLOSED);
        acc.setDeletedAt(LocalDateTime.now());
        return accountServiceRepo.save(acc); 
    }
}
