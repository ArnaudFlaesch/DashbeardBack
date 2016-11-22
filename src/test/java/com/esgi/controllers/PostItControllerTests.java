package com.esgi.controllers;

import com.esgi.DashbeardApiApplication;
import com.esgi.model.PostIt;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Arnaud on 01/11/2016.
 * Modified by Valentin on 22/11/2016
 */
@ActiveProfiles("tests")
@SpringApplicationConfiguration(classes = DashbeardApiApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PostItControllerTests {

    @Value("${server.port}")
    private Integer port;

    @Before
    public void setupRestassured() {
        RestAssured.port = port;
    }

    @Test
    public void shouldGetOnePostIt() {
        final PostIt postIt = new PostIt();
        postIt.setContent("contenu du postit, c'est le contenu du post it, chef un lapin! ni!");
        postIt.setIdPostIt(999999999999999999L);
        postIt.setPositionX(2f);
        postIt.setPositionY(3f);
        postIt.setSizeX(30f);
        postIt.setSizeY(60f);
        /*given()
                .log().all()
                .contentType(JSON)
                .body(toJson(innerAccount))
                .when()
                .post("/account/create")
                .then()
                .log().all()
                .statusCode(CREATED.value())
                .body("username", is(innerAccount.getUsername()))
                .body("name", is(innerAccount.getName()));
    }*/
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
