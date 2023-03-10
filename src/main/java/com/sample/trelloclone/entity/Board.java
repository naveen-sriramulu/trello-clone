package com.sample.trelloclone.entity;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Builder
@Data
public class Board {
    private Long id;
    private String name;
    private Set<Column> columns;
    private Instant createdOn;
    private Instant updateOn;
}
