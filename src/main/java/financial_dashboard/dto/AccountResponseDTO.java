package financial_dashboard.dto;

import java.math.BigDecimal;

public record AccountResponseDTO(
        Long id,
        BigDecimal currentBalance) {
}
