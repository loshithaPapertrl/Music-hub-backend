package com.papertrl.springsecurity.repository;

import com.papertrl.springsecurity.entity.UserTalentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserTalentCategoryRepository extends JpaRepository<UserTalentCategory,Integer> {

    @Query(value = "SELECT tc.userId FROM UserTalentCategory tc WHERE tc.categoryId=:categoryId")
    List<Integer> findUseridByCategoryId(Integer categoryId);
}
