package com.ECatering.e_catering.Response;

import com.google.gson.annotations.SerializedName;

public class Response_Register {


    @SerializedName("error")
    private boolean err;

    @SerializedName("message")
    private String msg;

    public Response_Register(boolean err, String msg){
        this.err = err;
        this.msg = msg;
    }

    public boolean isErr(){
        return err;
    }

    public String getMsg(){
        return msg;
    }
}
