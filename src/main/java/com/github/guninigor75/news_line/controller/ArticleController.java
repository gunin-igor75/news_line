package com.github.guninigor75.news_line.controller;

import com.github.guninigor75.news_line.dto.*;
import com.github.guninigor75.news_line.entity.Article;
import com.github.guninigor75.news_line.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
@Tag(name = "ArticleController", description = "Создание, удаление, изменение поиск новостей")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDto createArticle(@Valid @RequestBody CreateArticle createArticle) {
        return articleService.createArticle(createArticle);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteArticle(@PathVariable("id") long id) {
        articleService.deleteArticle(id);
    }

    @Operation(
            summary = "Получение всех новостей",
            description = "Возвращает коллекцию новостей по-странично",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Article> getArticles() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return articleService.findAll(page);
    }

    @Operation(
            summary = "Изменение новости",
            description = "Изменяет поля новости(название и соддержание)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArticleDto updateArticle(@PathVariable("id") Long id,
                                    @Valid @RequestBody PathArticle pathArticle) {

        return articleService.updateArticle(id, pathArticle);
    }

    @PostMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Article> getArticlesFilter(@RequestBody RequestDto requestDto) {
        Pageable pageable= new PageRequestDto().getPageable(requestDto.getPageRequestDto());
        return articleService.getArticles(requestDto, pageable);
    }
}
