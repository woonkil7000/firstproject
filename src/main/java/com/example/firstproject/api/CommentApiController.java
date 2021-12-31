package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.service.CommentService;
import com.sun.xml.bind.v2.schemagen.xmlschema.Particle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {


    @Autowired
    private CommentService commentService;


    // 댓글 목록조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        // 서비스에게 위임
        List<CommentDto> dtos = commentService.comments(articleId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    // 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,@RequestBody CommentDto dto){

        // 서비스 위임
        CommentDto createDto = commentService.create(articleId,dto);
        // 결과 응답
        return (createDto != null ) ?
                ResponseEntity.status(HttpStatus.OK).body(createDto) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 댓글 수정

    // 댓글 삭제
}
