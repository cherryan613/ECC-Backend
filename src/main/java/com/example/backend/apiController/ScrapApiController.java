package com.example.backend.apiController;

import com.example.backend.dto.ScrapDto;
import com.example.backend.service.ScrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScrapApiController {

    @Autowired
    ScrapService scrapService;

    // 유저에 대한 모든 스크랩
    @GetMapping("/api/users/{user_code}/scraps")
    public ResponseEntity<List<ScrapDto>> scraps(@PathVariable Long user_code) {
        // 서비스에 위임
        List<ScrapDto> dtos = scrapService.scraps(user_code);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 스크랩 저장
    @PostMapping("/api/users/{user_code}/scraps")
    public ResponseEntity<ScrapDto> save(@PathVariable Long user_code, @RequestBody ScrapDto dto) {
        // 서비스에 위임
        ScrapDto savedDto = scrapService.save(user_code, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(savedDto);
    }

    // 스크랩 수정
    @PatchMapping("/api/scraps/{scrap_code}")
    public ResponseEntity<ScrapDto> update(@PathVariable Long scrap_code, @RequestBody ScrapDto dto) {
        // 서비스에 위임
        ScrapDto updatedDto = scrapService.update(scrap_code, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    // 스크랩 삭제
    @DeleteMapping("/api/scraps/{scrap_code}")
    public ResponseEntity<ScrapDto> delete(@PathVariable Long scrap_code) {
        // 서비스에 위임
        ScrapDto deletedDto = scrapService.delete(scrap_code);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
