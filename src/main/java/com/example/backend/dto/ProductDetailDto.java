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

    private Long product_code;
    private String product_name;
    private String product_type;
    private BigDecimal product_interest_rate;
    private BigDecimal product_interest_top_rate;
    private String product_bank;
    private String product_amount;
    private String product_age;
    private String product_term;
    private String product_benefit;
    private String product_url;
    private String product_description;
    private LocalDateTime product_last_update;

    public static ProductDetailDto createProductDto(Product product){
        return new ProductDetailDto(
                product.getProduct_code(),
                product.getProduct_name(),
                product.getProduct_type(),
                product.getProduct_interest_rate(),
                product.getProduct_interest_top_rate(),
                product.getProduct_bank(),
                product.getProduct_amount(),
                product.getProduct_age(),
                product.getProduct_term(),
                product.getProduct_benefit(),
                product.getProduct_url(),
                product.getProduct_description(),
                product.getProduct_last_update()
        );
    }
}
