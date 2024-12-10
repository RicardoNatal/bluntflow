package br.com.tabacaria.sistema.model;

import br.com.tabacaria.sistema.validators.annotations.ValidateBirthDate;
import br.com.tabacaria.sistema.validators.annotations.ValidateCpf;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "TB_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório.")
    private String name;

    @NotNull(message = "Data de nascimento é obrigatória.")
    @ValidateBirthDate
    private LocalDate birthDate;

    @Column(unique = true)
    @NotBlank(message = "CPF é obrigatório.")
    @ValidateCpf
    private String cpf;

    @NotBlank(message = "E-mail é obrigatório.")
    @Email(message = "Insira um e-mail válido.")
    private String email;
}
