package br.com.tabacaria.sistema.exception.handler;

import br.com.tabacaria.sistema.dto.ResponseDTO;
import br.com.tabacaria.sistema.exception.NegocioException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> messageList = new ArrayList<>();

        // Itera sobre os erros e cria um mapa de mensagens
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            messageList.add(errorMessage);
        });

        return new ResponseEntity<>(ResponseDTO.builder().message(messageList).status(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<ResponseDTO> handleNegocioException(NegocioException ex) {
        return new ResponseEntity<>(ResponseDTO.builder().message(List.of(ex.getMessage())).status(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
    }
}