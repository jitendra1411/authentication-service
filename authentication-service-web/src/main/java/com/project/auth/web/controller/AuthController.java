/*
 * 16/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.controller;


import com.google.common.base.Stopwatch;
import com.project.auth.model.request.LoginRequest;
import com.project.auth.model.request.UpdateCredentialRequest;
import com.project.auth.model.response.GenericResponse;
import com.project.auth.model.response.LoginResponse;
import com.project.auth.web.constant.APIS;
import com.project.auth.web.constant.Messages;
import com.project.auth.web.constant.Users;
import com.project.auth.web.exception.AuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthController extends BaseController{

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<GenericResponse> login(@RequestBody LoginRequest loginRequest){

        Stopwatch timer = Stopwatch.createStarted();
        GenericResponse genericResponse = new GenericResponse(true, null, null);
        try {
            LoginResponse loginResponse = authService.login(loginRequest);
            genericResponse.setData(loginResponse);
        }
        catch (AuthException ae){
            genericResponse.setSuccessStatus(false);
            genericResponse.setMessage(ae.getMessage());
        }
        catch (Exception e){
            genericResponse.setSuccessStatus(false);
            genericResponse.setMessage(e.getMessage());
            LOG.error(e.getMessage(), e.fillInStackTrace());
        }

        LOG.debug(String.format(Messages.LOG.TIME_TAKEN, APIS.API.LOGIN, timer.stop()));
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/password/validate")
    public ResponseEntity<GenericResponse> validatePasswordToken(@RequestParam(value = "token") String token){
        Stopwatch timer = Stopwatch.createStarted();
        GenericResponse genericResponse = new GenericResponse(true, null, null);
        try {
            boolean validToken = authService.isPasswordTokenValid(token);
            genericResponse.setData(validToken);
        }
        catch (AuthException ae){
            genericResponse.setSuccessStatus(false);
            genericResponse.setMessage(ae.getMessage());
        }
        catch (Exception e){
            genericResponse.setSuccessStatus(false);
            genericResponse.setMessage(Messages.ExceptionMessage.INTERNAL_ERROR);
            LOG.error(e.getMessage(), e.fillInStackTrace());
        }

        LOG.debug(String.format(Messages.LOG.TIME_TAKEN, APIS.API.LOGIN, timer.stop()));
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }


    @PostMapping(value = "/password/resend")
    public ResponseEntity<GenericResponse> resendPasswordLink(@RequestBody String emailId){
        Stopwatch timer = Stopwatch.createStarted();
        GenericResponse genericResponse = new GenericResponse(true, Messages.SuccessMessage.LINK_SENT_FOR_RESET_PASSWORD, null);
        try {
            String token = authService.resendPasswordLink(emailId);
            genericResponse.setData(token);
        }
        catch (AuthException ae){
            genericResponse.setSuccessStatus(false);
            genericResponse.setMessage(ae.getMessage());
        }
        catch (Exception e){
            genericResponse.setSuccessStatus(false);
            genericResponse.setMessage(Messages.ExceptionMessage.INTERNAL_ERROR);
            LOG.error(e.getMessage(), e.fillInStackTrace());
        }

        LOG.debug(String.format(Messages.LOG.TIME_TAKEN, APIS.API.RESEND_PASSWORD_SET_LINK, timer.stop()));
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/password")
    public ResponseEntity<GenericResponse> updatePassword(@RequestBody UpdateCredentialRequest updateCredentialRequest){
        Stopwatch timer = Stopwatch.createStarted();
        GenericResponse genericResponse = new GenericResponse(true, Messages.SuccessMessage.PASSWORD_UPDATED, null);
        try {
            authService.updatePassword(updateCredentialRequest);
        }
        catch (AuthException ae){
            genericResponse.setSuccessStatus(false);
            genericResponse.setMessage(ae.getMessage());
        }
        catch (Exception e){
            genericResponse.setSuccessStatus(false);
            genericResponse.setMessage(Messages.ExceptionMessage.INTERNAL_ERROR);
            LOG.error(e.getMessage(), e.fillInStackTrace());
        }

        LOG.debug(String.format(Messages.LOG.TIME_TAKEN, APIS.API.UPDATE_PASSWORD, timer.stop()));
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }


}
