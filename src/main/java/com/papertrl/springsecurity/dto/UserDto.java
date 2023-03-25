package com.papertrl.springsecurity.dto;

import com.papertrl.springsecurity.util.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private String firstName;
    private String lastName;
    private String artistName;
    private Gender gender;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;

}
