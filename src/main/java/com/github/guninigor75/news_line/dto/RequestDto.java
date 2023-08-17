package com.github.guninigor75.news_line.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestDto {

    private List<SearchRequestDto> searchRequestDtos;

    private GlobalOperator globalOperator;

    private PageRequestDto pageRequestDto;
    public enum GlobalOperator {
        AND,
        OR;
    }
}
