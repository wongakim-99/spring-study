package com.example.springstudy.api;

import com.example.springstudy.dto.ArticleForm;
import com.example.springstudy.entity.Article;
import com.example.springstudy.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;

    // GET
    @GetMapping("/api/articles")  // 전체 게시글 조회
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")  // 단일 게시글 조회
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    // POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);  // 서비스를 통해 게시글 수정
        return (updated != null) ?  // 수정되면 정상, 안 되면 오류 응답
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);  // 서비스를 통해 게시글 삭제
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //trans action 메서드 정의
    @PostMapping("api/transaction-test")  // 여러 게시글 생성 요청 접수
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articleService.createArticles(dtos);  // 서비스 호출
        return (createdList != null) ?  // 생성 결과에 따라 응답 처리
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
