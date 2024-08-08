package com.example.backend.apiController;

import com.example.backend.dto.ProductDetailDto;
import com.example.backend.dto.ProductDto;
import com.example.backend.dto.ProductTypeDto;
import com.example.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductApiController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{product_code}")
    public ResponseEntity<List<ProductDetailDto>> getProductByCode(@PathVariable Long product_code) {
        List<ProductDetailDto> dtos = productService.productsByCode(product_code);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @GetMapping("/product/type/{type}")
    public ResponseEntity<List<ProductTypeDto>> getProductByType(@PathVariable String type) {
        List<ProductTypeDto> dtos = productService.productByType(type);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto){
        ProductDto dtos=productService.create(productDto);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PatchMapping("/product/{product_code}")
    public ResponseEntity<ProductDto> update(@PathVariable Long product_code, @RequestBody ProductDto productDto){
        ProductDto dtos=productService.update(product_code, productDto);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @DeleteMapping("/product/{product_code}")
    public ResponseEntity<ProductDto> delete(@PathVariable Long product_code){
        ProductDto dtos=productService.delete(product_code);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
}
