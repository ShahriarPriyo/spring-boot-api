package com.springapi.Converter;

import com.springapi.DTO.StoryDTO;
import com.springapi.Entity.Story;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class StoryConverter {
    public StoryDTO entityDTO(Story story){
        StoryDTO dto = new StoryDTO();
        dto.setId(story.getId());
        dto.setTitle(story.getTitle());
        dto.setDescription(story.getDescription());
        dto.setAuthorname(story.getAuthor().getEmail());
        dto.setCreatedAt(story.getCreated_At());
        return dto;
    }

    public Iterable<StoryDTO> iterableStoryDTO(Iterable<Story> stories){
        return StreamSupport
                .stream(stories.spliterator(),false)
                .toList()
                .stream()
                .map(this::entityDTO)
                .collect(Collectors.toList());
    }
    public List<StoryDTO> listStoryDTO(List<Story> stories){
        return stories.stream().map(this::entityDTO).collect(Collectors.toList());
    }
}
