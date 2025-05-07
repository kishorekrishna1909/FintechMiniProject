package com.example.AccountService.Model;

import java.time.LocalDateTime;

import com.example.AccountService.Enums.Enums.AccountType;
import com.example.AccountService.Enums.Enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Deposit_Accounts")
@NoArgsConstructor
@AllArgsConstructor

public class Account {
    @Id
    private String accountId;
    private String personId;
    @NotBlank(message="CustomerId is mandotory field")
    private String customerId;
    @NotBlank(message="CustomerId is mandotory field")
    private String depositProductId;
    private String accountNumber;  
    private String currency;
    private AccountType accountType;
    private float balance;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
