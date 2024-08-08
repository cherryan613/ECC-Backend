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

public class ProductDto {

    private String msg;
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
    private String feat1;
    private String feat2;
    private String feat3;

    public ProductDto(Long product_code, String product_name, String product_type, BigDecimal product_interest_rate,
                      BigDecimal product_interest_top_rate, String product_bank, String product_amount,
                      String product_age, String product_term, String product_benefit, String product_url,
                      String product_description, LocalDateTime product_last_update, String feat1, String feat2, String feat3) {
        this.product_code = product_code;
        this.product_name = product_name;
        this.product_type = product_type;
        this.product_interest_rate = product_interest_rate;
        this.product_interest_top_rate = product_interest_top_rate;
        this.product_bank = product_bank;
        this.product_amount = product_amount;
        this.product_age = product_age;
        this.product_term = product_term;
        this.product_benefit = product_benefit;
        this.product_url = product_url;
        this.product_description = product_description;
        this.product_last_update = product_last_update;
        this.feat1 = feat1;
        this.feat2 = feat2;
        this.feat3 = feat3;
    }

    public static ProductDto createProductDto(Product product){
        return new ProductDto(
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
                product.getProduct_last_update(),
                product.getFeat1(),
                product.getFeat2(),
                product.getFeat3()
        );
    }
}
