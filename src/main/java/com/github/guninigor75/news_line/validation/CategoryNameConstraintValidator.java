package com.github.guninigor75.news_line.validation;

import com.github.guninigor75.news_line.entity.Type;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CategoryNameConstraintValidator implements ConstraintValidator<ValidationCategoryName, String> {

    private Set<String> categoriesName;


    @Override
    public void initialize(ValidationCategoryName constraintAnnotation) {
        categoriesName = Stream.of(constraintAnnotation.categories()).map(Enum::name).collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String category, ConstraintValidatorContext constraintValidatorContext) {
        return categoriesName.contains(category.toUpperCase());
    }
}
