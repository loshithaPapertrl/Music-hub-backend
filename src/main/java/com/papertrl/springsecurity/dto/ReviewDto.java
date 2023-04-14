package com.papertrl.springsecurity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ReviewDto implements Serializable {


    private String reviewerName;

    private Integer reviewedUserId;

    private String reviewText;

    private Integer marks;


    public ReviewDto(String reviewerName,Integer marks, Integer reviewedUserId, String reviewText) {
        this.reviewerName = reviewerName;
        this.reviewedUserId = reviewedUserId;
        this.reviewText = reviewText;
        this.marks= marks;
    }
}
