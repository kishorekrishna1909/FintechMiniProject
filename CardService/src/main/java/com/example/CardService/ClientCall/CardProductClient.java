package com.example.CardService.ClientCall;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.SharedModule.Model.CardProduct;

@FeignClient("CARDPRODUCTSERVICE")
public interface CardProductClient {

    @GetMapping("/getCardProduct/{id}")
    public CardProduct getCardProduct(@PathVariable("id") String id);
}
