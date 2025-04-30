package com.example.SharedModule.Model;

import java.time.LocalDateTime;

import com.example.SharedModule.Model.Enums.DepositProductType;
import com.example.SharedModule.Model.Enums.Currency;
import com.example.SharedModule.Model.Enums.ProductCode;
import com.example.SharedModule.Model.Enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class DepositProduct {
   // @Id
    private String depositProductId;
    @Enumerated(EnumType.STRING)
    private ProductCode productCode;
    // @NotBlank(message="name field is mandatory")
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private DepositProductType type;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private Float minBalance;
    private int noOfAccountPerCustomer;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
