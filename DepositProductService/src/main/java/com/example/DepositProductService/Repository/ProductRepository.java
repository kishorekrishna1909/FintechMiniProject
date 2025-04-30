package com.example.DepositProductService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DepositProductService.Model.DepositProduct;

@Repository
public interface ProductRepository extends JpaRepository<DepositProduct, String> {
    
}
