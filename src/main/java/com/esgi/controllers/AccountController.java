package com.esgi.controllers;

/**
 * Created by valentin on 24/11/2016.
 */

import com.esgi.model.InnerAccount;
import com.esgi.model.OuterAccount;
import com.esgi.model.OuterId;
import com.esgi.services.AccountService;
import com.esgi.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * Created by MNV on 11/04/2016.
 */
@RequestMapping(value = "/account")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public OuterAccount createAccount(@RequestBody InnerAccount innerAccount) {
        if(null == innerAccount.getUsername()
                || innerAccount.getUsername().isEmpty()
                || null == innerAccount.getPassword()
                || innerAccount.getPassword().isEmpty()) {
            throw new CreationAccountException();
        }
        return accountService.createAccount(innerAccount);
    }


    @RequestMapping(value = "/update/{token}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OuterAccount updateAccount(@PathVariable("token") final String token, @RequestBody InnerAccount innerAccount) {
        tokenService.getUuidFromToken(token);
        return accountService.updateAccount(innerAccount);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean deleteAccount(String token) {
        String uuid = tokenService.getUuidFromToken(token);
        return accountService.deactivateAccount(uuid);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OuterAccount signIn(@RequestBody Credential credential) {
        return tokenService.createToken(credential);
    }

    @RequestMapping(value = "/signout", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean signOut(String token) {
        return tokenService.expireTokenStartingNow(token);
    }

    @RequestMapping(value = "/tool/infofromuuid", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OuterAccount retrieveAccountPublicInformationFromUuid(final String uuid) {
        return accountService.findAccountByUuid(uuid);
    }

    @RequestMapping(value = "/tool/infofromusername", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OuterAccount retrieveAccountPublicInformationFromUsername(final String username) {
        return accountService.findAccountByUsername(username);
    }

    @RequestMapping(value = "/tool/id", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OuterId retrieveAccountUuidFromUsername(final String username) {
        return accountService.findIdByUsername(username);
    }

}
