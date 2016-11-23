package com.esgi.controllers;

import com.esgi.DashbeardApiApplication;
import com.esgi.model.PostIt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;
import static org.slf4j.LoggerFactory.getLogger;


/**
 * Created by Arnaud on 01/11/2016.
 * Modified by Valentin on 22/11/2016
 */
@SpringApplicationConfiguration(classes = DashbeardApiApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PostItControllerBigTest {

    protected static final Logger LOGGER = getLogger(PostItControllerBigTest.class);

    @Autowired
    ObjectMapper objectMapper;

    @Value("${server.port}")
    private Integer port;

    @Before
    public void setupRestassured() {
        RestAssured.port = port;
    }

    public <T> String toJson(T entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            LOGGER.error("", e);
            return null;
        }
    }

    @Test
    public void shouldAddOnePostIt() {
        final PostIt postIt = new PostIt();
        postIt.setContent("contenu du postit, c'est le contenu du post it, chef un lapin! ni!");
        postIt.setIdPostIt((long)999999999);
        postIt.setPositionX(2);
        postIt.setPositionY(3);
        postIt.setSizeX(30);
        postIt.setSizeY(60);

        given()
                .log().all()
                .contentType(JSON)
                .body(toJson(postIt))
                .when()
                .post("/postit")
                .then()
                .log().all()
                .body("2.0", is(postIt.getPositionX()))
                .body("3.0", is(postIt.getPositionY()));
    }

    @Test
    public void shouldGetAllPostIts() {
        given()
                .log().all()
        .when()
                .get("/postit")
        .then()
                .log().all();
    }

    @Test
    public void shouldRemoveOnePostIt() {

    }

    @Test
    public void shouldNotRemovePostIt() {

    }
}
