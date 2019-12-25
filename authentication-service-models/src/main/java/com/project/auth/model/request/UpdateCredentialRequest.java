/*
 * 24/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.model.request;

public class UpdateCredentialRequest {

    private String setPasswordToken;
    private String password;
    private String confirmPassword;

    public String getSetPasswordToken() {
        return setPasswordToken;
    }

    public void setSetPasswordToken(String setPasswordToken) {
        this.setPasswordToken = setPasswordToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
