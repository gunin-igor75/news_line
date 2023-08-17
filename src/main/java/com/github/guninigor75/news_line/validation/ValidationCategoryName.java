package com.github.guninigor75.news_line.validation;

import com.github.guninigor75.news_line.entity.Type;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CategoryNameConstraintValidator.class)
public @interface ValidationCategoryName {

    Type[] categories() default  {Type.CULTURE, Type.ECONOMIC, Type.INTERNET, Type.SPORT};

    String message() default "Категория не допустима ${validatedValue} можеть быть: {categories}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
