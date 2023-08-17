package com.github.guninigor75.news_line.service;

import com.github.guninigor75.news_line.dto.RequestDto;
import com.github.guninigor75.news_line.dto.SearchRequestDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterSpecification<T> {

    public Specification<T> getSearchSpecification(SearchRequestDto searchRequestDto) {

        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
            }
        };
    }

    public Specification<T> getSearchSpecification(List<SearchRequestDto> searchRequestDtos, RequestDto.GlobalOperator globalOperator) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (SearchRequestDto requestDto : searchRequestDtos) {
                switch (requestDto.getOperator()) {
                    case EQUAL -> {
                        Predicate equal = criteriaBuilder.equal(root.get(requestDto.getColumn()), requestDto.getValue());
                        predicates.add(equal);
                    }
                    case LIKE -> {
                        Predicate like = criteriaBuilder.equal(root.get(requestDto.getColumn()), "%" + requestDto.getValue() + "%");
                        predicates.add(like);
                    }
                    case IN -> {
                        String[] split = requestDto.getValue().split(",");
                        Predicate in = root.get(requestDto.getColumn()).in(List.of(split));
                        predicates.add(in);
                    }
                    case JOIN -> {
                        Predicate join = criteriaBuilder.equal(root.join(requestDto.getJoinTable()), requestDto.getValue());
                        predicates.add(join);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + requestDto.getOperator());
                }
            }
            if (globalOperator.equals(RequestDto.GlobalOperator.AND)) {
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            } else {
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
