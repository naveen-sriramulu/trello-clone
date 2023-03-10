package com.sample.trelloclone.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ColumnDto {
    private String name;
    private Set<CardDto> cards;
}
