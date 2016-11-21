package com.esgi.controllers;

import com.esgi.DashbeardApiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Arnaud on 01/11/2016.
 */
@ActiveProfiles("tests")
@SpringApplicationConfiguration(classes = DashbeardApiApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PostItControllerTests {

    @Test
    public void shouldGetOnePostIt() {

    }

    @Test
    public void shouldGetAllPostIts() {

    }

    @Test
    public void shouldRemoveOnePostIt() {

    }

    @Test
    public void shouldNotRemovePostIt() {

    }
}
