package com.example.DepositProductService.Model;

import java.time.LocalDateTime;

import com.example.DepositProductService.Model.Enums.Currency;
import com.example.DepositProductService.Model.Enums.DepositProductType;
import com.example.DepositProductService.Model.Enums.ProductCode;
import com.example.DepositProductService.Model.Enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class DepositProduct {
    @Id
    private String depositProductId;
    @Enumerated(EnumType.STRING)
    private ProductCode productCode;
    @NotBlank(message="name field is mandatory")
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
