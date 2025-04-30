package com.example.CardProductService.Model;

import com.example.CardProductService.Model.CardProductEnums.CardProductType;
import com.example.CardProductService.Model.CardProductEnums.NetworkType;

import java.time.LocalDateTime;

import com.example.CardProductService.Model.CardProductEnums.CardProductCode;
import com.example.CardProductService.Model.CardProductEnums.Status;
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

public class CardProduct {
    @Id
    private String cardProductId;
    @Enumerated(EnumType.STRING)
    private CardProductCode cardProductCode;
    @NotBlank(message="name field is mandatory")
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private CardProductType type;
    @Enumerated(EnumType.STRING)
    private NetworkType networkType;
    private int validityYears;        
    private int panLength;
    private int pinLength;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
