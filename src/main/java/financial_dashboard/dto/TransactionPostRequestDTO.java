package financial_dashboard.dto;

import java.math.BigDecimal;

public record TransactionPostRequestDTO(
        String type,
        String category,
        BigDecimal value,
        String description,
        Long accountId
){
}
