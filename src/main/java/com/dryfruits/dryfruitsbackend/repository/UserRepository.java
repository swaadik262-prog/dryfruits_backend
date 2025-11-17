package com.dryfruits.dryfruitsbackend.repository;

import com.dryfruits.dryfruitsbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
