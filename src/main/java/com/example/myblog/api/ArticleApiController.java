package com.example.myblog.api;

import com.example.myblog.dto.ArticleForm;
import com.example.myblog.entity.Article;
import com.example.myblog.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping("/api/articles") // Post 요청이 "/api/articles" url로 온다면, 메소드 수행!
    public Long create(@RequestBody ArticleForm form) { // JSON 데이터를 받아옴!
        log.info(form.toString()); // 받아온 데이터 확인!

        Article article = form.toEntity();

        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return saved.getId();
    }

    @GetMapping("/api/articles/{id}")
    public ArticleForm getArticle(@PathVariable Long id){
        Article entity = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 Article이 없습니다."));
        return new ArticleForm(entity);
    }

    @PutMapping("/api/articles/{id}")
    public Long update(@PathVariable Long id, @RequestBody ArticleForm form){
        log.info("form: " + form.toString());
        return 0L;
    }
}
