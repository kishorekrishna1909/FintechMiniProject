package com.example.CustomerProject.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CustomerProject.Model.Customer;
import com.example.CustomerProject.Model.PersonCustomer;
import com.example.CustomerProject.Repository.CustomerRepo;
import com.example.CustomerProject.UtilityClass.Utility;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    public Customer SaveCustomer(Customer c) {
     for (PersonCustomer person : c.getPersons()) {
           // System.out.println("db======="+person);
             person.setPersonId(Utility.generateId()); 
     }
    c.setCustomerId(Utility.generateId());
    c.setCreatedTime(LocalDateTime.now());
    return customerRepo.save(c);
    }

    public List<Customer> GetCustomer() {
        return customerRepo.findAll();
    }

    public Customer getCustomerById(String id) {
        return customerRepo.findById(id).orElseThrow();
    }

    public Customer updateCustomers(String id,Customer c) {
        Customer existingCustomer=getCustomerById(id);
        existingCustomer.setGender(c.getGender());
        return customerRepo.save(existingCustomer);
    }

    public List<Customer> getCustomerByFilter(String name1,String name2) {
       List<Customer> res= customerRepo.getCustomerByFilter(name1,name2);
       if(res.isEmpty()){
        throw new NoSuchElementException("Customer not found");
       }
       return res;
    }  

    }

