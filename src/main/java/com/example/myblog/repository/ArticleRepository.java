package com.example.myblog.repository;

import com.example.myblog.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository  // DB와 소통하는 인터페이스, JPA가 해당 객체를 알아서 만듦!
    extends CrudRepository<Article, Long> {//<Article, Long> 의미? 관리 대상은 Article, 대상의 PK는 Long 타입!
}
