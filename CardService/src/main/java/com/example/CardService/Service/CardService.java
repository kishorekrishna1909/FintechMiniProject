package com.example.CardService.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CardService.ClientCall.CardProductClient;
import com.example.CardService.Model.Card;
import com.example.CardService.Enums.CardEnums;
import com.example.CardService.Model.PinUpdateRequest;
import com.example.CardService.UtilityClass.Utility;
import com.example.CardService.UtilityClass.EncryptDecrypt;
import com.example.CardService.Repository.CardRepository;
import com.example.SharedModule.Model.CardProduct;
import com.example.SharedModule.Enums.CardProductEnums.Status;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardProductClient cardProductClient;

    public Card createCard(Card card) {
        CardProduct cp;
        cp = cardProductClient.getCardProduct(card.getCardProductId());

        if(cp.getStatus().equals(Status.INACTIVE)){
            throw new IllegalStateException("PRODUCT_IN_INACTIVE_STATE");
        }
 
        String cardNumber = Utility.luhnGenerator();

        String encryptCardNumber="";
        try {
            encryptCardNumber = EncryptDecrypt.encrypt(cardNumber);
        } catch (Exception ex) {
            System.out.println("encryption error");
        }

        card.setCardId(Utility.generateId());
        card.setCardNumber(encryptCardNumber);
        card.setCardType(cp.getType().toString());
        card.setCardNetwork(cp.getNetworkType().toString());
        card.setCvv(Utility.cvvGenerator());
        card.setStatus(CardEnums.Status.BLOCKED);
        card.setIssueDate(LocalDateTime.now());
        card.setExpiryDate(LocalDate.now().plusYears(cp.getValidityYears()));
        
        return cardRepository.save(card);
    }

    public Card setPin(String id, PinUpdateRequest pinUpdateRequest) {
        Card existingCard = cardRepository.findById(id).orElseThrow();

        if(existingCard==null){
            throw new NoSuchElementException("CARD_NOT_FOUND");
        }else if(existingCard.getStatus().equals(CardEnums.Status.CLOSED)){
            throw new NoSuchElementException("CARD_CLOSED_CONTACT_BANK");
        }

        String encryptPin="";
        try {
            encryptPin = EncryptDecrypt.encrypt(pinUpdateRequest.getPin());
        } catch (Exception ex) {
            System.out.println("encryption error");
        }
        existingCard.setPin(encryptPin);
        existingCard.setStatus(CardEnums.Status.ACTIVE);
        return cardRepository.save(existingCard);
    }
    

    public Card getCard(String id) {
       return cardRepository.findById(id).orElseThrow();
    }

    public Card blockCard(String id) {
       Card existingCard = cardRepository.findById(id).orElseThrow();

       if(existingCard.getStatus().equals(CardEnums.Status.BLOCKED)||existingCard.getStatus().equals(CardEnums.Status.CLOSED)){
          throw new IllegalStateException("CARD_ALREADY_IN_BLOCKED_OR_CLOSED_STATE");
       }
 
       existingCard.setStatus(CardEnums.Status.BLOCKED);

       return cardRepository.save(existingCard);
    }


    public Card closeCard(String id) {
      Card existingCard = cardRepository.findById(id).orElseThrow();

      if(existingCard.getStatus().equals(CardEnums.Status.BLOCKED)||existingCard.getStatus().equals(CardEnums.Status.CLOSED)){
        throw new IllegalStateException("CARD_ALREADY_IN_BLOCKED_OR_CLOSED_STATE");
     }
 
       existingCard.setStatus(CardEnums.Status.CLOSED);
       existingCard.setDeletedAt(LocalDateTime.now());

       return cardRepository.save(existingCard);
    }

    public Card unBlockCard(String id) {
        Card existingCard = cardRepository.findById(id).orElseThrow();

        if(existingCard.getStatus().equals(CardEnums.Status.ACTIVE)){
           throw new IllegalStateException("CARD_ALREADY_IN_ACTIVE_STATE");
        }
  
        existingCard.setStatus(CardEnums.Status.ACTIVE);
         
        return cardRepository.save(existingCard);
    }

    public List<Card> listCards(String customerId,String status, String accountId, String cardProductId, String order, String currency, String cardType, String cardNetwork) {
        List<Card> listCards = cardRepository.listCards(customerId,status,accountId, cardProductId, currency, cardType, cardNetwork);
        
        if (order != null) {
            if (order.equalsIgnoreCase("ascending")) {
                listCards.sort(Comparator.comparing(Card::getIssueDate));
            } else if (order.equalsIgnoreCase("descending")) {
                listCards.sort(Comparator.comparing(Card::getIssueDate).reversed());
            }
        }

        if(listCards.isEmpty()){
            throw new NoSuchElementException();
        }
        return listCards;
    }

    
}

// private String cardId;
// private String cardProductId;
// private String cardHolderName;
// private String cardNumber;
// private String accountId;
// private String currency;   
// private String cardType; 
// private String cardNetwork;    
// private String description;
// private String cvv;
// private String pin;
// private Status status;
// private String issueDate;
// private String expiryDate;