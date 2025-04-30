package com.example.LedgerService.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.LedgerService.Model.Ledger;

@Repository
public interface LedgerRepo extends JpaRepository<Ledger, String>{
     public Ledger findByAccountId(String id);
}