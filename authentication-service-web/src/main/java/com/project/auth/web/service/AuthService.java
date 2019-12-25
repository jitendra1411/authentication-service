/*
 * 16/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.service;

import com.project.auth.model.request.LoginRequest;
import com.project.auth.model.request.UpdateCredentialRequest;
import com.project.auth.model.response.LoginResponse;
import com.project.auth.persistence.sql.entities.User;
import com.project.auth.persistence.sql.entities.UserCredential;
import com.project.auth.persistence.sql.entities.UserDetail;
import com.project.auth.persistence.sql.entities.UserRole;
import com.project.auth.web.constant.Messages;
import com.project.auth.web.constant.PropertyConstant;
import com.project.auth.web.constant.Users;
import com.project.auth.web.exception.AuthException;
import com.project.auth.web.security.TokenProvider;
import com.project.auth.web.security.UserDetails;
import com.project.auth.web.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AuthService extends BaseService{

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Value("${" + PropertyConstant.PASSWORD_RESET_TOKEN_TIME_INTERVAL_MINUTE+ "}")
    int passwordResetTokenTimeIntervalMinute;

    @Autowired
    private TokenProvider tokenProvider;

    public LoginResponse login(LoginRequest loginRequest){

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserEmailId(), loginRequest.getPassword())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setUsername(loginRequest.getUserEmailId());
        loginResponse.setUserId(userDetails.getUser().getUserId());
        UserRole userRole = userRoleRepository.findByUser(userDetails.getUser());
        UserCredential userCredential = userCredentialRepository.findByEmailId(loginRequest.getUserEmailId());
        UserDetail userDetail = userDetailRepository.findByUser(userCredential.getUser());

        loginResponse.setName(userDetail  == null ? "user" : userDetail.getName());
        loginResponse.setRoleId(userRole.getRole().getRoleId());
        loginResponse.setPhoneNumber(userDetails.getUser().getPhoneNumber());

        return loginResponse;
    }

    public boolean isPasswordTokenValid(String passwordToken) {
        if (null == passwordToken) {
            throw new AuthException(Messages.ExceptionMessage.INVALID_LINK);
        }
        UserCredential userCredential = userCredentialRepository.findByPasswordToken(passwordToken);
        if (null == userCredential) {
            throw new AuthException(Messages.ExceptionMessage.INVALID_LINK);
        }
        if (null == userCredential.getPasswordTokenExpiry()) {
            logger.error(String.format(Messages.ErrorMessage.TOKEN_NOT_FOUND));
            throw new AuthException(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        }
        if (userCredential.getPasswordTokenExpiry() < System.currentTimeMillis()) {
            logger.error(String.format(Messages.ExceptionMessage.TOKEN_EXPIRED, passwordToken));
            throw new AuthException(Messages.ExceptionMessage.INVALID_LINK);
        }
         return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updatePassword(UpdateCredentialRequest UpdateCredentialRequest) {
        if (UpdateCredentialRequest == null) {
            throw new AuthException(Messages.ExceptionMessage.INVALID_REQUEST);
        }
        return updatePassword(UpdateCredentialRequest.getSetPasswordToken(), UpdateCredentialRequest.getPassword(), UpdateCredentialRequest.getConfirmPassword());
    }

    public boolean updatePassword(String passwordToken, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new AuthException(Messages.ExceptionMessage.PASSWORD_AND_CONFIRM_PASSWORD_MISMATCH);
        }
        isPasswordTokenValid(passwordToken);
        UserCredential userCredential = userCredentialRepository.findByPasswordToken(passwordToken);
        userCredential.setPassword(passwordEncoder.encode(password));
        userCredential.setPasswordToken(null);
        userCredential.setPasswordTokenExpiry(null);
        User user = userCredential.getUser();
        user.setStatus(Users.AccountStatus.ACTIVATED);
        userCredentialRepository.save(userCredential);
        userRepository.save(user);
        logger.info(String.format(Messages.SuccessMessage.PASSWORD_UPDATED, userCredential.getEmailId()));
        return true;
    }

    public String resendPasswordLink(String emailId){
        if(!UserService.getInstance().isExistUser(emailId)){
            throw new AuthException(Messages.ExceptionMessage.USER_NOT_EXIST);
        }
        UserCredential userCredential = userCredentialRepository.findByEmailId(emailId);

        User user = userCredential.getUser();
        if(user.getStatus() == Users.AccountStatus.DEACTIVATED){
            throw new AuthException(Messages.ExceptionMessage.INACTIVE_USER);
        }

        userCredential.setPasswordToken(UUID.randomUUID().toString());
        userCredential.setPasswordTokenExpiry(System.currentTimeMillis() + passwordResetTokenTimeIntervalMinute *60*1000);
        userCredentialRepository.save(userCredential);
//        try{
//            EmailService.getInstance().sendPasswordSetMail(userCredential.getUser().getName(), emailId, userCredential.getSetPasswordToken());
//        }
//        catch (Exception e){
//            LOG.info(Messages.ExceptionMessages.ERROR_DURING_SENDING_MAIL);
//            throw new VEException(e);
//        }
        logger.info(String.format(Messages.LOG.LINK_SENT_FOR_RESET_PASSWORD, emailId));
        return userCredential.getPasswordToken();
    }
}
