package com.springapi.Controllers;

import com.springapi.Entity.Story;
import com.springapi.Services.StoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "${apiPrefix}/stories")
public class StoryController {
    @Autowired
    private StoryServices storyServices;

    @GetMapping
    public ResponseEntity<Iterable<Story>> getAllStories(){
        Iterable<Story> stories = storyServices.getAllStories();
        return ResponseEntity.status(HttpStatus.OK).body(stories);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Story> getStory(@PathVariable Integer id) {
        Story story = storyServices.getStory(id);
        return ResponseEntity.status(HttpStatus.OK).body(story);
    }

    @PostMapping
    public ResponseEntity<Story> createStory(@RequestBody Story story){
        Story newStory = storyServices.createStory(story);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStory);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Story> updateStory(@PathVariable Integer id, @RequestBody Story story){
        Story updated = storyServices.updateStory(id, story);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Story> deleteStory(@PathVariable Integer id){
        storyServices.deleteStory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
}
