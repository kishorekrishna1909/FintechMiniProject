  package com.example.SharedModule.Model;

  import java.time.LocalDateTime;
  import java.util.*;

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
  // @Id
    private String accountId;
  // @NotBlank(message="CustomerId is mandotory field")
    private String customerId;
  // @NotBlank(message="depositProductId is mandotory field")
    private String depositProductId;
    private Set<String> personId;
    private String accountNumber;  
    private String currency;
    private AccountType accountType;
    private float balance;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
  }