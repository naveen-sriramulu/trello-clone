package com.sample.trelloclone.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class CardDto {
    private String title;
//    private String column;
    private String description;
    private Set<String> labels;
    private String assignedTo;
    private String reportedBy;
    private String createdOn;
    private String updatedOn;
}
