package com.example.BookTransaction.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BookTransaction.Model.BookTransaction;

@Repository
public interface BookTransactionRepo extends JpaRepository<BookTransaction,String> {
    
}
