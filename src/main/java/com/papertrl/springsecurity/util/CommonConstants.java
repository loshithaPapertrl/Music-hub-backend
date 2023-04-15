package com.papertrl.springsecurity.util;

import java.io.Serializable;

public class CommonConstants implements Serializable {


    //--------------- API ------------------------
    public static final String USER = "/user";
    public static final String AUTH = "/api/v1/auth";

    //-------------------------------------------------
    public static final String AUTHENTICATE =AUTH +"/authenticate";
    public static final String REGISTER = USER + "/register";
    public static final String ALL_TALENTS = USER+"/getAllTalentCategories";
    public static final String CATEGORY_VISE_USER_ID = USER+"/get_all_userid_category_vise";
    public static final String GET_REVIEW_USER_VISE_USER_ID = USER+"/get_user_vise_review";
    public static final String GET_REVIEW = AUTH+"/get_personal_review";
    public static final String GET_USERs_CATEGORY_VISE = USER+"/get_users_category_vise";

    public static final String GET_RATINGS_BY_VISITOR = USER+"/get_rating_by_visitor";
    public static final String SAVE_TALENTS = AUTH+"/save_talents_as_a_post";
    public static final String POST_REVIEW = AUTH+"/post_review";
    public static final String GET_POST_BY_USER_ID = USER+"/get_post_by_user_id";

    public static final String GET_PERSONAL_POST = AUTH+"/get_personal_post";
    public static final String DELETE_POST = AUTH+"/delete_post";
    public static final String ADD_PROFILE_DETAILS = AUTH+"/add_profile_details";

    public static final String UPDATE_PROFILE_DETAILS = AUTH+"/update_profile_details";
    public static final String GET_PROFILE_DETAILS = AUTH+"/get_profile_details";
    public static final String GET_PROFILE_DETAILS_BY_VISITOR = USER+"/get_profile_details_by_visitor";
    public static final String COMMENT_ON_A_POST = AUTH+"/comment_on_a_post";

    public static final String GET_AUDIOS_AS_POST = AUTH+"/get_audios_as_post";
    public static final String GET_RATINGS_OWN = AUTH+"/get_rating_by_own";

    public static final String POST_LOCAL = AUTH+"/upload-mp3";

}
