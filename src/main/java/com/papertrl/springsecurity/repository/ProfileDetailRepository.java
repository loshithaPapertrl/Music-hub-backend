package com.papertrl.springsecurity.repository;


import com.papertrl.springsecurity.entity.ProfileDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

public interface ProfileDetailRepository extends JpaRepository<ProfileDetail,Integer> {


    ProfileDetail findByUserId(Integer userId);

    @Query(value = "SELECT new com.papertrl.springsecurity.entity.ProfileDetail" +
            "(pd.id,pd.userId,u.artistName,pd.profilePicture,pd.about,pd.genres,pd.moods,pd.profession,pd.spotifyLink,pd.youtubeLink,pd.talentCategory) " +
            "FROM ProfileDetail pd " +
            "INNER JOIN User u ON pd.userId= u.id " +
            "WHERE pd.userId=:userId")
    ProfileDetail findByUserIdWithName(Integer userId);
}
