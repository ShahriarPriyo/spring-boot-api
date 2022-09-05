package com.springapi.Repository;

import com.springapi.Entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Users,Integer> {
    public Optional<Users> findByEmail(String email);
}
