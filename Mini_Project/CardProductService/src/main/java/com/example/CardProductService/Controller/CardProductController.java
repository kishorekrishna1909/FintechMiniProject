package com.example.CardProductService.Controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.CardProductService.Model.CardProduct;

import com.example.CardProductService.Service.CardProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class CardProductController {

    @Autowired
    private CardProductService cardProductService;

   @PostMapping("/createCardProduct")
    public CardProduct CreateCardProduct(@Valid @RequestBody CardProduct dp) {
        return cardProductService.CreateCardProduct(dp);
   }

   @GetMapping("/getCardProduct/{id}")
   public CardProduct getCardProduct(@PathVariable("id") String id) {
      return cardProductService.getCardProduct(id);
   }
   
   @GetMapping("/getCardProduct")
   public Map<String,Object> getCardProduct() {
      return cardProductService.getCardProduct();
   }

   @PutMapping("/deactivateCardProduct/{id}")
   public CardProduct deactivateProduct(@PathVariable("id") String id) {
    return cardProductService.deactivateCardProduct(id);
}
    
}

