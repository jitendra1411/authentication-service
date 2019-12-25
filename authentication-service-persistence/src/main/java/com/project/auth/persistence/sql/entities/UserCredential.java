/*
 * 15/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.persistence.sql.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_credential")
public class UserCredential extends BusinessItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_credential_id")
    private int userCredentialId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "password")
    private String password;

    @Column(name = "set_password_token")
    private String passwordToken;

    @Column(name = "set_password_token_expiry")
    private Long passwordTokenExpiry;

    public UserCredential() {
    }

    public UserCredential(UserCredential userCredential) {
        this.userCredentialId = userCredential.getUserCredentialId();
        this.user = userCredential.getUser();
        this.emailId = userCredential.getEmailId();
        this.password = userCredential.getPassword();
        this.passwordToken = userCredential.getPasswordToken();
        this.passwordTokenExpiry = userCredential.getPasswordTokenExpiry();
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getUserCredentialId() {
        return userCredentialId;
    }

    public void setUserCredentialId(int userCredentialId) {
        this.userCredentialId = userCredentialId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordToken() {
        return passwordToken;
    }

    public void setPasswordToken(String passwordToken) {
        this.passwordToken = passwordToken;
    }

    public Long getPasswordTokenExpiry() {
        return passwordTokenExpiry;
    }

    public void setPasswordTokenExpiry(Long passwordTokenExpiry) {
        this.passwordTokenExpiry = passwordTokenExpiry;
    }
}
