package com.example.backend.dto;

import com.example.backend.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor // 매개변수가 아예 없는 기본 생성자 자동 생성
@Getter // 각 필드 값을 조회할 수 있는 getter 메서드 자동 생성
@Setter // 각 필드 값을 설정할 수 있는 setter 메서드 자동 생성
@ToString // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성

public class CommentDto {

    private Long commentCode;
    private String commentContent;
    private LocalDateTime commentWriteDateTime;
    private Long productCode;
    private Long userCode;

    public static CommentDto createCommentDto(Comment comment){
        return new CommentDto(
                comment.getCommentCode(), // 댓글 엔티티의 id
                comment.getCommentContent(), // 댓글 엔티티의 comment
                comment.getCommentWriteDateTime(), // 댓글 엔티티의 writeDateTime
                comment.getProduct().getProductCode(), // 댓글 엔티티가 속한 상품의 id
                comment.getUser().getUserCode() // 댓글을 작성한 사용자의 code
        );
    }
}