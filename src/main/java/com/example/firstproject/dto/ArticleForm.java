package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString
public class ArticleForm {

    private Long id; // update 쿼리를 위한 id 추가.
    private String title;
    private String content;

    public Article toEntity() { // 엔티티로 변환.
        //return new Article(null,title,content);
        return new Article(id,title,content);
    }

// @AllArgsConstructor 로 대체
//
//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

// @ToString 로 대체
//
//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

}
