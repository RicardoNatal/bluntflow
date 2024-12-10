package br.com.tabacaria.sistema.model.roles;

import lombok.Getter;

@Getter
public enum AdminRole {
    EMPLOYEE("employee"), MANAGER("manager"), OWNER("owner");

    private String role;

    AdminRole(String role) {
        this.role = role;
    }
}
