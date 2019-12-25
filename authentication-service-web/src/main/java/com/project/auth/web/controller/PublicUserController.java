/*
 * 25/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.controller;

import com.project.auth.model.request.AccountCreationRequest;
import com.project.auth.model.response.GenericResponse;
import com.project.auth.web.constant.Messages;
import com.project.auth.web.constant.UserConstant;
import com.project.auth.web.exception.AuthException;
import com.project.auth.web.model.Role;
import com.project.auth.web.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/public/user")
public class PublicUserController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(PublicUserController.class);

    @PostMapping
    public ResponseEntity<GenericResponse> create(@RequestBody AccountCreationRequest accountCreationRequest){
        GenericResponse genericResponse = new GenericResponse(true, Messages.SuccessMessage.ACCOUNT_CREATED_SUCCESSFULLY, null);

        try {
            if(UserUtil.isValidAccountCreationRequest(accountCreationRequest)){
                if(accountCreationRequest.getRoleId() != Role.USER.getOrdinal()){
                    throw new AuthException(Messages.ExceptionMessage.ACCESS_DENIED_FOR_ACCOUNT_CREATION_FOR_ROLE);
                }
                userService.createAccount(accountCreationRequest);
            }

        }
        catch (AuthException ae){
            genericResponse.setMessage(ae.getMessage());
            genericResponse.setSuccessStatus(false);
        }
        catch (Exception e){
            genericResponse.setMessage(Messages.ExceptionMessage.INTERNAL_ERROR);
            genericResponse.setSuccessStatus(false);
            logger.error(e.getMessage(), e.fillInStackTrace());
        }
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }
}
