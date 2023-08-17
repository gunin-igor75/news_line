package com.github.guninigor75.news_line.dto;

import com.github.guninigor75.news_line.validation.ValidationCategoryName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateArticle {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

    @NotBlank
    @Size(min = 50)
    private String content;

    @ValidationCategoryName
    private String category;

}
