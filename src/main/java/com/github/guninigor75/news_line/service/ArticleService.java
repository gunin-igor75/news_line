package com.github.guninigor75.news_line.service;

import com.github.guninigor75.news_line.dto.ArticleDto;
import com.github.guninigor75.news_line.dto.CreateArticle;
import com.github.guninigor75.news_line.dto.PathArticle;
import com.github.guninigor75.news_line.dto.RequestDto;
import com.github.guninigor75.news_line.entity.Article;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface ArticleService {
    Article getByTitle(String title);

    Article findById(long id);

    ArticleDto createArticle(CreateArticle createArticle);

    void deleteArticle(long id);

    Collection<Article> findAll(PageRequest page);

    @Transactional
    ArticleDto updateArticle(Long id, PathArticle pathArticle);

    Collection<Article> getArticles(RequestDto requestDto, Pageable pageable);
}
