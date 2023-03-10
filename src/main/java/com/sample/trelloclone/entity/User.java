package com.sample.trelloclone.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class User {
    private Integer id;
    private String name;
    @JsonIgnore
    private Instant lastActiveOn;
    private List<Board> boards;
}
