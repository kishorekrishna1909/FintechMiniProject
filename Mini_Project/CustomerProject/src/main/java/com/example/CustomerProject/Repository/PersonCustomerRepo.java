package com.example.CustomerProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CustomerProject.Model.PersonCustomer;

public interface PersonCustomerRepo extends JpaRepository<PersonCustomer,String>{
    
}
