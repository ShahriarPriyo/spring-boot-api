package com.springapi.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoryDTO {
    private int id;
    private String title;
    private String description;
    private String authorname;
    private LocalDateTime createdAt;
}
