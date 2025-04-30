package com.example.AccountService.Repository;
import com.example.AccountService.Model.Account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountServiceRepo extends JpaRepository<Account, String> {
    List<Account> findByCustomerIdAndDepositProductId(String customerId,String depositProductId); 
}
