package com.example.firstproject.service;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class TestService {

    @Autowired
    private ArticleRepository articleRepository;

    // Update
    @Transactional
    public Article update(ArticleForm dto,Long id) {

        // Make To Entity
        Article article = dto.toEntity();
        // Targeting
        Article target = articleRepository.findById(id).orElse(null);
        // 잘못된 요청 처리(대상이 없거나 아이디가 다른 경우)
        if(target==null || id != article.getId()){
            log.info("잘못된 요청 id: {} , article: {}",id,article.toString());
            return null;
        }
        // 4. update
        target.patch(article);
        return articleRepository.save(target);
    }

    // findAll()
    public List<Article> index() {
        return articleRepository.findAll();
    }

    // findById
    public Article show(Long id) {
        return  articleRepository.findById(id).orElse(null);
    }

    // CREATE
    @Transactional
    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // DELETE
    @Transactional
    public Article delete(Long id) {
        // targeting
        Article target = articleRepository.findById(id).orElse(null);
        // 오류 처리
        if(target == null) {
            log.info("Delete Failed.... target is NULL !!");
            return null;
        }
        // delete and return status
        articleRepository.delete(target);
        return target;
    }
}
