package com.springapi.Controllers;

import com.springapi.DTO.StoryDTO;
import com.springapi.Entity.Story;
import com.springapi.Services.StoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${apiPrefix}/stories")
public class StoryController {
    @Autowired
    private StoryServices storyServices;

    @GetMapping
    public ResponseEntity<List<StoryDTO>> getAllStories(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize
    ){
        List<StoryDTO> stories = storyServices.getAllStories(pageNumber, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(stories);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StoryDTO> getStory(@PathVariable Integer id) {
       StoryDTO story = storyServices.getStory(id);
        return ResponseEntity.status(HttpStatus.OK).body(story);
    }

    @PostMapping
    public ResponseEntity<StoryDTO> createStory(@RequestBody Story story){
        StoryDTO newStory = storyServices.createStory(story);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStory);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<StoryDTO> updateStory(@PathVariable Integer id, @RequestBody Story story){
        StoryDTO updated = storyServices.updateStory(id, story);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Story> deleteStory(@PathVariable Integer id){
        storyServices.deleteStory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

