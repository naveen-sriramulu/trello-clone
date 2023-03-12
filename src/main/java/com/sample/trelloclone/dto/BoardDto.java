package com.sample.trelloclone.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class BoardDto {
    private String name;
    Set<ColumnDto> columns;
}
