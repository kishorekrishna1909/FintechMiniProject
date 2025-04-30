package com.example.CustomerProject.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.CustomerProject.Model.Customer;
import com.example.CustomerProject.Service.CustomerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class CustomerController {
     @Autowired
    CustomerService customerService;

    @PostMapping("/customers")
    public Customer SaveCustomer(@Valid @RequestBody Customer c) {
       return customerService.SaveCustomer(c);
    }

    @GetMapping("/getCustomers")
    public  List<Customer> GetCustomer() {
        List<Customer> c= customerService.GetCustomer();
        if(c.isEmpty()){
            throw new NoSuchElementException("Customer not found");
        }
        return c;
    }
    
    @GetMapping("/getcustomers/{id}")
    public Customer getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/updateCustomers/{id}")
    public Customer UpdateCustomer(@PathVariable String id, @RequestBody Customer c) {
       return customerService.updateCustomers(id,c);
    }
        
        @PostMapping("/getCustomers/filter")
        public List<Customer> getMethodName(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {
            System.out.println("controller"+firstName+lastName);
            return customerService.getCustomerByFilter(firstName,lastName);
        }
        
    
}
