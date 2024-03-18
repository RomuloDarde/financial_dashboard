package financial_dashboard.dto;

import java.time.LocalDate;

public record UserResponseDTO(
        Long id,
        String name,
        String cpf,
        String email,
        String password,
        LocalDate registrationDate
        ) {

}
