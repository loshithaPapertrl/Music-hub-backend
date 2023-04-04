package com.papertrl.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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


    public Post(int userId, String postType, byte[] postContent, Date postDate) {
        this.userId = userId;
        this.postType = postType;
        this.postContent = postContent;
        this.postDate = postDate;
    }
}
