package com.papertrl.springsecurity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class PostDto implements Serializable {

    private Integer userId;

    private String postType;

    private MultipartFile postContent;

    private String about;

    private Date postDate;
}
