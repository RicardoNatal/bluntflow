package br.com.tabacaria.sistema.controller;

import br.com.tabacaria.sistema.dto.ResponseDTO;
import br.com.tabacaria.sistema.model.Product;
import br.com.tabacaria.sistema.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    private ResponseEntity<ResponseDTO> createProducts(@RequestBody List<Product> products) {
        productService.createProducts(products);
        return new ResponseEntity<>(ResponseDTO.builder().message(List.of("Produtos criados com sucesso!")).status(HttpStatus.CREATED).build(), HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<List<Product>> findAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/id/{id}")
    private ResponseEntity<Product> findProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @GetMapping("/brand")
    private ResponseEntity<List<Product>> findProductsByBrand(@RequestParam String name) {
        return ResponseEntity.ok(productService.findProductsByBrand(name));
    }

    @GetMapping("/name")
    private ResponseEntity<List<Product>> findProductsByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.findProductsByName(name));
    }

    @GetMapping("/price")
    @ResponseBody
    private ResponseEntity<List<Product>> findProductsByRangeValue(@RequestParam BigDecimal initialValue, @RequestParam BigDecimal finalValue) {
        return ResponseEntity.ok(productService.findProductsByRangeValue(initialValue, finalValue));
    }

    @DeleteMapping("{id}")
    private ResponseEntity<ResponseDTO> deleteProductById(@PathVariable Long id) {
        ResponseDTO responseDTO = productService.deleteProductById(id);
        return new ResponseEntity<>(responseDTO, responseDTO.status());
    }

}
