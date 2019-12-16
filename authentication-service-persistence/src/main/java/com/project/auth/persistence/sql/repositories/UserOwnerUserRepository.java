/*
 * 15/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.persistence.sql.repositories;

import com.project.auth.persistence.sql.entities.UserOwnerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOwnerUserRepository extends JpaRepository<UserOwnerUser, Integer> {
}
