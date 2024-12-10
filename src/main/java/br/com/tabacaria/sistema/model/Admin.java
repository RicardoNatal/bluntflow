package br.com.tabacaria.sistema.model;

import br.com.tabacaria.sistema.model.roles.AdminRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "TB_ADMIN")
@Builder
public class Admin implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @NotBlank(message = "Senha é obrigatório.")
    private String password;

    @NotBlank(message = "Nome é obrigatório.")
    private String name;

    private AdminRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == AdminRole.OWNER) {
            return List.of(new SimpleGrantedAuthority("ROLE_OWNER"),
                           new SimpleGrantedAuthority("ROLE_MANAGER"),
                           new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
        } else if (this.role == AdminRole.MANAGER) {
            return List.of(new SimpleGrantedAuthority("ROLE_MANAGER"),
                           new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
        }
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
        return true;
    }

    @Override
    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
        return true;
    }
}
