package com.duongkk.voice.apis;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MyPC on 9/12/2016.
 */
public class ApiResponse{
    @SerializedName("message")
    String message;
    @SerializedName("errorId")
    int status;
    @SerializedName("response")
    String data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
