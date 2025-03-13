package com.kimh.spm.domain.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> { 
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);  
    List<User> findAll();
}