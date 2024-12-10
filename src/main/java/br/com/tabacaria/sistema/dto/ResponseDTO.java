package br.com.tabacaria.sistema.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
public record ResponseDTO(List<String> message, HttpStatus status) {
}
