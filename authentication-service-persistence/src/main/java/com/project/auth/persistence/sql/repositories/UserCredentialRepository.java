/*
 * 15/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.persistence.sql.repositories;

import com.project.auth.persistence.sql.entities.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {
    public UserCredential findByEmailId(String emailId);
    public UserCredential findByPasswordToken(String passwordToken);
}
