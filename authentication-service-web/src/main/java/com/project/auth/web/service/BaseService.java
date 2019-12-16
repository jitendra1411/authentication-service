/*
 * 15/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.service;

import com.project.auth.persistence.sql.repositories.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    @Autowired
    GenderRepository genderRepository;
}
