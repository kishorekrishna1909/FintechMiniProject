package com.example.BookTransaction.Model;

import java.time.LocalDateTime;

import com.example.BookTransaction.Enums.Enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="BookTransactions")

public class BookTransaction {
    @Id
    private String transactionId;
    private String originatorAccountId;
    private String beneficiaryAccountId;
    private float amount;
    private String currency;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String description;
    private LocalDateTime transactionTime;
}
