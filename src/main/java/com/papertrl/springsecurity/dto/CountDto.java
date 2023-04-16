package com.papertrl.springsecurity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class CountDto implements Serializable {

    private Integer userCount;

    private Integer postCount;

    private Integer commentCount;
}
