/*
 * 16/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.service;

import com.project.auth.persistence.sql.entities.User;
import com.project.auth.persistence.sql.entities.UserCredential;
import com.project.auth.persistence.sql.entities.UserRole;
import com.project.auth.persistence.sql.repositories.UserCredentialRepository;
import com.project.auth.persistence.sql.repositories.UserRoleRepository;
import com.project.auth.web.constant.Messages;
import com.project.auth.web.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    UserCredentialRepository userCredentialRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        final UserCredential userCredential = userCredentialRepository.findByEmailId(emailId);
        if (null == userCredential) {
            throw new UsernameNotFoundException(Messages.ExceptionMessage.INVALID_CREDENTIAL);
        }
        User user = userCredential.getUser();
        UserRole userRole = userRoleRepository.findByUser(user);
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole.getRole().getName());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(grantedAuthority);
        return new UserDetails(userCredential, authorities);
    }
}
