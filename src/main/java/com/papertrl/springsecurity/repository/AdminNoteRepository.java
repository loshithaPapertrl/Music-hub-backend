package com.papertrl.springsecurity.repository;

import com.papertrl.springsecurity.entity.AdminNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminNoteRepository extends JpaRepository<AdminNote,Integer> {


//    List<AdminNote> findAll();
}
