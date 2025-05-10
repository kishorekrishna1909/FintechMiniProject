package com.example.CardService.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.CardService.Enums.CardEnums.Status;

import jakarta.persistence.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cards")
public class Card {
    @Id
    private String cardId;
    private String cardProductId;
    private String cardHolderName;
    private String cardNumber;
    private String accountId;
    private String currency;   
    private String cardType; 
    private String cardNetwork;    
    private String description;
    private String cvv;
  //  @Size(min = 4, max = 4, message = "PIN must be exactly 4 digits")
    private String pin;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime issueDate;
    private LocalDate expiryDate;
    private LocalDateTime deletedAt;
}

