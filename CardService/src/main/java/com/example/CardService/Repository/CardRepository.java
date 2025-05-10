package com.example.CardService.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.example.CardService.Model.Card;

@Repository
public interface  CardRepository  extends JpaRepository<Card, String>{

@Query(value = "SELECT c.* FROM cards c " +
               "JOIN deposit_accounts da ON c.account_id = da.account_id " +
                "WHERE (:customerId IS NULL OR da.customer_id = :customerId) " +
               "AND (:status IS NULL OR c.status = :status) " +
               "AND (:accountId IS NULL OR c.account_id = :accountId) " +
               "AND (:cardProductId IS NULL OR c.card_product_id = :cardProductId) " +
               "AND (:currency IS NULL OR c.currency = :currency) " +
               "AND (:cardType IS NULL OR c.card_type = :cardType) " +
               "AND (:cardNetwork IS NULL OR c.card_network = :cardNetwork)",
               nativeQuery = true)



    public List<Card> listCards(
    @Param("customerId") String customerId,
    @Param("status") String status,
    @Param("accountId") String accountId,
    @Param("cardProductId") String cardProductId,
    @Param("currency") String currency,
    @Param("cardType") String cardType,
    @Param("cardNetwork") String cardNetwork
    );

}
