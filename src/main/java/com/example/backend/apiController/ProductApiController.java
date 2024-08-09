package com.example.backend.apiController;

import com.example.backend.dto.ProductDetailDto;
import com.example.backend.dto.ProductDto;
import com.example.backend.dto.ProductTypeDto;
import com.example.backend.dto.ResponseDto;
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

    // 상품 상세 페이지
    @GetMapping("/api/products/{productCode}")
    public ResponseEntity<ResponseDto<?>> getProductByCode(@PathVariable Long productCode) {
        try {
            List<ProductDetailDto> dtos = productService.productsByCode(productCode);
            return ResponseEntity.ok(new ResponseDto<>("상품을 성공적으로 조회하였습니다.", dtos));
        } catch (Exception e) {
            ResponseDto<String> responseDto = new ResponseDto<>(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    // 상품 비교 페이지
    @GetMapping("/api/products/productType/{productType}")
    public ResponseEntity<ResponseDto<?>> getProductByType(@PathVariable String productType) {
        try {
            List<ProductTypeDto> dtos = productService.productByType(productType);
            return ResponseEntity.ok(new ResponseDto<>("상품을 성공적으로 조회하였습니다.", dtos));
        } catch (Exception e) {
            ResponseDto<String> responseDto = new ResponseDto<>(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    // 상품 등록
    @PostMapping("/api/products")
    public ResponseEntity<ResponseDto<?>> create(@RequestBody ProductDto productDto){
        try {
            ProductDto dtos = productService.create(productDto);
            return ResponseEntity.ok(new ResponseDto<>("상품을 성공적으로 등록하였습니다.", dtos));
        } catch (Exception e) {
            ResponseDto<String> responseDto = new ResponseDto<>(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    // 상품 수정
    @PatchMapping("/api/products/{productCode}")
    public ResponseEntity<ResponseDto<?>> update(@PathVariable Long productCode, @RequestBody ProductDto productDto){
        try{
            ProductDto dtos=productService.update(productCode, productDto);
            return ResponseEntity.ok(new ResponseDto<>("상품을 성공적으로 수정하였습니다.", dtos));
        } catch (Exception e) {
            ResponseDto<String> responseDto = new ResponseDto<>(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    // 상품 삭제
    @DeleteMapping("/api/products/{productCode}")
    public ResponseEntity<ResponseDto<?>> delete(@PathVariable Long productCode){
        try {
            ProductDto dtos = productService.delete(productCode);
            return ResponseEntity.ok(new ResponseDto<>("상품을 성공적으로 삭제하였습니다.", dtos));
        } catch (Exception e) {
            ResponseDto<String> responseDto = new ResponseDto<>(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }
}
