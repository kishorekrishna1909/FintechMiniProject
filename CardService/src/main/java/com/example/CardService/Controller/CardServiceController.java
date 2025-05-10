package com.example.CardService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.CardService.Model.Card;
import com.example.CardService.Model.PinUpdateRequest;
import com.example.CardService.Service.CardService;

import jakarta.validation.Valid;

@RestController
public class CardServiceController {
     
    @Autowired
    CardService cardService;

    @PostMapping("/createCard")
    public Card createCard(@RequestBody Card card){
       return cardService.createCard(card);
    }

    @GetMapping("/getCard/{id}")
    public Card getCard(@PathVariable("id") String id){
        return cardService.getCard(id);
    }

    @PutMapping("/setPin/{id}")
    public Card setPin(@PathVariable("id") String id,@RequestBody @Valid PinUpdateRequest pinUpdateRequest){
        return cardService.setPin(id,pinUpdateRequest);
    }

    @PostMapping("/blockCard/{id}")
    public Card blockCard(@PathVariable("id") String id){
       return cardService.blockCard(id);
    }

    @PostMapping("/unBlockCard/{id}")
    public Card unBlockCard(@PathVariable("id") String id){
        return cardService.unBlockCard(id);
    }

    @PostMapping("/closeCard/{id}")
    public Card closeCard(@PathVariable("id") String id){
        return cardService.closeCard(id);
    }

    @GetMapping("/listCards")
    public List<Card> listCards(
        @RequestParam(required = false) String customerId,
        @RequestParam(required = false) String status,
        @RequestParam(required = false) String accountId,
        @RequestParam(required = false) String cardProductId,
        @RequestParam(defaultValue = "ascending") String order,
        @RequestParam(required = false) String currency,
        @RequestParam(required = false) String cardType,
        @RequestParam(required = false) String cardNetwork
    ){
        return cardService.listCards(customerId,status,accountId,cardProductId,order,currency,cardType,cardNetwork);
    }

    
}
