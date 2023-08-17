package com.github.guninigor75.news_line.mapper;

import com.github.guninigor75.news_line.dto.ArticleDto;
import com.github.guninigor75.news_line.dto.CreateArticle;
import com.github.guninigor75.news_line.dto.PathArticle;
import com.github.guninigor75.news_line.entity.Article;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ArticleMapper {
    @Mapping(target = "createdAt", expression =  "java(java.time.LocalDateTime.now())")
    @Mapping(target = "category", expression = "java(null)")
    Article createArticleToArticle(CreateArticle createArticle);

    @Mapping(target = "category", source = "category.name")
    ArticleDto articleToArticleDto(Article article);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void path(@MappingTarget Article target, PathArticle source);
}
