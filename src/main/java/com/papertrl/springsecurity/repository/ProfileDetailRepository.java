package com.papertrl.springsecurity.repository;


import com.papertrl.springsecurity.entity.ProfileDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

public interface ProfileDetailRepository extends JpaRepository<ProfileDetail,Integer> {


    ProfileDetail findByUserId(Integer userId);
}
