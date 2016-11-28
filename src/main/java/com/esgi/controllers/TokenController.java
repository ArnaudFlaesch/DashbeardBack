package com.esgi.controllers;

import com.esgi.model.OuterId;
import com.esgi.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public OuterId checkToken(@RequestParam String tokenValue) {
        return new OuterId(tokenService.getUuidFromToken(tokenValue));
    }
}