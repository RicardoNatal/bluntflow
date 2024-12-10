package br.com.tabacaria.sistema.controller;

import br.com.tabacaria.sistema.dto.ResponseDTO;
import br.com.tabacaria.sistema.model.User;
import br.com.tabacaria.sistema.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createUser(@RequestBody @Valid User user) {
        userService.createUser(user);
        return new ResponseEntity<>(ResponseDTO.builder().message(List.of("Usuário criado")).status(HttpStatus.CREATED).build(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> listAllUsers() {
        return ResponseEntity.ok(userService.findUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> listUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteUseById(@PathVariable Long id) {
        ResponseDTO responseDTO = userService.deleteUserById(id);
        return new ResponseEntity<>(responseDTO, responseDTO.status());
    }
}
