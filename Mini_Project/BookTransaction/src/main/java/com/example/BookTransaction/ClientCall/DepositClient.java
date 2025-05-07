package com.example.BookTransaction.ClientCall;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.SharedModule.Model.DepositProduct;

@FeignClient("DEPOSITPRODUCTSERVICE")
public interface DepositClient {
   
   @GetMapping("/getDepositProduct/{id}")
   public DepositProduct getDepositProduct(@PathVariable("id") String id);
}
