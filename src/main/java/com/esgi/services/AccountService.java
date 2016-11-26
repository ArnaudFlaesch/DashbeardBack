package com.esgi.services;

import com.esgi.exceptions.CreationAccountException;
import com.esgi.exceptions.DeletionAccountException;
import com.esgi.exceptions.NotValidAccountException;
import com.esgi.model.Account;
import com.esgi.model.InnerAccount;
import com.esgi.model.OuterAccount;
import com.esgi.model.OuterId;
import com.esgi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by valentin on 26/11/2016.
 */
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TokenService tokenService;

    @Autowired
    public AccountService(AccountRepository accountRepository, TokenService tokenService) {
        this.accountRepository = accountRepository;
        this.tokenService = tokenService;
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }

    @Transactional
    public OuterAccount createAccount(InnerAccount innerAccount) {
        if(InnerAccount.isNotValid(innerAccount)){
            throw new NotValidAccountException();
        }

        if(null != accountRepository.findUserByUsername(innerAccount.getUsername())) {
            throw new NotValidAccountException();
        }

        Account account = InnerAccount.toAccount(innerAccount);

        String uuid = generateUUID();
        while(accountRepository.exists(uuid)) {
            uuid = generateUUID();
        }

        account.setUuid(uuid);
        account.setTokenValue("");
        account.setTokenExpirationDate(0);
        account.setActive(true); //pas de validation par email

        Account returnAccount = accountRepository.saveAndFlush(account);
        if(Account.isNotValid(returnAccount)) {
            throw new CreationAccountException();
        }
        return OuterAccount.fromAccount(returnAccount);
    }

    @Transactional
    public OuterAccount updateAccount(InnerAccount innerAccount) {
        if(InnerAccount.isNotValid(innerAccount)){
            throw new NotValidAccountException();
        }

        Account account = accountRepository.findUserByUsername(innerAccount.getUsername());
        account.setName(innerAccount.getName());
        account.setPassword(innerAccount.getPassword());
        account.setEmail(innerAccount.getEmail());

        Account returnAccount = accountRepository.saveAndFlush(account);
        if(Account.isNotValid(returnAccount)) {
            throw new CreationAccountException();
        }
        return OuterAccount.fromAccount(returnAccount);
    }

    @Transactional
    public boolean deactivateAccount(String uuid) {
        if(null == uuid
                || uuid.isEmpty()){
            throw new DeletionAccountException();
        }
        Account account = accountRepository.findOne(uuid);
        if(null == account) {
            throw new DeletionAccountException();
        }
        account.setActive(false);
        if(null == accountRepository.saveAndFlush(account)) {
            throw new DeletionAccountException();
        }
        return true;
    }

    @Transactional
    public OuterId findIdByUsername(String username) {
        if(null == username
                || username.isEmpty()){
            throw new NotValidAccountException();
        }
        Account account = accountRepository.findUserByUsername(username);
        if(null == account) {
            throw new NotValidAccountException();
        }
        return new OuterId(account.getUuid());
    }

    @Transactional
    public OuterAccount findAccountByUsername(String username) {
        if(null == username
                || username.isEmpty()){
            throw new NotValidAccountException();
        }
        Account account = accountRepository.findUserByUsername(username);
        if(null == account) {
            throw new NotValidAccountException();
        }
        return OuterAccount.fromAccount(account);
    }

    @Transactional
    public OuterAccount findAccountByUuid(String uuid) {
        if(null == uuid
                || uuid.isEmpty()){
            throw new NotValidAccountException();
        }
        Account account = accountRepository.findOne(uuid);
        if(null == account) {
            throw new NotValidAccountException();
        }
        return OuterAccount.fromAccount(account);
    }

}
