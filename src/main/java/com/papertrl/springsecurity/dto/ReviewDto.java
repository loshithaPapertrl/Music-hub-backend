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


    public ReviewDto(String reviewerName, Integer reviewedUserId, String reviewText) {
        this.reviewerName = reviewerName;
        this.reviewedUserId = reviewedUserId;
        this.reviewText = reviewText;
    }
}
