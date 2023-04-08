package com.papertrl.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "post")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "post_type")
    private String postType;

    @Lob
    @Column(name = "post_content")
    private byte[] postContent;

    @Column(name = "post_date")
    private Date postDate;

    @Column(name = "about")
    private String about;

    transient private List<Comment> comments;

    transient private String userName;

    transient private  byte[] profilePicture;


    public Post(Integer id,Integer userId, String userName, String about,byte[] postContent,byte[] profilePicture, Date postDate, String postType) {
        this.id = id;
        this.userId=userId;
        this.userName = userName;
        this.postType = postType;
        this.postContent = postContent;
        this.postDate = postDate;
        this.about = about;
        this.profilePicture=profilePicture;
    }
}
