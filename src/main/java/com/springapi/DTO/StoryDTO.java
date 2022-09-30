package com.springapi.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StoryDTO {
    private int id;
    private String title;
    private String description;
    private String authorname;
    private String email;
    private LocalDateTime createdAt;

    public StoryDTO(String title,String description){
        this.title=title;
        this.description=description;
    }

    public StoryDTO(int id,String title,String description,String email){
        this.id=id;
        this.title=title;
        this.description=description;
        this.email=email;
    }
}
