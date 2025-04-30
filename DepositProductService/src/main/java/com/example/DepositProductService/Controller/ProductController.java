package com.example.DepositProductService.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.DepositProductService.Model.DepositProduct;

import com.example.DepositProductService.Service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

   @PostMapping("/createDepositProduct")
    public DepositProduct CreateDepositProduct(@Valid @RequestBody DepositProduct dp) {
        return productService.CreateDepositProduct(dp);
   }

   @GetMapping("/getDepositProduct/{id}")
   public DepositProduct getDepositProduct(@PathVariable("id") String id) {
      return productService.getDepositProduct(id);
   }
   
   @GetMapping("/getDepositProduct")
   public Map<String,Object> getDepositProduct() {
      return productService.getDepositProduct();
   }

   @PutMapping("/deactivateDepositProduct/{id}")
   public DepositProduct deactivateProduct(@PathVariable("id") String id) {
    System.out.println(id);
    return productService.deactivateDepositProduct(id);
}
    
}

