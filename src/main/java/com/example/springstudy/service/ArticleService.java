package com.example.springstudy.service;

import com.example.springstudy.dto.ArticleForm;
import com.example.springstudy.entity.Article;
import com.example.springstudy.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service  // 서비스 객체 생성
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;  // 게시글 리파지터리 객체 주입

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();  // dto -> 엔티티로 변환한 후 article에 저장
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);  // article을 DB에 저장
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. DTO -> 엔티티 변환하기`
        Article article = dto.toEntity();
        log.info("id : {}, article : {}", id, article.toString());  // 로그 찍기

        // 2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리하기
        if (target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id : {}, article : {}", id, article.toString());
            return null;
        }

        // 4. 업데이트 및 정상 응답하기
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        // 1. DB에서 대상 엔티티가 있는지 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 2. ResponseEntity에 상태와 본문을 실어 보낼 필요가 없기 때문에, 그 역할은 컨트롤러가 진행
        // 따라서 그냥 null을 반환
        if (target == null) {
            return null;
        }
        // 3. 삭제한 대상을 컨트롤러에 보내줘야 하므로 target을 반환
        articleRepository.delete(target);
        return null;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. dto묶음(리스트)를 엔티티 묶음(리스트)로 변환하기
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        // 2. 엔티티 묶음(리스트)를 DB에 저장하기
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        // 3. 강제로 에러를 발생시키기
        articleRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("결제 실패!"));

        // 4. 결과 값 반환하기
        return articleList;
    }
}
