package com.github.guninigor75.news_line.service.imp;

import com.github.guninigor75.news_line.entity.Article;
import com.github.guninigor75.news_line.entity.Category;
import com.github.guninigor75.news_line.entity.Type;
import com.github.guninigor75.news_line.handler_exception.CategoryException;
import com.github.guninigor75.news_line.repository.CategoryRepository;
import com.github.guninigor75.news_line.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    @Transactional
    public void createCategory(String name, Article article) {
        Optional<Category> categoryOrEmpty = findByName(name);
        if (categoryOrEmpty.isEmpty()) {
            Type type = Type.getCategories(name);
            Category category = new Category(type);
            category.addArticle(article);
            categoryRepository.save(category);
        } else {
            String message = String.format("Категория с таким именем %s существует", name);
            log.debug(message);
            throw new CategoryException(message);
        }
    }
}
