package financial_dashboard.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UserUpdateRequestDto(
        String name,

        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido.")
        //@CPF
        String cpf,

        @Email
        String email,

        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
                message = "A senha deve conter pelo menos 8 caracteres, " +
                        "incluindo pelo menos uma letra minúscula, " +
                        "uma letra maiúscula, um número e um caractere especial.")
        String password
) {
}
