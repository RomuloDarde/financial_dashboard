package financial_dashboard.controller;

import financial_dashboard.dto.AccountResponseDTO;
import financial_dashboard.dto.AmountByCategoryResponseDTO;
import financial_dashboard.dto.TransactionResponseDTO;
import financial_dashboard.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    //Atributos
    @Autowired
    private AccountService service;


    //Métodos
    //Bucar todos
    @GetMapping
    public List<AccountResponseDTO> getAccounts() {
        return service.getAccounts();
    }

    //Buscar por id
    @GetMapping("/{id}")
    public AccountResponseDTO getAccountById(@PathVariable Long id) {
        return service.getAccountById(id);
    }

    //Buscar transações por conta
    @GetMapping("/{id}/transactions")
    public List<TransactionResponseDTO> getTransactionsByAccount(@PathVariable Long id) {
        return service.getTransactionsByAccount(id);
    }

    //Buscar débitos por categoria por conta
    @GetMapping("/{id}/transactions/")
    public List<TransactionResponseDTO> getDebitsByCategoryByAccount(
            @PathVariable Long id, @RequestParam("category") String category) {
        return service.getDebitsByCategoryByAccount(id, category);
    }

    //Ranking dos gastos por categoria por conta
    @GetMapping("/{id}/categoryrank")
    public List<AmountByCategoryResponseDTO> getExpensesRankByCategoryByAccount(@PathVariable Long id) {
        return service.getExpensesRankByCategoryByAccount(id);
    }
}
