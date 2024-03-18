package financial_dashboard.controller;

import financial_dashboard.dto.UserResponseDTO;
import financial_dashboard.dto.UserPostRequestDTO;
import financial_dashboard.dto.UserUpdateRequestDto;
import financial_dashboard.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    //Atributos
    @Autowired
    private UserService service;

    //Métodos
    //Buscar todos
    @GetMapping
    public List<UserResponseDTO> getUsers() {
        return service.getUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @PostMapping
    public void postUser(@RequestBody @Valid UserPostRequestDTO dto) {
        service.postUser(dto);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateRequestDto dto) {
        service.updateUserById(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        service.deleteUserById(id);
    }

}
