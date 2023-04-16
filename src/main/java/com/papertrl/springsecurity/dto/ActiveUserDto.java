package com.papertrl.springsecurity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActiveUserDto implements Serializable {

    private String role;

    private Boolean isActive;
}
