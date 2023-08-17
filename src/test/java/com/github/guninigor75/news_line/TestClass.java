package com.github.guninigor75.news_line;


import com.github.guninigor75.news_line.dto.PathArticle;
import com.github.guninigor75.news_line.entity.Article;
import com.github.guninigor75.news_line.entity.Category;
import com.github.guninigor75.news_line.entity.Type;
import com.github.guninigor75.news_line.mapper.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

public class TestClass {

    @Test
    void testMap() {
        PathArticle pathArticle = PathArticle.builder()
                .title("namewdewdwdedee")
                .content("33333333323232323232323")
                .build();

        Article article = new Article();
        article.setId(1L);
        article.setCategory(new Category(Type.SPORT));
        article.setCreatedAt(LocalDateTime.now());
        article.setTitle("NAME");
        article.setContent("AAAAAAAAAAAAA");
        ArticleMapper mapper = Mappers.getMapper(ArticleMapper.class);
        mapper.path(article, pathArticle);
        System.out.println(article);
    }
}
