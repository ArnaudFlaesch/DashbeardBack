package com.esgi.controllers;

import com.esgi.DashbeardApiApplication;
import com.esgi.model.PostIt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

/**
 * Created by valentin on 23/11/2016.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = DashbeardApiApplication.class)
public class PostItControllerTest {

    @InjectMocks
    PostItController postItController;

    @Mock
    PostIt mockPostIt;

    @Test
    public void should_create_new_post_it() {
        /*
        final InnerAccount innerAccount = InnerAccount.builder()
                .username("Nusername")
                .name("Nname")
                .email("Nemail")
                .password("Npassword")
                .build();
        accountController.createAccount(innerAccount);
        verify(accountService).createAccount(innerAccount);
        */
    }

    @Test/*(expected = CreationAccountException.class)*/
    public void should_not_create_new_postit_when_position_is_negative() {
        /*
            final InnerAccount innerAccount = InnerAccount.builder()

                .username("")
                .name("Nname")
                .email("Nemail")
                .password("Npassword")
                .build();
        accountController.createAccount(innerAccount);
        */
    }

    @Test
    public void should_modify_post_it() {
    }

    @Test
    public void should_delete_post_it() {
        /*
        accountController.deleteAccount(any());
        verify(tokenService).getUuidFromToken(any());
        verify(accountService).deactivateAccount(any());
        */
    }


}
