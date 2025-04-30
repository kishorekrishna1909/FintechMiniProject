package com.example.AccountService.Model;

import com.example.AccountService.Enums.Enums.AccountType;
import com.example.AccountService.Enums.Enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountResponseWithoutBalance {
    private String accountId;
    private String personId;
    private String customerId;
    private String depositProductId;
    private String accountNumber;
    private String currency;
    private AccountType accountType;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
