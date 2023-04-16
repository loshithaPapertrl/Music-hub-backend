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

    @Query(value = "SELECT new com.papertrl.springsecurity.entity.User(u.id,pd.id,u.artistName, u.phoneNumber, pd.profilePicture)" +
            " FROM User u INNER JOIN ProfileDetail pd ON u.id= pd.userId " +
            "INNER JOIN TalentsCategory tc ON pd.talentCategory= tc.id WHERE tc.id=:categoryId")
    List<User> getUsersCategoryVise(Integer categoryId);

    @Query(value = "SELECT u.artistName FROM User u WHERE u.id=:id")
    String findArtistNameById(Integer id);


    @Query(value = "SELECT new com.papertrl.springsecurity.entity.User(u.id,u.artistName, u.phoneNumber,u.email,pd.profession,tc.categoryName,u.isActive)" +
            " FROM User u INNER JOIN ProfileDetail pd ON u.id= pd.userId " +
            "INNER JOIN TalentsCategory tc ON pd.talentCategory= tc.id")
    List<User> getUsersToAdminTable();

    @Query(value = "SELECT u FROM User u WHERE u.id=:userId ")
    User getUserById(Integer userId);

}
