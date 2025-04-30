package com.example.SharedModule.Model;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
// @Table(name="Customer_Info")
public class Customer {
    // @Id
    private String customerId;
    private List<PersonCustomer> persons;

    // @OneToMany(cascade = CascadeType.ALL)
    // @JoinColumn(name = "customer_id")
    private List<Customer_Address> addresses;

    // @OneToMany(cascade = CascadeType.ALL)
    // @JoinColumn(name = "customer_id")
    private List<Customer_Contact_Detail> contactDetails;
    
    private LocalDateTime createdTime;

    // public String toString() {
    //     return "Customer{" +
    //             "customerId='" + customerId + '\'' +
    //             ", firstName='" + firstName + '\'' +
    //             ", lastName='" + lastName + '\'' +
    //             ", dateOfBirth='" + dateOfBirth + '\'' +
    //             ", gender='" + gender + '\'' +
    //             ", nationality='" + nationality + '\'' +
    //             ", addresses=" + addresses +
    //             ", contactDetails=" + contactDetails +
    //             ", createdTime=" + createdTime +
    //             '}';
    // }

    public List<PersonCustomer> getPersons() {
        return persons;
    }

}
