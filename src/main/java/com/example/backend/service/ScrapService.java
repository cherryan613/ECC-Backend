package com.example.backend.service;

import com.example.backend.dto.ScrapDto;
import com.example.backend.entity.Product;
import com.example.backend.entity.Scrap;
import com.example.backend.entity.User;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.ScrapRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScrapService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ScrapRepository scrapRepository;
    @Autowired
    private UserRepository userRepository;

    public List<ScrapDto> scraps(Long user_code){
        List<Scrap> scraps=scrapRepository.ScrapByUserCode(user_code);
        return scraps.stream()
                .map(ScrapDto::createScrapDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ScrapDto save(Long user_code, ScrapDto scrapDto) {
        // 1. 상품 조회 및 예외 발생
        Product product=productRepository.findById(scrapDto.getProduct_code())
                .orElseThrow(() -> new IllegalArgumentException("스크랩 생성 실패! 대상 상품이 없습니다."));
        // 2. 사용자 조회 및 예외 발생
        User user=userRepository.findById(user_code)
                .orElseThrow(() -> new IllegalArgumentException("스크랩 생성 실패! 사용자가 존재하지 않습니다."));
        // 3. 댓글 엔티티 생성
        Scrap scrap = Scrap.createScrap(scrapDto, product, user);
        // 4. 댓글 엔티티를 DB에 저장
        Scrap saved=scrapRepository.save(scrap);
        // 5. DTO로 변환해 반환
        return ScrapDto.createScrapDto(saved);
    }

    @Transactional
    public ScrapDto update(Long scrap_code, ScrapDto scrapDto) {
        // 1. 댓글 조회 및 예외 발생
        Scrap target=scrapRepository.findById(scrap_code)
                .orElseThrow(() -> new IllegalArgumentException("스크랩 수정 실패! 대상 스크랩이 없습니다."));
        // 2. 댓글 수정
        target.patch(scrapDto);
        // 3. DB로 갱신
        Scrap updated=scrapRepository.save(target);
        // 4. 댓글 엔티티를 DTO로 변환 및 반환
        return ScrapDto.createScrapDto(updated);
    }

    @Transactional
    public ScrapDto delete(Long scrap_code) {
        // 1. 댓글 조회 및 예외 발생
        Scrap target=scrapRepository.findById(scrap_code)
                .orElseThrow(() -> new IllegalArgumentException("스크랩 삭제 실패! 대상 스크랩이 없습니다."));
        // 2. 댓글 삭제
        scrapRepository.delete(target);
        // 3. 삭제 댓글을 DTO로 변환 및 반환
        return ScrapDto.createScrapDto(target);
    }

}
