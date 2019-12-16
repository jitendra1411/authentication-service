/*
 * 16/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.security;

import com.project.auth.persistence.sql.entities.UserCredential;
import com.project.auth.web.constant.Users;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserDetails extends UserCredential implements org.springframework.security.core.userdetails.UserDetails {

    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public UserDetails(UserCredential userCredential, Collection<? extends GrantedAuthority> authorities) {
        super(userCredential);
        this.grantedAuthorities = authorities;
    }

    public void setGrantedAuthorities(Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return super.getEmailId();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return super.getUser().getStatus() == Users.AccountStatus.ACTIVATED;
    }
}
