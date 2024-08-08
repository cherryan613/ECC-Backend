package com.example.backend.service;

import com.example.backend.dto.CommentDto;
import com.example.backend.entity.Comment;
import com.example.backend.entity.Product;
import com.example.backend.entity.User;
import com.example.backend.repository.CommentRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    public List<CommentDto> comments(Long product_code){
        List<Comment> comments = commentRepository.CommentByProductCode(product_code);
        return comments.stream()
                .map(CommentDto::createCommentDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long product_code, CommentDto commentDto) {
        // 1. 게시글 조회 및 예외 발생
        Product product=productRepository.findById(product_code)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 상품이 없습니다."));
        // 2. 사용자 조회 및 예외 발생
        User user=userRepository.findById(commentDto.getUser_code())
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 사용자가 존재하지 않습니다."));
        // 3. 댓글 엔티티 생성
        Comment comment = Comment.createComment(commentDto, product, user);
        // 4. 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);
        // 5. DTO로 변환해 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long comment_id, CommentDto commentDto) {
        // 1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(comment_id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));
        // 2. 댓글 수정
        target.patch(commentDto);
        // 3. DB로 갱신
        Comment updated = commentRepository.save(target);
        // 4. 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long comment_id) {
        // 1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(comment_id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));
        // 2. 댓글 삭제
        commentRepository.delete(target);
        // 3. 삭제 댓글을 DTO로 변환 및 반환
        return CommentDto.createCommentDto(target);
    }

}
