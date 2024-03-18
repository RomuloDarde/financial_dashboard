package financial_dashboard.dto;

import financial_dashboard.model.TransactionCategory;

import java.math.BigDecimal;

public record AmountByCategoryResponseDTO(
        TransactionCategory category,
        BigDecimal ammount){
}
