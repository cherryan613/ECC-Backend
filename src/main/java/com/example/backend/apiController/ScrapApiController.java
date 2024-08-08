package com.example.backend.apiController;

import com.example.backend.dto.ResponseDto;
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
    public ResponseEntity<?> scraps(@PathVariable Long user_code) {
        try {
            // 서비스에 위임
            List<ScrapDto> dtos = scrapService.scraps(user_code);
            // 결과 응답
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        }catch(Exception e){
            String errorMessage = "스크랩 목록 조회에 실패하였습니다: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    // 스크랩 저장
    @PostMapping("/api/users/{user_code}/scraps")
    public ResponseEntity<?> save(@PathVariable Long user_code, @RequestBody ScrapDto dto) {
        try {
            // 서비스에 위임
            ScrapDto savedDto = scrapService.save(user_code, dto);
            savedDto.setMsg("스크랩을 성공적으로 저장하였습니다.");
            // 결과 응답
            return ResponseEntity.status(HttpStatus.OK).body(savedDto);
        }catch(Exception e){
            ResponseDto responseDto=new ResponseDto();
            responseDto.setMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    // 스크랩 수정
    @PatchMapping("/api/scraps/{scrap_code}")
    public ResponseEntity<?> update(@PathVariable Long scrap_code, @RequestBody ScrapDto dto) {
        try {
            // 서비스에 위임
            ScrapDto updatedDto = scrapService.update(scrap_code, dto);
            updatedDto.setMsg("스크랩을 성공적으로 수정하였습니다.");
            // 결과 응답
            return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
        }catch(Exception e){
            ResponseDto responseDto=new ResponseDto();
            responseDto.setMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    // 스크랩 삭제
    @DeleteMapping("/api/scraps/{scrap_code}")
    public ResponseEntity<?> delete(@PathVariable Long scrap_code) {
        try {
            // 서비스에 위임
            ScrapDto deletedDto = scrapService.delete(scrap_code);
            deletedDto.setMsg("스크랩을 성공적으로 삭제하였습니다.");
            // 결과 응답
            return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
        }catch(Exception e){
            ResponseDto responseDto=new ResponseDto();
            responseDto.setMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }
}
