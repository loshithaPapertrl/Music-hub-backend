package com.papertrl.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "reviewer_name")
    private String reviewerName;

    @Column(name = "reviewed_user_id")
    private Integer reviewedUserId;

    @Column(name = "review_text")
    private String reviewText;

    @Column(name = "marks")
    private Integer marks;


}
