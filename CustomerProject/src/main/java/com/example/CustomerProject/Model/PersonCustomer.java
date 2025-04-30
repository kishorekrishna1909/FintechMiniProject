package com.example.CustomerProject.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Person_Customer")
@NoArgsConstructor
@AllArgsConstructor
public class PersonCustomer {
    @Id
    private String personId;
    @NotBlank(message = "fisrt name field is mandotory")
    private String firstName;
    @NotBlank(message = "last name field is mandotory")
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String nationality;

    public void setPersonId(String generateId) {
        this.personId=generateId; 
        System.out.println("personid"+personId);
    }
}
