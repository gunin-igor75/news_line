package com.github.guninigor75.news_line.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleDto {
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private String category;

}
