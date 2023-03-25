package com.papertrl.springsecurity.repository;

import com.papertrl.springsecurity.entity.TalentsCategory;
import com.papertrl.springsecurity.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalentCategoryRepository extends CrudRepository<TalentsCategory,Integer> {

    List<TalentsCategory> findAll();
}
