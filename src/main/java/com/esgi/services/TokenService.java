package com.esgi.services;

import com.esgi.exceptions.BadCredentialException;
import com.esgi.exceptions.BadTokenException;
import com.esgi.model.Account;
import com.esgi.model.Credential;
import com.esgi.model.OuterAccount;
import com.esgi.model.TimestampMillis;
import com.esgi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

/**
 * Created by valentin on 25/11/2016.
 */
@Service
public class TokenService {

    private final AccountRepository accountRepository;

    @Autowired
    public TokenService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    static final String characterSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom secureRandom = new SecureRandom();
    static final int tokenLength = 200;

    private boolean isTokenNotExist(String token) {
        if(null == token) {
            return true;
        }
        for(Account account : accountRepository.findByActiveTrue()) {
            if(0 == account.getTokenValue().compareTo(token)) {
                return false;
            }
        }
        return true;
    }

    private boolean isTokenExist(String token) {
        return !isTokenNotExist(token);
    }

    private String generateTokenValue(){
        StringBuilder stringBuilder = new StringBuilder(tokenLength);
        for( int i = 0; i < tokenLength; i++ )
            stringBuilder.append(characterSet.charAt(secureRandom.nextInt(characterSet.length())));
        return stringBuilder.toString();
    }

    @Transactional
    public OuterAccount createToken(Credential credential) {
        Account account = accountRepository.findUserByUsername(credential.getUsername());
        if(null == account
                || account.isNotActive()
                || 0 != account.getPassword().compareTo(credential.getPassword())) {
            throw new BadCredentialException();
        }
        String token;
        do{
            token = generateTokenValue();
        } while (isTokenExist(token));
        account.setTokenValue(token);
        account.setTokenExpirationDate(System.currentTimeMillis() + TimestampMillis.WEEK*2);
        return OuterAccount.fromAccount(account);
    }

    public String getUuidFromToken(String token) {

        if(null == token){
            throw new BadTokenException();
        }
        for (Account account : accountRepository.findByActiveTrue()) {
            if (0 == account.getTokenValue().compareTo(token)) {
                if (account.getTokenExpirationDate() > System.currentTimeMillis()) {
                    return account.getUuid();
                }
            }
        }
        throw new BadTokenException();
    }

    @Transactional
    public boolean expireTokenStartingNow(String token) {
        if(null == token
                || token.isEmpty()) {
            throw new BadTokenException();
        }
        for(Account account : accountRepository.findByActiveTrue()) {
            if(0 == account.getTokenValue().compareTo(token)) {
                if(account.getTokenExpirationDate() <= System.currentTimeMillis()) {
                    throw new BadTokenException();
                }
                account.setTokenExpirationDate(0);
                accountRepository.save(account);
                return true;
            }
        }
        throw new BadTokenException();
    }
}
