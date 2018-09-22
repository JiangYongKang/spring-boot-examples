package com.vincent.spring.boot.session.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: vincent
 * Date: 2018-09-22 20:24:00
 * Comment:
 */

@Slf4j
@RestController
@RequestMapping("/sessions")
public class SessionsController {

    @GetMapping
    public ResponseEntity<?> index(HttpServletRequest request) {
        log.info(request.getSession().getId());
        return ResponseEntity.ok().build();
    }

}
