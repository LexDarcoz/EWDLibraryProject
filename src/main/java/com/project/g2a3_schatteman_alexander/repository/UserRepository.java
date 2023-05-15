package com.project.g2a3_schatteman_alexander.repository;


import com.project.g2a3_schatteman_alexander.entities.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

}

