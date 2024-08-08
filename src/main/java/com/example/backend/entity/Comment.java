package com.example.backend.entity;

import com.example.backend.dto.CommentDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Table(name = "comments")
@Entity // 해당 클래스가 엔티티임을 선언, 클래스 필드를 바탕으로 DB에 테이블 생성
@Getter // 각 필드 값을 조회할 수 있는 getter 메서드 자동 생성
@ToString // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor // 매개변수가 아예 없는 기본 생성자 자동 생성

public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // DB에 등록될 때 자동으로 1씩 증가
    private Long comment_code;

    @Column(name="comment_content")
    private String comment_content;

    @Column(name="comment_write_date_time", insertable = false, updatable = false) // 현재 시각이 자동으로 기록되도록 함
    private LocalDateTime comment_write_date_time;

    @ManyToOne
    @JoinColumn(name = "product_code")
    @JsonManagedReference
    private Product product;

    @ManyToOne
    @JoinColumn(name="user_code")
    private User user;

    public static Comment createComment(CommentDto commentDto, Product product, User user) {
        // 예외 발생
        if (commentDto.getComment_code() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if (commentDto.getProduct_code() != product.getProduct_code())
            throw new IllegalArgumentException("댓글 생성 실패! 상품의 id가 잘못됐습니다.");
        if(commentDto.getUser_code() != user.getUser_code())
            throw  new IllegalArgumentException("댓글 생성 실패! 사용자의 code가 잘못됐습니다.");

        // 엔티티 생성 및 반환
        return new Comment(
                commentDto.getComment_code(),
                commentDto.getComment_content(),
                commentDto.getComment_write_date_time(),
                product,
                user
        );
    }

    public void patch(CommentDto commentDto) {
        // 예외 발생
        if (this.comment_code != commentDto.getComment_code())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력됐습니다.");
        // 객체 갱신
        if (commentDto.getComment_content() != null) // 수정할 본문 데이터가 있다면
            this.comment_content = commentDto.getComment_content(); // 내용 반영
        this.comment_write_date_time=commentDto.getComment_write_date_time();
    }
}
