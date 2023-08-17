package com.github.guninigor75.news_line.service.imp;

import com.github.guninigor75.news_line.dto.*;
import com.github.guninigor75.news_line.entity.Article;
import com.github.guninigor75.news_line.entity.Category;
import com.github.guninigor75.news_line.handler_exception.ArticleException;
import com.github.guninigor75.news_line.mapper.ArticleMapper;
import com.github.guninigor75.news_line.repository.ArticleRepository;
import com.github.guninigor75.news_line.service.ArticleService;
import com.github.guninigor75.news_line.service.CategoryService;
import com.github.guninigor75.news_line.service.FilterSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleServiceImp implements ArticleService {

    private final ArticleRepository articleRepository;

    private final CategoryService categoryService;

    private final ArticleMapper articleMapper;

    private final FilterSpecification<Article> filterSpecification;

    @Override
    public Article getByTitle(String title) {
        return articleRepository.findByTitle(title).orElseThrow(() -> {
                    String message = String.format("Новость с таким именем %s не существует", title);
                    log.debug(message);
                    return new ArticleException(message);
                }
        );
    }

    @Override
    public Article findById(long id) {
        return articleRepository.findById(id).orElseThrow(() -> {
                    String message = String.format("Новость с таким id %d не существует", id);
                    log.debug(message);
                    return new ArticleException(message);
                }
        );
    }

    @Override
    @Transactional
    public ArticleDto createArticle(CreateArticle createArticle) {
        Article article = articleMapper.createArticleToArticle(createArticle);
        String name = createArticle.getCategory();
        Optional<Category> categoryOrEmpty = categoryService.findByName(name);
        Article persistentArticle;
        if (categoryOrEmpty.isPresent()) {
            Category category = categoryOrEmpty.get();
            article.setCategory(category);
            persistentArticle = articleRepository.save(article);
        } else {
            categoryService.createCategory(name, article);
            persistentArticle = getByTitle(article.getTitle());
        }
        return articleMapper.articleToArticleDto(persistentArticle);
    }

    @Override
    @Transactional
    public void deleteArticle(long id) {
        Article article = findById(id);
        articleRepository.delete(article);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Article> findAll(PageRequest page) {
        Page<Article> articles = articleRepository.findAll(page);
        return articles.getContent();
    }

    @Override
    @Transactional
    public ArticleDto updateArticle(Long id, PathArticle pathArticle) {
        Article article = findById(id);
        articleMapper.path(article, pathArticle);
        Article persistentArticle = articleRepository.save(article);
        return articleMapper.articleToArticleDto(persistentArticle);
    }

    @Override
    public Collection<Article> getArticles(RequestDto requestDto, Pageable pageable) {
        Specification<Article> specification =
                filterSpecification.getSearchSpecification(requestDto.getSearchRequestDtos(), requestDto.getGlobalOperator());
        Page<Article> all = articleRepository.findAll(specification, pageable);
        return all.getContent();
    }
}
