/*
 * 16/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.controller;


import com.google.common.base.Stopwatch;
import com.project.auth.model.request.LoginRequest;
import com.project.auth.model.response.GenericResponse;
import com.project.auth.model.response.LoginResponse;
import com.project.auth.web.constant.APIS;
import com.project.auth.web.constant.Messages;
import com.project.auth.web.exception.AuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController extends BaseController{

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public ResponseEntity<GenericResponse> login(LoginRequest loginRequest){

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
            genericResponse.setMessage(Messages.ErrorMessage.INTERNAL_ERROR);
            LOG.error(e.getMessage(), e.fillInStackTrace());
        }

        LOG.debug(String.format(Messages.LOG.TIME_TAKEN, APIS.API.LOGIN, timer.stop()));
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

}
