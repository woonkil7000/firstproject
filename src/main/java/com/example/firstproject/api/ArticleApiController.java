package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
@Slf4j
@RestController
public class ArticleApiController {

    @Autowired // DI, 생성 객체를 가져와 연결
    private ArticleService articleService;


    //GET
    @GetMapping("/api/articles")
    public List<Article> index(){

        return articleService.index();

    }

    // GET 단건
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){

        return articleService.show(id);

    }

    // POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){

        // form data 가 아닌 json data 이므로 @RequestBody 로 받는다.

        Article created =  articleService.create(dto);

        return (created != null) ? // 3항 연산자 true : false
                ResponseEntity.status(HttpStatus.CREATED).body(created) : // : 3항 연산자 1.true 2.false
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // Patch
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {

        Article updated = articleService.update(id,dto);

        return (updated != null) ?
            ResponseEntity.status(HttpStatus.OK).body(updated) : // 수정 후 json 수정 후 데이타 리턴.
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // Delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {

        Article deleted = articleService.delete(id);

        return (deleted != null) ?
        // 데이타 반환
        ResponseEntity.status(HttpStatus.OK).build() :
        ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 트랜잭션 -> 실패 -> 롤백!
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){

        List<Article> createList = articleService.createArticles(dtos);

        return (createList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
