package com.example.CardProductService.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.CardProductService.Model.Utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CardProductService.Model.CardProduct;
import com.example.CardProductService.Model.CardProductEnums;
import com.example.CardProductService.Repository.CardProductRepository;

@Service
public class CardProductService {

    @Autowired
    private CardProductRepository cardproductRepository;

    public CardProduct CreateCardProduct(CardProduct cp) {
        cp.setCardProductId(Utility.generateId());
        cp.setCreatedAt(LocalDateTime.now());
        return cardproductRepository.save(cp);
    }

    public CardProduct getCardProduct(String id) {
       CardProduct cp= cardproductRepository.findById(id).orElseThrow();
       if(cp.getStatus().equals(CardProductEnums.Status.ACTIVE)){
        return cp;
       }
       throw new IllegalStateException("Card product is not active");
    }

    public CardProduct deactivateCardProduct(String id) {
        CardProduct cp = cardproductRepository.findById(id).orElseThrow();
        if (cp.getStatus().equals(CardProductEnums.Status.INACTIVE)) {
           throw new IllegalStateException("Product is already deactivated state");
        }
        cp.setStatus(CardProductEnums.Status.INACTIVE);
        cp.setDeletedAt(LocalDateTime.now());
        return cardproductRepository.save(cp);
    }

public Map<String, Object> getCardProduct() {
    List<CardProduct> cp = cardproductRepository.findAll();
    Map<String, Object> response = new HashMap<>();
    List<CardProduct> activeCardProducts = new ArrayList<>();
    int count = 0;

    for (CardProduct cardProduct : cp) {
        if (cardProduct.getStatus().equals(CardProductEnums.Status.ACTIVE)) {
            activeCardProducts.add(cardProduct);
            count++;
        }
    }

    response.put("TotalCount", count);
    response.put("CardProducts", activeCardProducts);

    return response;
}
    
}
