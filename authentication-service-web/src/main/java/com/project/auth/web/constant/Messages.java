/*
 * 16/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.constant;

public class Messages {

    public interface SuccessMessage{
        String LOGGED_IN_SUCCESSFULLY = "User %s logged in successfully";
    }

    public interface ErrorMessage{
        String INTERNAL_ERROR = "Something went wrong";
    }

    public interface ExceptionMessage{
        String INVALID_CREDENTIAL = "invalid username or password";
    }

    public interface LOG{
        String TIME_TAKEN = "Time Taken in %s is %s";
    }
}
