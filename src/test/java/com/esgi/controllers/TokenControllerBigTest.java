package com.esgi.controllers;

import com.esgi.DashbeardApiApplication;
import com.esgi.exceptions.BadTokenException;
import com.esgi.repositories.datasets.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.ACCEPTED;

/**
 * Created by valentin on 26/11/2016.
 */
@WebIntegrationTest
@ActiveProfiles("tests")
@PostItDataset
@SpringApplicationConfiguration(classes = DashbeardApiApplication.class)
@AccountDataset
@RunWith(SpringJUnit4ClassRunner.class)
public class TokenControllerBigTest {

    protected static final Logger LOGGER = getLogger(PostItControllerBigTest.class);


    @Test
    public void testThatShouldWork() {
    }

    @Test
    public void shouldAcceptToken() {
        String token = "1234567";
        LOGGER.info("token is ::: "+token);
        given()
                .log().all()
                .queryParam("tokenValue", token)
                .when()
                .post("/token/check")
                .then()
                .log().all()
                .body("uuid", is("ed05768b-068c-4f9a-aaed-3c0c441972f1"))
                .statusCode(ACCEPTED.value());
    }

    @Test
    public void shouldRefuseTokenBecauseIsExpired() {
        String token = "ABCDEFGHL";
        LOGGER.info("token is ::: "+token);
        given()
                .log().all()
                .contentType(JSON)
                .queryParam("token", token)
                .when()
                .post("/token/check")
                .then()
                .log().all()
                .body("exception", is(BadTokenException.class.getName()));
    }

    @Test
    public void shouldRefuseTokenBecauseIsNotExisting() {
        String token = "JKNKLDNLKDKLDKL?LKD?LKD?LD?LK?DL?DLK?LKD?LD?LKD?LKD?LD?";
        LOGGER.info("token is ::: "+token);
        given()
                .log().all()
                .contentType(JSON)
                .queryParam("token", token)
                .when()
                .post("/token/check")
                .then()
                .log().all()
                .body("exception", is(BadTokenException.class.getName()));
    }
}
