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
    @GetMapping("/product/{product_code}")
    public ResponseEntity<?> getProductByCode(@PathVariable Long product_code) {
        try {
            List<ProductDetailDto> dtos = productService.productsByCode(product_code);
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        }catch(Exception e){
            String errorMessage = "상품 조회에 실패하였습니다: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    // 상품 비교 페이지
    @GetMapping("/product/type/{type}")
    public ResponseEntity<?> getProductByType(@PathVariable String type) {
        try {
            List<ProductTypeDto> dtos = productService.productByType(type);
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        }catch(Exception e){
            String errorMessage = "상품 목록 조회에 실패하였습니다: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    // 상품 추가
    @PostMapping("/product")
    public ResponseEntity<?> create(@RequestBody ProductDto productDto){
        try {
            ProductDto dtos = productService.create(productDto);
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        }catch(Exception e){
            ResponseDto responseDto=new ResponseDto();
            responseDto.setMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    // 상품 수정
    @PatchMapping("/product/{product_code}")
    public ResponseEntity<?> update(@PathVariable Long product_code, @RequestBody ProductDto productDto){
        try{
        ProductDto dtos=productService.update(product_code, productDto);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
        }catch(Exception e){
            ResponseDto responseDto=new ResponseDto();
            responseDto.setMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    // 상품 삭제
    @DeleteMapping("/product/{product_code}")
    public ResponseEntity<?> delete(@PathVariable Long product_code){
        try {
            ProductDto dtos = productService.delete(product_code);
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        }catch(Exception e){
            ResponseDto responseDto=new ResponseDto();
            responseDto.setMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }
}
