package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service // 서비스 객체를 생성.
public class ArticleService {

    @Autowired // DI
    private ArticleRepository articleRepository;

    public List<Article> index() {

        return articleRepository.findAll();
    }

    public Article show(Long id) {

        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {

        Article article = dto.toEntity();
        if(article.getId()!=null){
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id,ArticleForm dto) {

        // 1. 수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id : {}, article: {}",id,article.toString());

        // 2. 대상 엔티티 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리(대상이 없거나 id가 url과json id가 다른 경우 )
        if(target == null || id != article.getId()){
            log.info("잘못된 요청! id:{}, article:{}",id,article.toString());
            return null;
        }

        // 4. 엡데이트 및 정상 응답(200)
        target.patch(article);
        return articleRepository.save(target);
    }

    public Article delete(Long id) {

        // 대상찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if(target == null ){
            // 400.  잘못된 요청.응답!!
            return null;
        }

        // 대상 삭제
        articleRepository.delete(target);
        return target;
    }

    @Transactional // 해당 메소드를 트랜잭션으로 묶는다.
    public List<Article> createArticles(List<ArticleForm> dtos) {

        // dtos 를 엔티티로 변환
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        /* same upper code
        List<Article> articleList = new ArrayList<>();
        for(int i=0;i<dtos.size();i++) {
            ArticleForm dto = dtos.get(i);
            Article entity = dto.toEntity();
            articleList.add(entity);
            }
         */

        // 엔티티 묶음을 디비로 저장.
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        /*
        for(int i=0;i < articleList.size();i++) {
            Article article = articleList.get(i);
            articleRepository.save(article);
         */

        // 강제 예외 살생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패")
        );


        // 결과값 반환.
        return articleList;
    }
}
