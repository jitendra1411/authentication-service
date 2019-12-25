/*
 * 26/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.controller;

import com.project.auth.model.response.GenericResponse;
import com.project.auth.persistence.sql.entities.User;
import com.project.auth.web.constant.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/secure")
public class SecureController extends BaseController{

    @GetMapping
    public ResponseEntity<GenericResponse> testSecurity(){
        String loggedInUser = getCurrentUserDetails().getUsername();
        GenericResponse genericResponse = new GenericResponse(true, String.format(Messages.SuccessMessage.SECURITY_CHECK_MESSAGE, loggedInUser), null);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }
}
