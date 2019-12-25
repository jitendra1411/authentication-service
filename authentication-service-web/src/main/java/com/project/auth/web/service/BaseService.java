/*
 * 15/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.service;

import com.project.auth.persistence.sql.repositories.*;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class BaseService extends CurrentUserService{

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    UserCredentialRepository userCredentialRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserOwnerUserRepository userOwnerUserRepository;
}
