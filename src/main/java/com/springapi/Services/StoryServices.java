package com.springapi.Services;

import com.springapi.Entity.Story;
import com.springapi.Repository.StoryRepository;
import com.springapi.Utils.EntityNotFoundException;
import com.springapi.Utils.StoryProtectedRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoryServices {
    @Autowired
    private StoryRepository storyRepository;
   @Autowired
    private StoryProtectedRoute storyProtectedRoute;
    public List<Story> getAllStories(){
        return storyRepository.findAll();
    }

    public Story getStory(Integer id){
        Optional<Story> story = storyRepository.findById(id);
        if (story.isEmpty()) throw new EntityNotFoundException(Story.class, "id", String.valueOf(id));
        return story.get();
    }


    public Story updateStory(Integer id, Story story){
        Optional<Story> existStory =  storyRepository.findById(id);
        if (existStory.isEmpty()){
            throw new EntityNotFoundException(Story.class, "id", String.valueOf(id));
        }
        storyProtectedRoute.checkValidUser(story.getAuthor());
        setUserProperties(existStory.get().getTitle(), story);
        return storyRepository.save(existStory.get());
    }


    protected void setUserProperties(String currentStory, Story story) {
        currentStory.setTitle(story.getTitle());
        currentStory.setDescription(story.getDescription());
    }


    public void deleteStory(Integer id){
        Optional<Story> story = storyRepository.findById(id);
        if (story.isEmpty()) throw new EntityNotFoundException(Story.class, "id", String.valueOf(id));
        storyRepository.deleteById(id);
    }


}
