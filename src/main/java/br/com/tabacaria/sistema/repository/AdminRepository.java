package br.com.tabacaria.sistema.repository;

import br.com.tabacaria.sistema.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
   UserDetails findByUsername(String username);
}
