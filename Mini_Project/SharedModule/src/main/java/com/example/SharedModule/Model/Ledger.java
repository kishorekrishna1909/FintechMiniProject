package  com.example.SharedModule.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="LedgerAccounts")
public class Ledger {
    private String accountId;
    @Id
    private String LedgerAccountId;
    private String currency;
    private String accountType;
    private float balance;
    private LocalDateTime createdAt;

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
