/*
 * 16/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value = "test")
public class TestController {

    @GetMapping
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("Authentication service is running", HttpStatus.OK);
    }
}
