package com.example.BookTransaction.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import com.example.BookTransaction.Model.BookTransaction;

@Repository
public interface BookTransactionRepo extends JpaRepository<BookTransaction,String> {
    @Query(value = "SELECT bt.* FROM book_transactions bt " +
    "JOIN deposit_accounts da ON (da.account_id = bt.originator_account_id OR da.account_id = bt.beneficiary_account_id) " +
    "WHERE (:customerId IS NULL OR da.customer_id = :customerId) AND " +
    "(:status IS NULL OR bt.status = :status) AND " +
    "(:originatorAccountId IS NULL OR bt.originator_account_id = :originatorAccountId) AND " +
    "(:beneficiaryAccountId IS NULL OR bt.beneficiary_account_id = :beneficiaryAccountId)", 
    nativeQuery = true)

   public List<BookTransaction> listTransaction(
    @Param("customerId") String customerId,
    @Param("status") String status,
    @Param("originatorAccountId") String originatorAccountId,
    @Param("beneficiaryAccountId") String beneficiaryAccountId
    );
}
