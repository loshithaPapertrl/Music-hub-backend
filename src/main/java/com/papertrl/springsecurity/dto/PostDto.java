package com.papertrl.springsecurity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class PostDto implements Serializable {

    private Integer userId;

    private String postType;

    private MultipartFile postContent;

    private Date postDate;
}
