package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {

        /* Case 1: 4번 게시글의 모든 댓글 조회 */
        {
        // 입력데이타 준비
        Long articleId = 3L;

        // 실제 수행
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        // 예상하기
        Article article = new Article(3L, "음식", "1111");
        Comment a = new Comment(4L, article, "Park","치킨");
        Comment b = new Comment(5L, article, "Park","샤브샤브");
        Comment c = new Comment(6L, article, "Park","초밥");
        List<Comment> expected = Arrays.asList(a,b,c);

        // 검증
        assertEquals(expected.toString(),comments.toString()," 3번 글의 모든 댓글 출력");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {

        // 입력 데이타 준비
        String nickname = "Park";

        // 실제 수행
        List<Comment> comments = commentRepository.findByNickname(nickname);
        // 예상하기
        Article article = new Article(3L, "음식", "1111");
        Comment a = new Comment(4L, article, nickname,"치킨");
        Comment b = new Comment(5L, article, nickname,"샤브샤브");
        Comment c = new Comment(6L, article, nickname,"초밥");
        List<Comment> expected = Arrays.asList(a,b,c);

        // 검증

    }
}