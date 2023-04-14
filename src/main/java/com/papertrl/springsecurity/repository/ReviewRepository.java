package com.papertrl.springsecurity.repository;

import com.papertrl.springsecurity.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {


        @Query(value = "SELECT rv FROM Review rv WHERE rv.reviewedUserId=:userId")
        List<Review> findReviewByUserId(Integer userId);
        @Query(value = "SELECT SUM(rv.marks) FROM Review rv WHERE rv.reviewedUserId=:userId ")
        Integer getSumOfAllMarks(Integer userId);
        @Query(value = "SELECT COUNT (rv) FROM Review rv WHERE rv.reviewedUserId=:userId ")
        Integer getReviewCount(Integer userId);

}
