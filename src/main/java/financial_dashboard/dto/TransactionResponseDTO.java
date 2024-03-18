package financial_dashboard.dto;

import financial_dashboard.model.TransactionCategory;
import financial_dashboard.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponseDTO
        (Long id,
         TransactionType type,
         TransactionCategory category,
         BigDecimal value,
         String description,
         LocalDate registrationDate){
}
