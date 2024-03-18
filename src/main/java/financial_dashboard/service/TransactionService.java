package financial_dashboard.service;

import financial_dashboard.dto.TransactionPostRequestDTO;
import financial_dashboard.dto.TransactionResponseDTO;
import financial_dashboard.dto.TransactionUpdateRequestDTO;
import financial_dashboard.model.Transaction;
import financial_dashboard.repository.AccountRepository;
import financial_dashboard.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    //Atributos
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;


    //Métodos
    //Buscar todos
    public List<TransactionResponseDTO> getTransactions() {
        return transactionRepository.findAll().stream()
                .map(transaction -> new TransactionResponseDTO(
                        transaction.getId(),
                        transaction.getType(),
                        transaction.getCategory(),
                        transaction.getValue(),
                        transaction.getDescription(),
                        transaction.getRegistrationDate()))
                .collect(Collectors.toList());
    }

    //Buscar por id
    public TransactionResponseDTO getTransactionById(Long id) {
        verifyIfTransactionExists(id);
        var transaction = transactionRepository.findById(id).get();

        return new TransactionResponseDTO(
                transaction.getId(),
                transaction.getType(),
                transaction.getCategory(),
                transaction.getValue(),
                transaction.getDescription(),
                transaction.getRegistrationDate());
    }

    //Postar
    public void postTransaction(TransactionPostRequestDTO dto) {
        verifyIfAccountExists(dto.accountId());
        var account = accountRepository.findById(dto.accountId()).get();

        var transaction = new Transaction(
                dto.type(),
                dto.category(),
                dto.value(),
                dto.description());

        transaction.addAccount(account);
        transactionRepository.save(transaction);

        account.updateCurrentBalance();
        accountRepository.save(account);

    }

    //Atualizar
    public void updateTransactionById(Long id, TransactionUpdateRequestDTO dto) {
        verifyIfTransactionExists(id);
        var transaction = transactionRepository.findById(id).get();
        transaction.updateData(dto);
        transactionRepository.save(transaction);

        var account = accountRepository.findById(transaction.getAccount().getId()).get();
        account.updateCurrentBalance();
        accountRepository.save(account);

    }

    //Deletar
    public void deleteTransactionById(Long id) {
        verifyIfTransactionExists(id);
        var transaction = transactionRepository.findById(id).get();
        transactionRepository.deleteById(id);

        var account = accountRepository.findById(transaction.getAccount().getId()).get();
        account.updateCurrentBalance();
        accountRepository.save(account);





    }


    //Verificar se a transação existe
    private void verifyIfTransactionExists(Long id) {
        if (transactionRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Não existe uma transação com esse número de Id.");
        }
    }

    //Verificar se a conta existe
    private void verifyIfAccountExists(Long accountId) {
        if (accountRepository.findById(accountId).isEmpty()) {
            throw new RuntimeException("Não existe uma conta com esse número de Id.");
        }
    }
}
