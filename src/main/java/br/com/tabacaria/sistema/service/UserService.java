package br.com.tabacaria.sistema.service;

import br.com.tabacaria.sistema.dto.ResponseDTO;
import br.com.tabacaria.sistema.exception.NegocioException;
import br.com.tabacaria.sistema.exception.handler.GlobalExceptionHandler;
import br.com.tabacaria.sistema.model.User;
import br.com.tabacaria.sistema.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NegocioException("Usuário não encontrado ou não existe."));
    }

    public ResponseDTO deleteUserById(Long id) {
        boolean userExists = userRepository.existsById(id);

        if (!userExists) throw new NegocioException("Usuário não existe.");

        userRepository.deleteById(id);
        return ResponseDTO.builder().message(List.of("Usuário deletado.")).status(HttpStatus.OK).build();
    }
}
