/*
 * 26/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.service;

import com.project.auth.persistence.sql.entities.UserCredential;
import com.project.auth.persistence.sql.repositories.UserOwnerUserRepository;
import com.project.auth.persistence.sql.repositories.UserRepository;
import com.project.auth.persistence.sql.repositories.UserRoleRepository;
import com.project.auth.web.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserOwnerUserRepository userOwnerUserRepository;

    protected UserDetails getCurrentUserDetails() {
//        UserCredential userCredential = new UserCredential();
//        userCredential.setEmailId("local.hardcoaded@test.com");
//        UserDetails userDetails = new UserDetails(userCredential, null);
//        userDetails.setUser(userRepository.findByUserId(2));
//        return userDetails;


        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
