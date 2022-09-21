package com.springapi.Repository;
import com.springapi.Entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StoryRepository extends JpaRepository<Story,Integer> {

}
