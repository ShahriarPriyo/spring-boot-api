package com.springapi.ServiceTest;

import com.springapi.Converter.StoryConverter;
import com.springapi.DTO.StoryDTO;
import com.springapi.Entity.Story;
import com.springapi.Repository.StoryRepository;
import com.springapi.Services.StoryServices;
import com.springapi.Utils.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StoryServiceTest {
    @Autowired
    private StoryServices storyService;
    @Mock
    private StoryRepository storyRepository;

    @MockBean
    private StoryConverter storyConverter;

    @Test
    @DisplayName("GET Test for story -Success")
    void testGetAllStory(){
        StoryDTO mockstoryDto1 = new StoryDTO(1,"updated title test  fdfd  sdsdsd erwerer 01", "updated jhausjjhjka dfdffg dfdfdfdf cffgf fddfdfgdf dfdfdfdf rtrrttr", "priyo98@gmail.com");
        StoryDTO mockstoryDto2 = new StoryDTO(2,"updated title test  fdfd  sdsdsd erwerer 01", "updated jhausjjhjka dfdffg dfdfdfdf cffgf fddfdfgdf dfdfdfdf rtrrttr", "priyo98@gmail.com");
        Story mockstory1 = new Story(1,"updated title test  fdfd  sdsdsd erwerer 01", "updated jhausjjhjka dfdffg dfdfdfdf cffgf fddfdfgdf dfdfdfdf rtrrttr");
        Story mockstory2 = new Story(2,"updated title test  fdfd  sdsdsd erwerer 01", "updated jhausjjhjka dfdffg dfdfdfdf cffgf fddfdfgdf dfdfdfdf rtrrttr");

        doReturn(Arrays.asList(mockstoryDto1,mockstoryDto2)).when(storyConverter).listStoryDTO(Arrays.asList(mockstory1,mockstory2));

        storyService.getAllStories(0,6);
        List<Story> allStory = Arrays.asList(mockstory1,mockstory2);
        List<StoryDTO> stories = storyConverter.listStoryDTO(allStory);
        int counter = 0;
        for (Object i : stories) {
            counter++;
        }
        Assertions.assertEquals(2, counter, "Get All Story");


    }

    @Test
    @DisplayName("GET Story find by id - Success")
    void testGetStoryByIdSuccess() {
        StoryDTO mockstoryDto = new StoryDTO(1,"updated title test  fdfd  sdsdsd erwerer 01", "updated jhausjjhjka dfdffg dfdfdfdf cffgf fddfdfgdf dfdfdfdf rtrrttr", "priyo98@gmail.com");
        Story mockstory = new Story(1,"updated title test  fdfd  sdsdsd erwerer 01", "updated jhausjjhjka dfdffg dfdfdfdf cffgf fddfdfgdf dfdfdfdf rtrrttr");
        doReturn(Optional.of(mockstory)).when(storyRepository).findById(1);

        StoryDTO returnedStory = storyService.getStory(1);
        System.out.println(returnedStory);
        //Assertions.assertNotNull(returnedStory);
    }

    @Test
    @DisplayName("GET Story find by id - Not Found")
    void testStoryFindByIdNotFound() {
        StoryDTO mockstoryDto = new StoryDTO(1,"updated title test  fdfd  sdsdsd erwerer 01", "updated jhausjjhjka dfdffg dfdfdfdf cffgf fddfdfgdf dfdfdfdf rtrrttr", "priyo98@gmail.com");
        Story mockstory = new Story(1,"updated title test  fdfd  sdsdsd erwerer 01", "updated jhausjjhjka dfdffg dfdfdfdf cffgf fddfdfgdf dfdfdfdf rtrrttr");

        when(storyRepository.findById(100))
                .thenThrow(new EntityNotFoundException(StoryServiceTest.class," Id ", String.valueOf(100)));

        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> storyService.getStory(100));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Story not found with id 100";
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("GET user email get All Story - Success")
    void testGetAllStoryByEmailSuccess() {
        StoryDTO mockstoryDto1 = new StoryDTO(1,"hello 01", "hjashalallkdaldlnadd", "priyo98@gmail.com");
        StoryDTO mockstoryDto2 = new StoryDTO(2,"hello 01", "hjashalallkdaldlnadd", "priyo98@gmail.com");
        doReturn(Arrays.asList(mockstoryDto1, mockstoryDto2)).when(storyRepository).findAllByauthorEmail("priyo98@gmail.com");

        List<StoryDTO> allStory = storyService.getAllStories(1,1);

        Assertions.assertNotNull(allStory);
    }

    @Test
    @DisplayName("GET user email get All Story - Not Found")
    void testGetAllStoryByEmailNotFound (){
        when(storyRepository.findAllByauthorEmail("priyo98@gmail.com"))
                .thenThrow(new EntityNotFoundException(StoryServiceTest.class," Email ","priyo98@gmail.com"));

        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> storyService.getAllStories(1,1));
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        String expectedMessage = "Story not found with Email priyo98@gmail.com";
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("POST Create Story - Success")
    void testCreateStory(){
        StoryDTO mockstoryDto = new StoryDTO(1,"updated title test  fdfd  sdsdsd erwerer 01", "updated jhausjjhjka dfdffg dfdfdfdf cffgf fddfdfgdf dfdfdfdf rtrrttr", "priyo98@gmail.com");
        Story mockstory = new Story();

        doReturn(mockstoryDto).when(storyRepository).save(mockstory);

//        StoryService mockStoryService = mock(StoryService.class);
//        mockStoryService.createStory(mockstory);
//
//        verify(mockStoryService, times(1)).createStory(mockstory);
    }

    @Test
    @DisplayName("DELETE Story delete - Not Found")
    void testStoryDeleteNotFound(){
        when(storyRepository.findById(100))
                .thenThrow(new EntityNotFoundException(StoryServiceTest.class," Id ","100"));

        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> storyService.deleteStory(100));
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        String expectedMessage = "Story not found with id 100";
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
