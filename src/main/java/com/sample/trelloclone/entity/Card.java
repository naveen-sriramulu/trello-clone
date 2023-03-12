package com.sample.trelloclone.entity;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Builder
@Data
public class Card {
    private Integer id;
    private String title;
    private String description;
    private Set<Label> labels;
    private User reportedBy;
    private User assignedTo;
    private Instant createdOn;
    private Instant updateOn;
}
