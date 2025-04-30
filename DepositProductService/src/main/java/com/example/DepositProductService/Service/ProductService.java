package com.example.DepositProductService.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.DepositProductService.Model.Utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.example.CustomerProject.Model.Customer;
import com.example.DepositProductService.Model.DepositProduct;
import com.example.DepositProductService.Model.Enums;
import com.example.DepositProductService.Repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public DepositProduct CreateDepositProduct(DepositProduct dp) {
        dp.setDepositProductId(Utility.generateId());
        dp.setCreatedAt(LocalDateTime.now());
        return productRepository.save(dp);
    }

    public DepositProduct getDepositProduct(String id) {
       DepositProduct dp= productRepository.findById(id).orElseThrow();
       if(dp.getStatus().equals(Enums.Status.ACTIVE)){
        return dp;
       }
       throw new IllegalStateException("Deposit product is not active");
    }

    public DepositProduct deactivateDepositProduct(String id) {
        DepositProduct dp = productRepository.findById(id).orElseThrow();
        if (dp.getStatus().equals(Enums.Status.INACTIVE)) {
           throw new IllegalStateException("Product is already deactivated state");
        }
        dp.setStatus(Enums.Status.INACTIVE);
        dp.setDeletedAt(LocalDateTime.now());
        return productRepository.save(dp);
    }

    public Map<String, Object> getDepositProduct() {
        List<DepositProduct> dp = productRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        List<DepositProduct> activeCardProducts = new ArrayList<>();
        int count = 0;
    
        for (DepositProduct depositProduct : dp) {
            if (depositProduct.getStatus().equals(Enums.Status.ACTIVE)) {
                activeCardProducts.add(depositProduct);
                count++;
            }
        }
    
        response.put("TotalCount", count);
        response.put("CardProducts", activeCardProducts);
    
        return response;
    }

    
}
