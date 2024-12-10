package br.com.tabacaria.sistema.service;

import br.com.tabacaria.sistema.dto.ResponseDTO;
import br.com.tabacaria.sistema.exception.NegocioException;
import br.com.tabacaria.sistema.model.Product;
import br.com.tabacaria.sistema.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public void createProducts(List<Product> products) {
        productRepository.saveAll(products);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NegocioException("Produto não encontrado ou não existe."));
    }

    public List<Product> findProductsByBrand(String brand) {
        return productRepository.findProductsByBrand(brand);
    }

    public List<Product> findProductsByRangeValue(BigDecimal initialPice, BigDecimal finalPrice) {
        return productRepository.findProductsByPriceRange(initialPice, finalPrice);
    }

    public List<Product> findProductsByName(String name) {
        return productRepository.findProductsByName(name);
    }

    public ResponseDTO deleteProductById(Long id) {
        boolean productExists = productRepository.existsById(id);

        if (!productExists) throw new NegocioException("Produto não encontrado ou não existe!");

        productRepository.deleteById(id);
        return ResponseDTO.builder().message(List.of("Produto excluido com sucesso!")).status(HttpStatus.OK).build();
    }
}
