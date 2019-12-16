/*
 * 16/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.model.request;

public class LoginRequest {

    private String userEmailId;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String userEmailId, String password) {
        this.userEmailId = userEmailId;
        this.password = password;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
