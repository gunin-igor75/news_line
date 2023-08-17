package com.github.guninigor75.news_line.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {
    private String column;
    private String value;
    private String joinTable;
    private Operator operator;



    public enum Operator{
        EQUAL,
        LIKE,
        IN,
        JOIN;
    }
}

