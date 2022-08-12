package com.example.megha.model;


public class Response {

    private Object body;
    private  String message;
    private  boolean success;

    public Response() {
    }

    public Response(Object body, String message, boolean success) {
        this.body = body;
        this.message = message;
        this.success = success;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

