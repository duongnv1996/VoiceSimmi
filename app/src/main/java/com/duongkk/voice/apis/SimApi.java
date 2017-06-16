package com.duongkk.voice.apis;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by MyPC on 9/12/2016.
 */
public interface SimApi {
    @GET(ApiConstants.API_GET_ANSWER)
    Call<ApiResponse> getAnswer(@Query("key") String key, @Query("lc") String lc, @Query("ft") float ft, @Query("text") String text);

}
