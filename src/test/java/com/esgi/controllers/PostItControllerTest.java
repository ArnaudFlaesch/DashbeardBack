package com.esgi.controllers;

import com.esgi.DashbeardApiApplication;
import com.esgi.model.PostIt;
import com.esgi.repositories.PostItRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;


/**
 *
 * Created by valentin on 23/11/2016.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = DashbeardApiApplication.class)
public class PostItControllerTest {

    @InjectMocks
    PostItController postItController;

    @Mock
    PostItRepository postItRepository;

    @Mock
    PostIt mockPostIt;

    @Test
    public void should_create_new_post_it() {
        postItController.addPostIt(mockPostIt);
        verify(postItRepository, atLeastOnce()).save(mockPostIt);
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
