package com.duongkk.voice.apis;

/**
 * Created by MyPC on 9/12/2016.
 */
public class ApiConstants {

    public static final String API_ROOT="http://sandbox.api.simsimi.com/";
    public static final String API_GET_ANSWER="request.p";


    //CODE
    public static final int CODE_SUCESS = 200;
    public static final int CODE_ERROR_PARAM = 404;
    public static final int CODE_ERROR_SERVER = 500;
    public static final int CODE_ERROR_ACTIVED = 400;
    public static final int CODE_ERROR_LOGIN_PARAM = 300;
    public static final int CODE_ERROR_NO_EXIST_ACC = 404;
    public static final int CODE_ERROR_ALREADY_ACTIVE = 100;
    public static final int CODE_ERROR_LOCKED_ACC = 101;
    public static final int CODE_DEVICE_ANDROID = 1;
    public static final int CODE_CUSTOMER = 2;
    public static final int CODE_FINDING = 0;
    public static final int CODE_BIDED = 1;
    public static final int CODE_FINISH = 2;
    public static final int CODE_DECLINE = -1;
    public static final int CODE_TIME_OUT = -2;


    //LOGIN
    public static final String KEY_PHONE_NUMBER="phone_number";
    public static final String KEY_PASSWORD="password";
    public static final String KEY_DEVICE_TOKEN="device_token";
    public static final String KEY_TYPE_DEVICE="type_device";
    public static final String KEY_TYPE_USER="type_user";


    //ACCESSTOKEN

    public static final String KEY_USERNAME="UserName";
    public static final String KEY_PASSWORD_ACCESSTOKEN="Password";
    public static final String KEY_LANGUAGE="Language";
    public static final String KEY_SITE="Site";

    //STORE
    public static final String KEY_ACCESSTOKEN="AccessToken";
    public static final String KEY_PAGESIZE ="PageSize";
    public static final String KEY_PAGEINDEX="PageIndex";
    public static final String KEY_QUERY="Query";
    //PRODUCT
    public static final String KEY_CODE="Code";


    public static final String KEY_COLLECTION = "Collection";
    public static final String KEY_COLOR = "Color";
    public static final String KEY_STYLE = "Style";
    public static final String KEY_SIZE = "Size";

    public static final String KEY_IMAGE_SIZE = "ImageWidth";

    public static final String KEY_PRODUCT_CODE="ProductCode";


    public static final String KEY_MODE = "Mode";
    public static final String KEY_AUTHTOKEN="AuthenticatedAccessToken";

}
