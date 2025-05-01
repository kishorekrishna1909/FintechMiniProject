package com.example.BookTransaction.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BookTransaction.Model.BookTransaction;

@Repository
public interface BookTransactionRepo extends JpaRepository<BookTransaction,String> {
    @Query(value = "SELECT * FROM customer_info WHERE first_name = :firstname AND last_name = :lastname", nativeQuery = true)
    List<Customer> getCustomerByFilter(@Param("firstname")String name1,@Param("lastname") String name2);
}
