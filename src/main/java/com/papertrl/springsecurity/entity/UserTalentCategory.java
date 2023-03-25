package com.papertrl.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_talent_category")
public class UserTalentCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "user_id")
    private Integer userId;

    @JoinColumn(name = "category_id")
    private Integer categoryId;

}

