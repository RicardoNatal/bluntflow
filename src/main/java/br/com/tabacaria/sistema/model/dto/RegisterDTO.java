package br.com.tabacaria.sistema.model.dto;

import br.com.tabacaria.sistema.model.roles.AdminRole;

public record RegisterDTO(String username, String password, AdminRole role, String name) {
}
