package com.github.guninigor75.news_line.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PathArticle {

    @Size(min = 3, max = 100)
    private String title;

    @Size(min = 10)
    private String content;

}
