package com.springapi.Services;

import com.springapi.Converter.StoryConverter;
import com.springapi.DTO.StoryDTO;
import com.springapi.Entity.Story;
import com.springapi.Entity.Users;
import com.springapi.Repository.StoryRepository;
import com.springapi.Utils.CreateStoryRouteProtection;
import com.springapi.Utils.EntityNotFoundException;
import com.springapi.Utils.StoryProtectedRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class StoryServices {
    @Autowired
    private StoryRepository storyRepository;
   @Autowired
    private StoryProtectedRoute storyProtectedRoute;
   @Autowired
   private CreateStoryRouteProtection createStoryRouteProtection;
   @Autowired
   private StoryConverter storyConverter;
    public List<StoryDTO> getAllStories(Integer pageNumber,Integer pageSize){
        Pageable pageableObj =  PageRequest.of(pageNumber,pageSize);
        Page<Story> storyPage= storyRepository.findAll(pageableObj);
        List<Story> allStory = storyPage.getContent();
        return storyConverter.listStoryDTO(allStory);
    }

    public StoryDTO createStory(Story story){
        Users storyauthor = createStoryRouteProtection.checkUserValidation();
        story.setAuthor(storyauthor);
        return storyConverter.entityDTO(storyRepository.save(story));
    }

    public StoryDTO getStory(Integer id){
        Optional<Story> story = storyRepository.findById(id);
        if (story.isEmpty()) throw new EntityNotFoundException(Story.class, "id", String.valueOf(id));
        return storyConverter.entityDTO(story.get());
    }


    public StoryDTO updateStory(Integer id, Story story){
        Optional<Story> storyObj =  storyRepository.findById(id);
        if (storyObj.isEmpty()){
            throw new EntityNotFoundException(Story.class, "id", String.valueOf(id));
        }
        storyProtectedRoute.checkValidUser(storyObj.get().getAuthor().getId());
        setUserProperties(storyObj.get(), story);
        return storyConverter.entityDTO(storyRepository.save(storyObj.get()));
    }


    protected void setUserProperties(Story currentStory, Story story) {
        currentStory.setTitle(story.getTitle());
        currentStory.setDescription(story.getDescription());
    }


    public void deleteStory(Integer id){
        Optional<Story> story = storyRepository.findById(id);
        if (story.isEmpty()) throw new EntityNotFoundException(Story.class, "id", String.valueOf(id));
        storyProtectedRoute.checkValidUser(story.get().getAuthor().getId());
        storyRepository.deleteById(id);
    }



}
