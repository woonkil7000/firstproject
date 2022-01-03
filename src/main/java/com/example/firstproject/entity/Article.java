package com.example.firstproject.entity;

import lombok.*;

import javax.persistence.*;

//@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor // 파라미터가 없는(No Args) 기본 생성자. 디폴트 생성자 추가해준다!!
// 없으면 error syntax: no default constructor for entity!!
@ToString
@Entity // 해당 클래스로 테이블을 생성한다.!! //DB가 해당 객체를 인식가능하게함.
@Getter
@Table(
        name="article"
)
public class Article {

    @Id //대표값. 유니크한 대표값
    //@GeneratedValue //1,2,3,4.....자동생성
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 ID값을 자동생성하도록~
    private Long id;
    
    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article) {

        // patch: null 이 아닌 필드만 저장.
        if(article.title != null){
            this.title = article.title;
        }

        if(article.content != null){
            this.content = article.content;
        }
    }


    // @Geter로 대체
//    public Long getId() {
//        return id;
//    }

// @AllArgsConstructor 로 대체
// 생성자 추가
//    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }

// @ToString 으로 대체됨.
//    // toString 메소드
//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
}
