/*
 * 16/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.model.response;

public class LoginResponse {

    private String name;
    private String username;
    private String token;
    private String phoneNumber;
    private int roleId;
    private int userId;

    public LoginResponse() {
    }

    public LoginResponse(String name, String username, String token, String phoneNumber, int roleId, int userId) {
        this.name = name;
        this.username = username;
        this.token = token;
        this.phoneNumber = phoneNumber;
        this.roleId = roleId;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
