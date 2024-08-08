package com.example.backend.apiController;

import com.example.backend.dto.CommentDto;
import com.example.backend.dto.ResponseDto;
import com.example.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // 상품에 대한 모든 댓글
    @GetMapping("/api/products/{product_code}/comments")
    public ResponseEntity<ResponseDto<?>> comments(@PathVariable Long product_code) {
        try{
            // 서비스에 위임
            List<CommentDto> dtos = commentService.comments(product_code);
            // 결과 응답
            return ResponseEntity.ok(new ResponseDto<>("댓글을 성공적으로 조회하였습니다.", dtos));
        } catch (Exception e) {
            ResponseDto<String> responseDto = new ResponseDto<>(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    // 댓글 생성
    @PostMapping("/api/products/{product_code}/comments")
    public ResponseEntity<ResponseDto<?>> create(@PathVariable Long product_code, @RequestBody CommentDto dto) {
        try {
            // 서비스에 위임
            CommentDto createdDto = commentService.create(product_code, dto);
            // 결과 응답
            return ResponseEntity.ok(new ResponseDto<>("댓글을 성공적으로 생성하였습니다.", createdDto));
        } catch (Exception e) {
            ResponseDto<String> responseDto = new ResponseDto<>(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    // 댓글 수정
    @PatchMapping("/api/comments/{comment_code}")
    public ResponseEntity<ResponseDto<?>> update(@PathVariable Long comment_code, @RequestBody CommentDto dto) {
        try {
            // 서비스에 위임
            CommentDto updatedDto = commentService.update(comment_code, dto);
            // 결과 응답
            return ResponseEntity.ok(new ResponseDto<>("댓글을 성공적으로 수정하였습니다.", updatedDto));
        } catch (Exception e) {
            ResponseDto<String> responseDto = new ResponseDto<>(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    // 댓글 삭제
    @DeleteMapping("/api/comments/{comment_code}")
    public ResponseEntity<ResponseDto<?>> delete(@PathVariable Long comment_code) {
        try {
            // 서비스에 위임
            CommentDto deletedDto = commentService.delete(comment_code);
            // 결과 응답
            return ResponseEntity.ok(new ResponseDto<>("댓글을 성공적으로 삭제하였습니다.", deletedDto));
        } catch (Exception e) {
            ResponseDto<String> responseDto = new ResponseDto<>(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }
}