package com.dryfruits.dryfruitsbackend.repository;

import com.dryfruits.dryfruitsbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "Select * from users where phone_no =:phoneNo", nativeQuery = true)
    User findByPhoneNo(String phoneNo);
}
