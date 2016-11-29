package com.esgi.controllers;

import com.esgi.DashbeardApiApplication;
import com.esgi.model.PostIt;
import com.esgi.repositories.PostItRepository;
import com.esgi.repositories.datasets.AccountDataset;
import com.esgi.repositories.datasets.PostItDataset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 * Created by valentin on 23/11/2016.
 */
@PostItDataset
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
    public void shouldCreateNewPostIt() {
        postItController.addPostIt(mockPostIt);
        verify(postItRepository, atLeastOnce()).save(mockPostIt);
    }

    @Test//(expected = Error.class)
    public void shouldNotCreateNewPostitWhenIdExists() {
        final PostIt postIt = new PostIt();
        postIt.setContent("contenu du postit, c'est le contenu du post it, chef un lapin! ni!");
        postIt.setIdPostIt((long)1);
        postIt.setPositionX(2);
        postIt.setPositionY(3);
        postIt.setSizeX(30);
        postIt.setSizeY(60);
        postItController.addPostIt(postIt);
    }

    @Test
    public void shouldModifyPostIt() {
        postItController.updatePostIt(mockPostIt);
        verify(postItRepository, atLeastOnce()).save(mockPostIt);
    }

    @Test
    public void shouldDeletePostIt() {
        postItController.removePostIt(mockPostIt.getIdPostIt());
        verify(postItRepository, atLeastOnce()).delete(mockPostIt.getIdPostIt());
    }


}
