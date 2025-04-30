package com.example.AccountService.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.SharedModule.Model.Customer;

@FeignClient("CUSTOMERPROJECT")
public interface CustomerClient {

    @GetMapping("/getcustomers/{id}")
    public Customer getCustomerById(@PathVariable("id") String id);
}
