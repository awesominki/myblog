package com.example.myblog.controller;

import com.example.myblog.dto.ArticleForm;
import com.example.myblog.entity.Article;
import com.example.myblog.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; //로깅 기능 추가! Lombok 플러그인 설치 필요!
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleRepository articleRepository;
    @GetMapping("/articles")
    public String index(Model model){
        Iterable<Article> articleList = articleRepository.findAll();

        model.addAttribute("articles",articleList);

        return "articles/index";
    }

    @GetMapping("/articles/new")
    public String newArticle(){
        return "articles/new";
    }

    @PostMapping("/articles") //Post방식은 "/articles" 요청이 들어오, 아래 메소 수행!
    public String create(ArticleForm form){ // 폼 태그 데이터가 ArtileForm 객체로 만들어 짐 !
        log.info(form.toString()); //ArticleForm 객체 정보를 확인!
        return "redirect:/articles"; // 브라우저를 "/articles" url로 보냄!
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        Article article = articleRepository.findById(id).orElse(null);

        model.addAttribute("article",article);
        return "articles/show";
    }

    @GetMapping("/articles/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Article target = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 Article이 없습니다.")
        );
        model.addAttribute("article", target);
        return "articles/edit";
    }
}