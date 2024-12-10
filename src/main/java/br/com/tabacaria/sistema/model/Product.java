package br.com.tabacaria.sistema.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "TB_PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do produto é obrigatório.")
    private String name;

    @NotBlank(message = "Marca do produto é obrigatória.")
    private String brand;

    private BigDecimal price;

}
