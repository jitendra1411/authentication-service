/*
 * 16/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.controller;

import com.project.auth.web.service.AuthService;
import com.project.auth.web.service.CurrentUserService;
import com.project.auth.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public class BaseController extends CurrentUserService {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;
}
