/*
 * 24/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.util;
import com.project.auth.model.request.AccountCreationRequest;
import com.project.auth.persistence.sql.entities.Role;
import com.project.auth.persistence.sql.entities.User;
import com.project.auth.persistence.sql.entities.UserCredential;
import com.project.auth.web.constant.Messages;
import com.project.auth.web.exception.AuthException;
import com.project.auth.web.model.UserStatus;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.UUID;

public class UserUtil {

    public static boolean isValidEmailId(String emailId){
        return EmailValidator.getInstance().isValid(emailId);
    }

    public static boolean isValidAccountCreationRequest(AccountCreationRequest accountCreationRequest){
        if(accountCreationRequest.getEmailId() == null || !isValidEmailId(accountCreationRequest.getEmailId())){
            throw new AuthException(Messages.ExceptionMessage.INVALID_EMAIL);
        }
        if(accountCreationRequest.getRoleId() == null){
            throw new AuthException(Messages.ExceptionMessage.INVALID_ROLE);
        }
        return true;
    }

    public static User userRequestToUserEntity(AccountCreationRequest accountCreationRequest){
        User user = new User();
        user.setPhoneNumber(accountCreationRequest.getPhoneNumber());
        user.setStatus(UserStatus.PASSWORD_SET_PENDING.getStatus());
        return user;
    }

    public static UserCredential getNewUserCredential(User user, String emailId, int tokenExpiryMinute){
        UserCredential userCredential = new UserCredential();
        userCredential.setUser(user);
        userCredential.setEmailId(emailId);
        userCredential.setPasswordToken(UUID.randomUUID().toString());
        userCredential.setPasswordTokenExpiry(System.currentTimeMillis() + tokenExpiryMinute * 60 * 1000);
        return userCredential;
    }

}
