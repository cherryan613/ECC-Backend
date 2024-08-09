package com.example.backend.dto;

import com.example.backend.entity.Product;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor // 매개변수가 아예 없는 기본 생성자 자동 생성
@Getter // 각 필드 값을 조회할 수 있는 getter 메서드 자동 생성
@Setter // 각 필드 값을 설정할 수 있는 setter 메서드 자동 생성
@ToString // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성

public class ProductDetailDto { // 상품 상세 페이지(모든 정보)

    private Long productCode;
    private String productName;
    private String productType;
    private BigDecimal productInterestRate;
    private BigDecimal productInterestTopRate;
    private String productBank;
    private String productAmount;
    private String productAge;
    private String productTerm;
    private String productBenefit;
    private String productUrl;
    private String productDescription;
    private LocalDateTime productLastUpdate;

    public static ProductDetailDto createProductDto(Product product){
        return new ProductDetailDto(
                product.getProductCode(),
                product.getProductName(),
                product.getProductType(),
                product.getProductInterestRate(),
                product.getProductInterestTopRate(),
                product.getProductBank(),
                product.getProductAmount(),
                product.getProductAge(),
                product.getProductTerm(),
                product.getProductBenefit(),
                product.getProductUrl(),
                product.getProductDescription(),
                product.getProductLastUpdate()
        );
    }
}