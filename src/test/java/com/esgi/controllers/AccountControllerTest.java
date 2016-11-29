package com.esgi.controllers;

import com.esgi.DashbeardApiApplication;
import com.esgi.exceptions.CreationAccountException;
import com.esgi.model.Account;
import com.esgi.model.InnerAccount;
import com.esgi.services.AccountService;
import com.esgi.services.TokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by valentin on 26/11/2016.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = DashbeardApiApplication.class)
public class AccountControllerTest {

    @InjectMocks
    AccountController accountController;

    @Mock
    AccountService accountService;

    @Mock
    TokenService tokenService;

    @Mock
    Account mockAccount;

    @Test
    public void shouldCreateNewAccount() {
        final InnerAccount innerAccount = new InnerAccount(
                "Nusername",
                "Npassword",
                "Nemail",
                "Nname"
        );
        accountController.createAccount(innerAccount);
        verify(accountService).createAccount(innerAccount);
    }

    @Test(expected = CreationAccountException.class)
    public void shouldNotCreateNewAccountWhenUsernameIsEmpty() {
        final InnerAccount innerAccount = new InnerAccount(
                "",
                "Npassword",
                "Nemail",
                "Nname"
        );
        accountController.createAccount(innerAccount);
    }

    @Test(expected = CreationAccountException.class)
    public void shouldNotCreateNewAccountWhenPasswordIsEmpty() {
        final InnerAccount innerAccount = new InnerAccount(
                "Nusername",
                "",
                "Nemail",
                "Nname"
        );
        accountController.createAccount(innerAccount);
    }

    @Test
    public void shouldDeleteAccount() {
        accountController.deleteAccount(any());
        verify(tokenService).getUuidFromToken(any());
        verify(accountService).deactivateAccount(any());
    }

}
