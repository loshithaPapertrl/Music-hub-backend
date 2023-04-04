package com.papertrl.springsecurity.repository;

import com.papertrl.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findAllByEmail(String email);

    User findByEmail(String email);

    @Query(value = "SELECT u.artistName FROM User u WHERE u.email=:email")
    String findArtistNameByEmail(String email);

    @Query(value = "SELECT u.id FROM User u WHERE u.email=:email")
    Integer findArtistIdByEmail(String email);

}
