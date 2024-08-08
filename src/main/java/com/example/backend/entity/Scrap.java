package com.example.backend.entity;

import com.example.backend.dto.ScrapDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Table(name = "scraps")
@Entity // 해당 클래스가 엔티티임을 선언, 클래스 필드를 바탕으로 DB에 테이블 생성
@Getter // 각 필드 값을 조회할 수 있는 getter 메서드 자동 생성
@ToString // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor // 매개변수가 아예 없는 기본 생성자 자동 생성

public class Scrap {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long scrap_code;

    @Column(name = "scrap_time")
    private LocalDateTime scrap_time;

    @Column(name="scrap_memo")
    private String scrap_memo;

    @ManyToOne
    @JoinColumn(name="product_code")
    @JsonManagedReference
    private Product product;

    @ManyToOne
    @JoinColumn(name="user_code")
    @JsonManagedReference
    private User user;

    public static Scrap createScrap(ScrapDto scrapDto, Product product, User user) {
        // 예외 발생
        if(scrapDto.getScrap_code() != null)
            throw new IllegalArgumentException("스크랩 생성 실패! 스크랩의 code가 없어야 합니다.");
        System.out.println("scrapDto.getProduct_code(): "+scrapDto.getProduct_code()+"product.getProduct_code()"+product.getProduct_code());
        if(scrapDto.getProduct_code() != product.getProduct_code())
            throw new IllegalArgumentException("스크랩 생성 실패! 상품의 code가 잘못됐습니다.");
        if(scrapDto.getUser_code() != user.getUser_code())
            throw new IllegalArgumentException("스크랩 생성 실패! 사용자의 code가 잘못됐습니다.");

        return new Scrap(
                scrapDto.getScrap_code(),
                scrapDto.getScrap_time(),
                scrapDto.getScrap_memo(),
                product,
                user
        );
    }

    public void patch(ScrapDto scrapDto) {
        // 예외 발생
        if (this.scrap_code != scrapDto.getScrap_code())
            throw new IllegalArgumentException("스크랩 수정 실패! 잘못된 code가 입력됐습니다.");
        // 객체 갱신
        if (scrapDto.getScrap_memo() != null) // 수정할 본문 데이터가 있다면
            this.scrap_memo = scrapDto.getScrap_memo(); // 내용 반영
        this.scrap_time=scrapDto.getScrap_time();
    }

}
