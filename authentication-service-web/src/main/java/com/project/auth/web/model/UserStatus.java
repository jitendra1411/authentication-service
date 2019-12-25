/*
 * 28/6/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.model;

public enum UserStatus {

    INACTIVE(2),
    ACTIVE(1),
    PASSWORD_SET_PENDING(0);

    private int status;

    UserStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static UserStatus getUserStatus(int status) {
        for (UserStatus userStatus: values()) {
            if(userStatus.getStatus() == status) {
                return userStatus;
            }
        }
        return null;
    }
}
