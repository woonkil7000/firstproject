package com.example.firstproject.api;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController // Return JSON DATA
public class TestApiController {

    @Autowired
    private TestService testService;


    // 리스트 조회.
    @GetMapping("/api/testlist")
    public List<Article> index(){
        //return articleRepository.findAll(); // JSON DATA
        return testService.index();
    }

    // 단건 조회
    @GetMapping("/api/testlist/{id}")
    public Article show(@PathVariable Long id){

        return testService.show(id);
    }

    // POST
    @PostMapping("/api/testlist")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {

        Article created = testService.create(dto);
        return (created != null) ?
            ResponseEntity.status(HttpStatus.OK).body(created):
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping("/api/testlist/{id}")
    public ResponseEntity<Article> update(@RequestBody ArticleForm dto, @PathVariable Long id){
        // return 상태  값을 JSON 데이타로 반환.

        Article updated = testService.update(dto,id);

        // 3. 잘못된 요청 처리(대상이 없거나 아이디가 다른 경우)
         return ( updated != null) ?
                 ResponseEntity.status(HttpStatus.OK).body(updated):
                 ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/testlist/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){

        Article deleted = testService.delete(id);

        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}











