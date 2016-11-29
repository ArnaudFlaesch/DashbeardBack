package com.esgi.controllers;

import com.esgi.DashbeardApiApplication;
import com.esgi.model.PostIt;
import com.esgi.repositories.datasets.AccountDataset;
import com.esgi.repositories.datasets.PostItDataset;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static com.jayway.restassured.RestAssured.DEFAULT_URI;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.slf4j.LoggerFactory.getLogger;


/**
 * Created by Arnaud on 01/11/2016.
 * Modified by Valentin on 22/11/2016
 */
@WebAppConfiguration
@ActiveProfiles("tests")
@PostItDataset
@SpringApplicationConfiguration(classes = DashbeardApiApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PostItControllerBigTest {

    protected static final Logger LOGGER = getLogger(PostItControllerBigTest.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private PostItController postItController;

    public <T> String toJson(T entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            LOGGER.error("", e);
            return null;
        }
    }


    @Test
    public void testThatShouldWork() {
    }
/*
    @Test
    public void shouldAddOnePostIt() {
        final PostIt postIt = new PostIt();
        postIt.setContent("contenu du postit, c'est le contenu du post it, chef un lapin! ni!");
        postIt.setIdPostIt((long) 999999999);
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
                .log().all();
                /*
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
            .log().all()
        .body("$", hasSize(12));
    }

    @Test
    public void shouldRemoveOnePostIt() {

    }

    @Test
    public void shouldNotRemovePostIt() {

    }

    @Test
    public void shouldUpdatePostIt(){
        final PostIt postIt = new PostIt();
        postIt.setContent("contenu du postit, c'est le contenu du post it, chef un lapin! ni!");
        postIt.setIdPostIt((long)1);
        postIt.setPositionX(2);
        postIt.setPositionY(3);
        postIt.setSizeX(30);
        postIt.setSizeY(60);
        postItController.addPostIt(postIt);
        postItController.updatePostIt(postIt);
        boolean modified = false;
        List<PostIt> postItList = postItController.getDashboard();
        assertThat(postItList.size(), is(10));
        for (PostIt p : postItList) {
            if(p.getIdPostIt() == 1L) {
                modified = true;
                break;
            }
        }
        assertThat(modified, is(true));

    }

*/
}
