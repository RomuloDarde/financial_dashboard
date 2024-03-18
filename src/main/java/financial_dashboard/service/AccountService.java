package financial_dashboard.service;

import financial_dashboard.dto.AccountResponseDTO;
import financial_dashboard.dto.AmountByCategoryResponseDTO;
import financial_dashboard.dto.TransactionResponseDTO;
import financial_dashboard.model.Account;
import financial_dashboard.model.Transaction;
import financial_dashboard.model.TransactionCategory;
import financial_dashboard.model.TransactionType;
import financial_dashboard.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {


    //Atributos
    @Autowired
    private AccountRepository repository;


    //Métodos
    //Buscar todas contas
    public List<AccountResponseDTO> getAccounts() {
        return repository.findAll().stream()
                .map(account -> new AccountResponseDTO(
                        account.getId(), account.getCurrentBalance()))
                .collect(Collectors.toList());
    }

    //Buscar contas por id
    public AccountResponseDTO getAccountById(Long id) {
        verifyIfAccountExists(id);
        var account = repository.findById(id).get();
        return new AccountResponseDTO(account.getId(), account.getCurrentBalance());
    }

    //Buscar transações por conta
    public List<TransactionResponseDTO> getTransactionsByAccount(Long id) {
        verifyIfAccountExists(id);
        var account = repository.findById(id).get();
        return convertListTransactionToListDTO(account.getTransactions());
    }

    //Buscar débitos por categoria por conta
    public List<TransactionResponseDTO> getDebitsByCategoryByAccount(Long accountId, String stringCategory) {
        verifyIfAccountExists(accountId);
        var account = repository.findById(accountId).get();
        var category = TransactionCategory.fromString(stringCategory);

        var debitsByCategory = getDebitsByCategoryByAccount(account, category);

        return convertListTransactionToListDTO(debitsByCategory);
    }

    //Ranking dos gastos por categoria por conta
    public List<AmountByCategoryResponseDTO> getExpensesRankByCategoryByAccount(Long accountId) {
        verifyIfAccountExists(accountId);
        var account = repository.findById(accountId).get();
        List<AmountByCategoryResponseDTO> dtoList = new ArrayList<>();

        List<TransactionCategory> categorys = Arrays.asList(TransactionCategory.values());

        categorys.stream().forEach(c -> {
            var transactions = getDebitsByCategoryByAccount(account, c);
            var value = BigDecimal.ZERO;
            for (Transaction transaction:transactions) {
                value = value.add(transaction.getValue());
            }
            if(!value.equals(BigDecimal.ZERO))
            dtoList.add(new AmountByCategoryResponseDTO(c, value));
        });
        return dtoList.stream()
                .sorted(Comparator.comparing(AmountByCategoryResponseDTO::ammount).reversed())
                .collect(Collectors.toList());
    }



    //Métodos privados
    //Verificar se a conta existe pelo Id
    private void verifyIfAccountExists(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new RuntimeException("Não existe uma conta com esse número de id.");
        }
    }

    //Converter uma lista de Transações em Uma lista de DTOs
    private List<TransactionResponseDTO> convertListTransactionToListDTO(List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> new TransactionResponseDTO(
                        transaction.getId(),
                        transaction.getType(),
                        transaction.getCategory(),
                        transaction.getValue(),
                        transaction.getDescription(),
                        transaction.getRegistrationDate()))
                .collect(Collectors.toList());
    }

    //Listar débitos por categoria
    private List<Transaction> getDebitsByCategoryByAccount (Account account, TransactionCategory category) {
        return account.getTransactions().stream()
                .filter(t -> t.getType().equals(TransactionType.DEBITO))
                .filter(t -> t.getCategory().equals(category))
                .collect(Collectors.toList());
    }
}
