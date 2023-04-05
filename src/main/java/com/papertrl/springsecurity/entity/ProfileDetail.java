package com.papertrl.springsecurity.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "profile_detail")
public class ProfileDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Lob
    @Column(name = "profile_picture")
    private byte[] profilePicture;

    @Column(name = "profession")
    private String profession;

    @Column(name = "genres")
    private String genres;

    @Column(name = "moods")
    private String moods;

    @Column(name = "about")
    private String about;

    @Column(name = "youtube_link")
    private String youtubeLink;

    @Column(name = "spotify_link")
    private String spotifyLink;

}
