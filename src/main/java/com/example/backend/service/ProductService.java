package com.example.backend.service;

import com.example.backend.dto.ProductDetailDto;
import com.example.backend.dto.ProductDto;
import com.example.backend.dto.ProductTypeDto;
import com.example.backend.entity.Comment;
import com.example.backend.entity.Product;
import com.example.backend.repository.CommentRepository;
import com.example.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CommentRepository commentRepository;

    // 상품 코드로 상품 리스트 반환
    public List<ProductDetailDto> productsByCode(Long product_code) {
        List<Product> products = productRepository.findByProductCode(product_code);
        return products.stream()
                .map(ProductDetailDto::createProductDto)
                .collect(Collectors.toList());
    }

    // 상품 타입으로 상품 리스트 반환
    public List<ProductTypeDto> productByType(String product_type){
        List<Product> products = productRepository.findByProductType(product_type);
        return products.stream()
                .map(ProductTypeDto::createProductDto)
                .collect(Collectors.toList());
    }

    // 댓글 리스트
    public List<Comment> getComments(){
        return commentRepository.findAll();
    }

    // 상품 생성
    @Transactional
    public ProductDto create(ProductDto productDto) {
        // 상품 엔티티 생성
        Product product=Product.createProduct(productDto);
        // DB로 갱신
        Product created=productRepository.save(product);
        // 상품 엔티티를 DTO로 변환 및 반환
        return ProductDto.createProductDto(created);
    }

    // 상품 수정
    @Transactional
    public ProductDto update(Long product_code, ProductDto productDto) {
        // 상품 조회 및 예외 발생
        Product target=productRepository.findById(product_code)
                .orElseThrow(() -> new IllegalArgumentException("상품 수정 실패! 대상 상품이 없습니다."));
        // 상품 수정
        target.patch(productDto);
        // DB로 갱신
        Product updated=productRepository.save(target);
        //상품 엔티티를 DTO로 변환 및 반환
        return ProductDto.createProductDto(updated);
    }

    // 상품 삭제
    @Transactional
    public ProductDto delete(Long product_code) {
        // 상품 조회 및 예외 발생
        Product target=productRepository.findById(product_code)
                .orElseThrow(() -> new IllegalArgumentException("상품 삭제 실패! 대상 상품이 없습니다."));
        // 상품 삭제
        productRepository.delete(target);
        // 삭제 상품을 DTO로 변환 및 반환
        return ProductDto.createProductDto(target);
    }
}
