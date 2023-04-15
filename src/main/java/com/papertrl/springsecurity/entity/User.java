package com.papertrl.springsecurity.entity;

import com.papertrl.springsecurity.util.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "artist_name")
    private String artistName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    private transient byte[] profilePicture;

    private transient Integer profileId;

    private transient String profession;

    private transient String categoryName;

    public User(Integer id,Integer profileId, String artistName, String phoneNumber, byte[] profilePicture) {
        this.id = id;
        this.profileId= profileId;
        this.artistName = artistName;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
    }


    /**
     * this method use for admin table
     * @param id
     * @param artistName
     * @param phoneNumber
     * @param email
     * @param profession
     * @param categoryName
     */
    public User(Integer id,String artistName, String phoneNumber, String email, String profession,String categoryName) {
        this.id = id;
        this.artistName= artistName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profession = profession;
        this.categoryName = categoryName;
    }

}
