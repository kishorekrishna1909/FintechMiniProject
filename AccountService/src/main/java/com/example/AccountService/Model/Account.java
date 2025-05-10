package com.example.AccountService.Model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.*;

import com.example.AccountService.Enums.Enums.AccountType;
import com.example.AccountService.Enums.Enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Convert;
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
    @NotBlank(message="CustomerId is mandotory field")
    private String customerId;
    @NotBlank(message="depositProductId is mandotory field")
    private String depositProductId;
    @Convert(converter = StringListConverter.class)
    private Set<String> personId;
    private String accountNumber;  
    private String currency;
    private AccountType accountType;
    private float balance;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
