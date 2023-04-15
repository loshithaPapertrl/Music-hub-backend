package com.papertrl.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postDate;

    @Column(name = "about")
    private String about;

    @Column(name = "path")
    private String path;


    transient private List<Comment> comments;

    transient private String userName;

    transient private  byte[] profilePicture;

    transient private String postContentAsString;

    public Post(Integer id,Integer userId, String userName,String path, String about,byte[] postContent,byte[] profilePicture, Date postDate, String postType) {
        this.id = id;
        this.userId=userId;
        this.userName = userName;
        this.postType = postType;
        this.postContent = postContent;
        this.postDate = postDate;
        this.about = about;
        this.profilePicture=profilePicture;
        this.path=path;
    }
}
