package com.github.guninigor75.news_line.repository;

import com.github.guninigor75.news_line.entity.Category;
import com.github.guninigor75.news_line.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

}
