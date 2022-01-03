package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j // // 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired //@Autowired: ArticleRespository 클래스 선언만으로도 스프링부트가 미리 생성해놓은 객체를 가져다가 자동연결.
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;


    @GetMapping("/articles/new")
    public String newArticleForm(){

        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form, RedirectAttributes rttr){

        //DTO??(ArticleForm)? title, content? ??? ??

        //System.out.println(form.toString());
        // sysout => log.info()? ??.
        log.info("form.toString() => "+form.toString());

        //DTO를 ==> 엔티티로 변환
        // form data를 Article 엔티티로 만듬. 그 엔티티를 DB에 전달하는 구조.
        Article article = form.toEntity();

        //System.out.println(article.toString());
        log.info("article.toString() => "+article.toString());


        // Repository 에게 엔티티를 DB에 저장하게함.
        // Article 클래스에서 article 객체(ID 자동생성,title,content)를 DB에 전달
        // Article  article ??(ID ????,title,content)? DB? ??

        Article saved = articleRepository.save(article);

        //System.out.println(saved.toString());
        log.info("saved.toString() => "+saved.toString());
        log.info("Article saved  id ");

        rttr.addFlashAttribute("msg","추가 되었습니다");

        return "redirect:/articles/" + saved.getId(); // Getter
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id,Model model){
        log.info(" ################# @Pathvariable id =>  "+id);
        
        // 1. // 1. id로 데이터 가져옴. null return.
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);

        // 2. 데이터를 모델에 등록
        model.addAttribute("article",articleEntity);
        model.addAttribute("commentDtos",commentDtos);


        // 3. 보여줄 페이지 설정
        return "articles/show";
    }

    @GetMapping({"/","/articles"})
        public String index(Model model){

        // 1. 모든 Article 을 가져온다.
        //List<Article> articleEntityList = (List<Article>) articleRepository.findAll();
        //Iterable<Article> articleEntityList = (List<Article>) articleRepository.findAll();
        List<Article> articleEntityList =  articleRepository.findAll();

        // 2. 가져온 Article 묶음을 뷰로 전달.
        model.addAttribute("articleList",articleEntityList);

        // 3. 뷰 페이지 설정

            return "articles/index"; // articles/index.mustache page
        }

        @GetMapping("/articles/{id}/edit")
        public String edit(@PathVariable long id,Model model){
        ////수정할 데이터 가져오기
            Article articleEntity =  articleRepository.findById(id).orElse(null);
           // 모델에 데이터 등록
            model.addAttribute("article",articleEntity);
        //뷰페이지 설정
        return "articles/edit"; //edit form
        }


        @PostMapping("/articles/update")
        public String update(ArticleForm form, RedirectAttributes rttr){
        log.info("################# ArticleForm form.toString => "+form.toString());

        // form action with id,title,content => /article/update
        // dto<form data: id,title,content>?
            // Entity<DB table data => field: id,title,content>? ????.
        Article articleEntity = form.toEntity();
        log.info("#### Article articleEntity >> form.toEntity() => ",form.toEntity());
        log.info("#### form.toEntity().toString() => ",form.toEntity().toString());

        // 2.엔티티 DB 저장
            //2-1 데이타를  id로  조회후 데이타 가져오기.
            //Optional<Article> target = articleRepository.findById(articleEntity.getId());
            Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
            log.info("#### id로 조회된 삭제 대상 설정 :: articleRepository.getId.orElse(null);");
            log.info("##### 삭제할 대상.toString() : Article target.toString() =>"+target.toString());

            //2-2 엔티티를 레파지토리에 저장.
            if(target != null){
                articleRepository.save(articleEntity);
                rttr.addFlashAttribute("msg","수정완료");
            }
            log.info("#### articleRepository.save(articleEntity)");

        //  DB 저장후 페이지 이동.
            log.info(" ### redirect:/articles");
        return "redirect:/articles/" + articleEntity.getId();
        }

        //@DeleteMapping("/articles/{id}/delete")
        @GetMapping("/articles/{id}/delete")
        public String delete(@PathVariable long id, RedirectAttributes rttr){
            log.info("삭제요청이 들어왔습니다");

            // 1. id로 검색된 삭제 대상을 Article target 에 담는다.
            Article target = articleRepository.findById(id).orElse(null);
            log.info("##### 삭제할 대상 Article target.toString() =>"+target.toString());

            // 2. 대상을 삭제한다.
            if(target != null){

                articleRepository.delete(target); // repository => delete(대상 Article Entity)
                rttr.addFlashAttribute("msg","삭제완료");
            }

            // 3.  결과 페이지(목록페이지)로 리다이렉트한다.
            return "redirect:/articles";

        }
}
