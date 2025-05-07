package com.example.CustomerProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CustomerProject.Model.Customer_Address;

@Repository
public interface CustomerAddressRepo extends JpaRepository<Customer_Address,Long> {
    
}