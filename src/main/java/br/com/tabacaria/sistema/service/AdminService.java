package br.com.tabacaria.sistema.service;

import br.com.tabacaria.sistema.exception.NegocioException;
import br.com.tabacaria.sistema.model.Admin;
import br.com.tabacaria.sistema.model.dto.RegisterDTO;
import br.com.tabacaria.sistema.repository.AdminRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    private final AdminRepository adminRepository;

    public void createUserAdmin(RegisterDTO admin) {
        if(adminRepository.findByUsername(admin.username()) != null) throw new NegocioException("Usuário informado já existe!");

        String encryptedPassword = new BCryptPasswordEncoder().encode(admin.password());
        Admin newAdmin = Admin.builder().username(admin.username()).password(encryptedPassword).name(admin.name()).role(admin.role()).build();

        adminRepository.save(newAdmin);
    }

    public Admin findAdminByUsername(String username) {
        return adminRepository.findById(username).orElseThrow(() -> new NegocioException("Usuário inválido."));
    }
}
