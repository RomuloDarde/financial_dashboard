package financial_dashboard.model;

import financial_dashboard.dto.UserUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;


@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String password;

    @CreationTimestamp
    private LocalDate registrationDate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;
    //mapped By identifica o atributo (pelo nome) na entidade relacionada.
    //CascadeType.ALL permite que a entidade relacionada seja persistida
    // no banco de dados junto com essa entidade.


    //Construtor sem id, registrationDate e account
    public User(String name, String cpf, String email, String password) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
    }

    //Método para adicionar uma conta ao usuário
    public void addAccount() {
        this.account = new Account(this);
 }

    //Método para atualizar dados cadastrais
    public void updateData(UserUpdateRequestDto dto) {
        if (dto.name() != null) this.name = dto.name();
        if (dto.email() != null) this.email = dto.email();
        if (dto.cpf() != null) this.cpf = dto.cpf();
        if (dto.password() != null) this.password = dto.password();
    }


}
