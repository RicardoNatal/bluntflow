package br.com.tabacaria.sistema.controller;

import br.com.tabacaria.sistema.dto.ResponseDTO;
import br.com.tabacaria.sistema.model.dto.AuthenticationDTO;
import br.com.tabacaria.sistema.model.dto.RegisterDTO;
import br.com.tabacaria.sistema.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return new ResponseEntity<>(ResponseDTO.builder().message(List.of("Usuário logado com sucesso!")).status(HttpStatus.OK).build(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody @Valid RegisterDTO registerDTO) {
        adminService.createUserAdmin(registerDTO);

        return new ResponseEntity<>(ResponseDTO.builder().message(List.of("Usuário criado com sucesso!")).status(HttpStatus.CREATED).build(), HttpStatus.CREATED);
    }
}
