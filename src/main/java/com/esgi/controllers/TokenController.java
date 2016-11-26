package com.esgi.controllers;

import com.esgi.model.OuterId;
import com.esgi.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by valentin on 24/11/2016.
 */
@RequestMapping(value = "/token")
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OuterId checkToken(String tokenValue) {
        return new OuterId(tokenService.getUuidFromToken(tokenValue));
    }
}