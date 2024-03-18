package financial_dashboard.dto;

import java.math.BigDecimal;

public record TransactionUpdateRequestDTO(
        String type,
        String category,
        BigDecimal value,
        String description) {
}
