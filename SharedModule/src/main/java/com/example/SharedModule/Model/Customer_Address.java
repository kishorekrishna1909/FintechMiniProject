package com.example.SharedModule.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
// @Table(name="customer_address")


public class Customer_Address {
    private Long id;
    private String line1;
    private String line2;
    private String line3;
    private String city;
    private String state;
    private String pincode;
    private String country;
}



