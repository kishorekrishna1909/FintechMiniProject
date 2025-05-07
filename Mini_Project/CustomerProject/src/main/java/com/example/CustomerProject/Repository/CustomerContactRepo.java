package com.example.CustomerProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CustomerProject.Model.Customer_Contact_Detail;

@Repository
public interface CustomerContactRepo extends JpaRepository<Customer_Contact_Detail,Long> {
    
}