/*
 * 24/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.service;

import com.project.auth.model.request.AccountCreationRequest;
import com.project.auth.persistence.sql.entities.*;
import com.project.auth.web.constant.Messages;
import com.project.auth.web.constant.PropertyConstant;
import com.project.auth.web.exception.AuthException;
import com.project.auth.web.util.UserUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends BaseService{

    private static UserService INSTANCE;

    public UserService(){
        INSTANCE = this;
    }
    public static UserService getInstance(){
        return INSTANCE;
    }


    @Value("${" + PropertyConstant.PASSWORD_RESET_TOKEN_TIME_INTERVAL_MINUTE + "}")
    int passwordResetTokenTimeIntervalMinute;

    public boolean isExistUser(String emailId) {
        UserCredential userCredential = userCredentialRepository.findByEmailId(emailId);
        return userCredential != null;
    }

    @Transactional
    public void createAccount(AccountCreationRequest accountCreationRequest){
        if(UserUtil.isValidAccountCreationRequest(accountCreationRequest)){
            String userEmailId = accountCreationRequest.getEmailId();
            if (isExistUser(userEmailId)) {
                throw new AuthException(Messages.ExceptionMessage.USER_EXIST);
            }
            User user = createUser(accountCreationRequest);
            createUserCredential(user.getUserId(), accountCreationRequest.getEmailId());
            createUserRole(user.getUserId(), accountCreationRequest.getRoleId());
            User loggedInUser = null;
            try{
                loggedInUser = getCurrentUserDetails().getUser();
            }
            catch (Exception e){

            }
            int ownerId = loggedInUser == null ? user.getUserId() : loggedInUser.getUserId();
            createUserOwner(user.getUserId(), ownerId);
        }
    }

    @Transactional
    public UserOwnerUser createUserOwner(int userId, int ownerId){
        User user = userRepository.findByUserId(userId);
        if(user == null){
            throw new AuthException(Messages.ExceptionMessage.USER_NOT_FOUND);
        }
        UserOwnerUser userOwnerUser = new UserOwnerUser(user, null);
        if(userId == ownerId){
            userOwnerUser.setUserOwner(user);
        }
        else {
            User userOwner = userRepository.findByUserId(ownerId);
            if(userOwner == null){
                throw new AuthException(Messages.ExceptionMessage.USER_OWNER_NOT_FOUND);
            }
            userOwnerUser.setUserOwner(userOwner);
        }
        userOwnerUser = userOwnerUserRepository.save(userOwnerUser);
        return userOwnerUser;
    }

    @Transactional
    public UserRole createUserRole(int userId, int roleId){
        User user = userRepository.findByUserId(userId);
        if(user == null){
            throw new AuthException(Messages.ExceptionMessage.USER_NOT_FOUND);
        }

        Role role = roleRepository.findByRoleId(roleId);
        if(role == null){
            throw new AuthException(Messages.ExceptionMessage.ROLE_NOT_FOUND);
        }
        UserRole userRole = new UserRole(user, role);
        userRole = userRoleRepository.save(userRole);
        return userRole;
    }

    @Transactional
    public UserCredential createUserCredential(int userId, String emailId){
        if(emailId == null || !UserUtil.isValidEmailId(emailId)){
            throw new AuthException(Messages.ExceptionMessage.INVALID_EMAIL);
        }
        User user = userRepository.findByUserId(userId);
        if(user == null){
            throw new AuthException(Messages.ExceptionMessage.USER_NOT_FOUND);
        }
        UserCredential userCredential = UserUtil.getNewUserCredential(user, emailId, passwordResetTokenTimeIntervalMinute);
        userCredential.setPassword("");
        return userCredentialRepository.save(userCredential);
    }

    @Transactional
    public User createUser(AccountCreationRequest accountCreationRequest){
        User user = UserUtil.userRequestToUserEntity(accountCreationRequest);
        user = userRepository.save(user);
        return user;
    }
}
