package financial_dashboard.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal currentBalance;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    //@JoinColumn cria uma coluna (foreign key) na tabela do banco de dados.

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
    //mapped By identifica o atributo (pelo nome) na entidade relacionada.
    //CascadeType.ALL permite que a entidade relacionada seja persistida
    // no banco de dados junto com essa entidade.


    //Construtor sem o id
    public Account(User user) {
        this.currentBalance = BigDecimal.ZERO;
        this.user = user;
    }

    //Método que adiciona uma transação a lista
    public void addTransactionToList(Transaction transaction) {
        transactions.add(transaction);
    }

    //Método que atualiza o saldo
    public void updateCurrentBalance() {
        this.currentBalance = BigDecimal.ZERO;
        transactions.stream()
                .filter(t -> t.getType().equals(TransactionType.CREDITO))
                .forEach(t -> this.setCurrentBalance(this.currentBalance.add(t.getValue())));

        transactions.stream()
                .filter(t -> t.getType().equals(TransactionType.DEBITO))
                .forEach(t -> this.setCurrentBalance(this.currentBalance.subtract(t.getValue())));


    }
}


