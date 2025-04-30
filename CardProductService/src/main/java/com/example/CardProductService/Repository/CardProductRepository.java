package com.example.CardProductService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CardProductService.Model.CardProduct;

@Repository
public interface CardProductRepository extends JpaRepository<CardProduct, String> {
    
}
