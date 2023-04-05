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
    public static final String SAVE_TALENTS = AUTH+"/save_talents_as_a_post";

    public static final String POST_REVIEW = AUTH+"/post_review";

    public static final String GET_POST_BY_USER_ID = AUTH+"/get_post_by_user_id";
    public static final String DELETE_POST = AUTH+"/delete_post";

    public static final String ADD_PROFILE_DETAILS = AUTH+"/add_profile_details";

}
