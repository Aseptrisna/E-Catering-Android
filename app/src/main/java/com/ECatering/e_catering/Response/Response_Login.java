package com.ECatering.e_catering.Response;

import com.ECatering.e_catering.Storage.User;

public class Response_Login {

    private boolean error;
    private String message;
    private User user;

    public Response_Login (boolean error, String message, User user) {
        this.error = error;
        this.message = message;
        this.user = user;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
