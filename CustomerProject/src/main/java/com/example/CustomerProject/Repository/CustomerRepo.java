package com.example.CustomerProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CustomerProject.Model.Customer;


@Repository
public interface CustomerRepo extends JpaRepository<Customer,String> {
    @Query(value = "SELECT * FROM customer_info WHERE first_name = :firstname AND last_name = :lastname", nativeQuery = true)
    List<Customer> getCustomerByFilter(@Param("firstname")String name1,@Param("lastname") String name2);
    //Customer findByFirstName(String firstName);;
}
