/*
 * 16/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.model.response;

public class GenericResponse<T> {

    private boolean successStatus;
    private String message;
    private T data;

    public GenericResponse() {
    }

    public GenericResponse(boolean successStatus, String message, T data) {
        this.successStatus = successStatus;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccessStatus() {
        return successStatus;
    }

    public void setSuccessStatus(boolean successStatus) {
        this.successStatus = successStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
