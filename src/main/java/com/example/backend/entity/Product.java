package com.example.backend.entity;

import com.example.backend.dto.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "products")
@Entity // 해당 클래스가 엔티티임을 선언, 클래스 필드를 바탕으로 DB에 테이블 생성
@Getter // 각 필드 값을 조회할 수 있는 getter 메서드 자동 생성
@ToString // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor // 매개변수가 아예 없는 기본 생성자 자동 생성
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long product_code;

    @Column(name = "product_name")
    private String product_name;

    @Column(name="product_type")
    private String product_type;

    @Column(name = "product_interest_rate")
    private BigDecimal product_interest_rate;

    @Column(name = "product_interest_top_rate")
    private BigDecimal product_interest_top_rate;

    @Column(name = "product_bank")
    private String product_bank;

    @Column(name = "product_age")
    private String product_age;

    @Column(name = "product_amount")
    private String product_amount;

    @Column(name = "product_url")
    private String product_url;

    @Column(name = "product_term")
    private String product_term;

    @Column(name = "product_benefit")
    private String product_benefit;

    @Column(name="product_description")
    private String product_description;

    @Column(name = "product_last_update", insertable = false, updatable = false) //MySQL이 자동으로 현재 시각 등록
    private LocalDateTime product_last_update;

    @Column(name="feat1")
    private String feat1;

    @Column(name="feat2")
    private String feat2;

    @Column(name="feat3")
    private String feat3;

    public static Product createProduct(ProductDto productDto) {
        // 예외 발생
        if(productDto.getProduct_code() != null)
            throw new IllegalArgumentException("상품 생성 실패! 상품의 code가 없어야 합니다.");

        return new Product(
                productDto.getProduct_code(),
                productDto.getProduct_name(),
                productDto.getProduct_type(),
                productDto.getProduct_interest_rate(),
                productDto.getProduct_interest_top_rate(),
                productDto.getProduct_bank(),
                productDto.getProduct_age(),
                productDto.getProduct_amount(),
                productDto.getProduct_url(),
                productDto.getProduct_term(),
                productDto.getProduct_benefit(),
                productDto.getProduct_description(),
                productDto.getProduct_last_update(),
                productDto.getFeat1(),
                productDto.getFeat2(),
                productDto.getFeat3()
        );
    }

    public void patch(ProductDto productDto) {
        // 예외 발생
        System.out.println("this.product_code: "+this.product_code+", productDto.getProduct_code(): "+productDto.getProduct_code());
        if (this.product_code != productDto.getProduct_code())
            throw new IllegalArgumentException("상품 수정 실패! 잘못된 code가 입력됐습니다.");
        // 객체 갱신
        if (productDto.getProduct_name() != null) { // 수정할 name이 있다면
            this.product_name = productDto.getProduct_name();
            this.product_last_update = productDto.getProduct_last_update();
        }
        if (productDto.getProduct_type() != null) { // 수정할 type이 있다면
            this.product_type = productDto.getProduct_type();
            this.product_last_update = productDto.getProduct_last_update();
        }
        if (productDto.getProduct_interest_rate() != null) {// 수정할 interest rate이 있다면
            this.product_interest_rate = productDto.getProduct_interest_rate();
            this.product_last_update = productDto.getProduct_last_update();
        }
        if (productDto.getProduct_interest_top_rate() != null) { // 수정할 interest top rate이 있다면
            this.product_interest_top_rate = productDto.getProduct_interest_top_rate();
            this.product_last_update = productDto.getProduct_last_update();
        }
        if (productDto.getProduct_interest_top_rate() != null) { // 수정할 interest top rate이 있다면
            this.product_interest_top_rate = productDto.getProduct_interest_top_rate();
            this.product_last_update = productDto.getProduct_last_update();
        }
        if (productDto.getProduct_interest_top_rate() != null){ // 수정할 interest top rate이 있다면
            this.product_interest_top_rate=productDto.getProduct_interest_top_rate();
            this.product_last_update=productDto.getProduct_last_update();
        }
        if (productDto.getProduct_interest_top_rate() != null) { // 수정할 interest top rate이 있다면
            this.product_interest_top_rate = productDto.getProduct_interest_top_rate();
            this.product_last_update = productDto.getProduct_last_update();
        }
    }
}
