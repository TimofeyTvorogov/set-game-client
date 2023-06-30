package com.example.client;


public class ServResponse {
    private boolean success;
    private Expn exception;
    private String nickname;
    private String accesstoken;


    protected class Expn {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Expn getException() {
        return exception;
    }

    public void setException(Expn exception) {
        this.exception = exception;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }
}

