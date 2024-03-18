package financial_dashboard.service;

import financial_dashboard.dto.UserResponseDTO;
import financial_dashboard.dto.UserPostRequestDTO;
import financial_dashboard.dto.UserUpdateRequestDto;
import financial_dashboard.model.User;
import financial_dashboard.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    //Atributos
    @Autowired
    private UserRepository repository;


    //Métodos
    //Buscar todos
    public List<UserResponseDTO> getUsers() {
        return repository.findAll().stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getCpf(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRegistrationDate()))
                .collect(Collectors.toList());
    }

    //Buscar por id
    public UserResponseDTO getUserById(Long id) {
        verifyIfUserExists(id);
        var user = repository.findById(id).get();
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getCpf(),
                user.getEmail(),
                user.getPassword(),
                user.getRegistrationDate());
    }


    //Cadastrar
    @Transactional
    public void postUser(UserPostRequestDTO dto) {
        var user = new User(dto.name(), dto.cpf(), dto.email(), dto.password());
        user.addAccount();
        repository.save(user);
    }

    //Atualizar
    public void updateUserById(Long id, UserUpdateRequestDto dto) {
        verifyIfUserExists(id);
        var user = repository.findById(id).get();
        user.updateData(dto);
        repository.save(user);
    }

    //Deletar
    public void deleteUserById(Long id) {
        verifyIfUserExists(id);
        repository.deleteById(id);
    }

    //Verificar se o usuário existe
    public void verifyIfUserExists(Long id) {
        if(repository.findById(id).isEmpty()) {
            throw new RuntimeException("Não existe um usuário com esse número de Id.");
        }
    }
}
