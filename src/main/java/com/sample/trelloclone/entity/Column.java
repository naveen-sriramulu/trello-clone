package com.sample.trelloclone.entity;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Builder
@Data
public class Column {
    private Integer id;
    private String name;
    private Set<Card> cards;
    private Board board;
    private Instant createdOn;
    private Instant updateOn;
}
