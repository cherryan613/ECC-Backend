package com.example.backend.dto;

import com.example.backend.entity.Scrap;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor // 매개변수가 아예 없는 기본 생성자 자동 생성
@Getter // 각 필드 값을 조회할 수 있는 getter 메서드 자동 생성
@Setter // 각 필드 값을 설정할 수 있는 setter 메서드 자동 생성
@ToString // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성

public class ScrapDto {

    private String msg;
    private Long scrap_code;
    private LocalDateTime scrap_time;
    private String scrap_memo;
    private Long product_code;
    private Long user_code;

    public ScrapDto(Long scrap_code, LocalDateTime scrap_time, String scrap_memo, Long product_code, Long user_code) {
        this.scrap_code = scrap_code;
        this.scrap_time = scrap_time;
        this.scrap_memo = scrap_memo;
        this.product_code = product_code;
        this.user_code = user_code;
    }

    public static ScrapDto createScrapDto(Scrap scrap){
        return new ScrapDto(
                scrap.getScrap_code(),
                scrap.getScrap_time(),
                scrap.getScrap_memo(),
                scrap.getProduct().getProduct_code(),
                scrap.getUser().getUser_code()
        );
    }
}
