/*
 * 16/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.constant;

public class Messages {

    public interface SuccessMessage{
        String
                LOGGED_IN_SUCCESSFULLY = "User %s logged in successfully",
                SECURITY_CHECK_MESSAGE  = "Server is securer and logged in user is %s",
                PASSWORD_UPDATED = "Password updated successfully",
                ACCOUNT_CREATED_SUCCESSFULLY = "Account created successfully",
                LINK_SENT_FOR_RESET_PASSWORD = "An email has been sent on your registered email address. Follow the instructions in email to proceed.";
    }

    public interface ErrorMessage{
        String INTERNAL_ERROR = "Something went wrong",
                TOKEN_NOT_FOUND = "Token not found";
    }

    public interface ExceptionMessage{
        String INVALID_CREDENTIAL = "invalid username or password",
                TOKEN_EXPIRED = "Authentication token %s is expired",
                INVALID_EMAIL = "Invalid email id",
                INVALID_ROLE = "Invalid role id",
                USER_NOT_EXIST = "User does not exist",
                INACTIVE_USER = "Inactive User",
                INVALID_REQUEST = "Invalid Request",
                INTERNAL_ERROR = "Something went wrong",
                USER_EXIST = "User already exists with same emailId",
                USER_NOT_FOUND = "User not found",
                USER_OWNER_NOT_FOUND = "User owner not found",
                ROLE_NOT_FOUND = "Role not found",
                ACCESS_DENIED_FOR_ACCOUNT_CREATION_FOR_ROLE = "Sorry, You have not access to create account for this role",
                PASSWORD_AND_CONFIRM_PASSWORD_MISMATCH = "Password and confirm password provided do not match",
                INVALID_LINK = "The link to set password has expired or is no longer valid";
    }

    public interface LOG{
        String
                TIME_TAKEN = "Time Taken in %s is %s",
                LINK_SENT_FOR_RESET_PASSWORD = "Email sent for set password to %s";
    }
}
