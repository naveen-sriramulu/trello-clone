package com.sample.trelloclone.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BoardDto {
    private String name;
    Set<ColumnDto> columns;
}
