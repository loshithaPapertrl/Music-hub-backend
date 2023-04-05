package com.papertrl.springsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileDetailDto {

    private Integer id;

    private Integer userId;

    private MultipartFile profilePicture;

    private String profession;

    private String genres;

    private String moods;

    private String about;

    private String youtubeLink;

    private String spotifyLink;
}
