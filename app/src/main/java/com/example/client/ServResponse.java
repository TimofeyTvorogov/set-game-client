package com.example.client;


public class ServResponse {
    private boolean success;
    private Expn expn;



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

    public Expn getExpn() {
        return expn;
    }

    public void setExpn(Expn expn) {
        this.expn = expn;
    }
}

