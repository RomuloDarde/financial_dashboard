package financial_dashboard.controller;

import financial_dashboard.dto.TransactionPostRequestDTO;
import financial_dashboard.dto.TransactionResponseDTO;
import financial_dashboard.dto.TransactionUpdateRequestDTO;
import financial_dashboard.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    //Atributos
    @Autowired
    private TransactionService service;


    //Métodos
    //Buscar todos
    @GetMapping
    public List<TransactionResponseDTO> getTransactions() {
        return service.getTransactions();
    }

    //Buscar por id
    @GetMapping("/{id}")
    public TransactionResponseDTO getTransactionById(@PathVariable Long id) {
        return service.getTransactionById(id);
    }

    //Cadastrar
    @PostMapping
    public void postTransaction(@RequestBody TransactionPostRequestDTO dto) {
        service.postTransaction(dto);
    }

    //Atualizar
    @PutMapping("/{id}")
    public void updateTransactionById(@PathVariable Long id, @RequestBody TransactionUpdateRequestDTO dto) {
        service.updateTransactionById(id, dto);

    }

    //Deletar
    @DeleteMapping("/{id}")
    public void deleteTransasction(@PathVariable Long id) {
        service.deleteTransactionById(id);
    }
}
