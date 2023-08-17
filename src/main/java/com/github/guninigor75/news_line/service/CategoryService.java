package com.github.guninigor75.news_line.service;

import com.github.guninigor75.news_line.entity.Article;
import com.github.guninigor75.news_line.entity.Category;

import java.util.Optional;

public interface CategoryService {
    Optional<Category> findByName(String name);

    void createCategory(String name, Article article);
}
