package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArticleServiceTest {

    @Autowired ArticleService articleService;

    @Test
    void index() {
        // 예상
        Article a = new Article(1L,"가가가","1111");
        Article b = new Article(2L,"나나나","1111");
        Article c = new Article(3L,"다다다","1111");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        // 실제
        List<Article> articles = articleService.index();

        // 비교
        assertEquals(expected.toString(),articles.toString());
    }

    @Test
    void show_성공_존재하는_아이디_입력() {

        // 예상
        Long id =1L;
        Article expected = new Article(id,"가가가","1111");

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    void show_실패_존재하지않는_아이디입력() {

        // 예상 : null 반환.
        Long id =-1L;
        Article expected = null;

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected,article);

    }

    @Test
    @Transactional
    void create_성공_title_content만_입력한_경우() {

        // 예상
        String title="라라라라";
        String content="4444";
        ArticleForm dto = new ArticleForm(null,title,content);

        Article expected = new Article(4L,title,content);

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void create_실패_id가포함된dto가입력된경우() {

        // 예상
        String title="라라라라";
        String content="4444";
        ArticleForm dto = new ArticleForm(4L,title,content);

        Article expected = null;

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title만_있는dto_입력(){

    }
    @Test
    @Transactional
    void update_실패_존재하지않는_id의dto_입력(){

    }
    @Test
    @Transactional
    void update_실패_id만있는_dto_입력(){

    }

    @Test
    @Transactional
    void delete_성공_존재하는_id_입력(){

    }
    @Test
    @Transactional
    void delete_실패_존재하지않는_id_입력(){

    }
}