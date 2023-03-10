package com.sample.trelloclone.entity;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class Label {
    private Integer id;
    private String tag;
    private Instant createdOn;
    private Instant updateOn;
}
