package com.example.SharedModule.Model;

import java.time.LocalDateTime;

import com.example.SharedModule.Enums.Enums.AccountType;
import com.example.SharedModule.Enums.Enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotBlank;
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
    public String accountId;
    public String personId;
   // @NotBlank(message="CustomerId is mandotory field")
    public String customerId;
  //  @NotBlank(message="CustomerId is mandotory field")
    public String depositProductId;
    public String accountNumber;  
    public String currency;
    public AccountType accountType;
    public float balance;
    public Status status;
    public LocalDateTime createdAt;
    public LocalDateTime deletedAt;
}
