package com.esgi.controllers;

import com.esgi.DashbeardApiApplication;
import com.esgi.model.Credential;
import com.esgi.model.InnerAccount;
import com.esgi.model.OuterAccount;
import com.esgi.repositories.AccountRepository;
import com.esgi.repositories.datasets.AccountDataset;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.*;

/**
 * Created by valentin on 26/11/2016.
 */
@ActiveProfiles
@AccountDataset
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DashbeardApiApplication.class)
public class AccountControllerBigTest {

    protected static final Logger LOGGER = getLogger(PostItControllerBigTest.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AccountRepository accountRepository;

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
    @Test(expected = AssertionError.class)
    public void shouldNotAllowMethodPostOnAccount() {
        given()
                .log().all()
                .contentType(JSON)
                .body(toJson(null))
        .when()
                .post("/account")
        .then()
                .log().all()
                .statusCode(CREATED.value());
    }

    @Test
    public void shouldCreateNewAccount() throws JsonProcessingException {
        final InnerAccount innerAccount = new InnerAccount(
                                                "LONGPOTATO",
                                                "long_passwd",
                                                "potate@mail.com",
                                                "potate"
        );
        given()
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
    }

    @Test(expected = AssertionError.class)
    public void shouldNotCreateNewAccountBecauseOfAnAlreadyExistingUsername() throws JsonProcessingException {
        final InnerAccount innerAccount = new InnerAccount(
                "LONGPOTATO",
                "long_passwd",
                "potate@mail.com",
                "potate"
        );
        given()
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

        given()
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
    }

    @Test(expected = AssertionError.class)
    public void shouldNotCreateNew_Account() throws JsonProcessingException {
        final InnerAccount innerAccount = new InnerAccount(
                "LONGPOTATO",
                "long_passwd",
                "potate@mail.com",
                "potate"
        );
        given()
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
    }

    @Test
    public void shouldSignIn() throws JsonProcessingException {
        final Credential credential = new Credential(
                "Ness147",
                "ness_passwd"
        );
        OuterAccount outerAccount = given()
                .log().all()
                .contentType(JSON)
                .body(toJson(credential))
        .when()
                .post("/account/signin")
        .then()
                .statusCode(ACCEPTED.value())
                .log().all()
                .body("name", is("Ness"))
        .extract()
                .body().as(OuterAccount.class);

        assertThat(outerAccount.getUsername(), is("Ness147"));
        assertThat(outerAccount.getName(), is("Ness"));
        assertThat(outerAccount.getEmail(), is("ness@mail.com"));
        LOGGER.info("tokenValue ::: "+outerAccount.getTokenvalue());
        LOGGER.info("tokenExpirationDate ::: "+outerAccount.getTokenexpirationdate());

        assertThat(accountRepository.findUserByUsername("Ness147").isActive(), is(true));
    }

    @Test
    public void shouldSignOut() throws JsonProcessingException {
        String isSignOut = given()
                .log().all()
                .contentType(JSON)
                .queryParam("token", "1234567")
                .when()
                .post("/account/signout")
                .then()
                .statusCode(ACCEPTED.value())
                .log().all()
                .extract()
                .body().asString();

        assertThat(isSignOut, is("true"));
        assertThat(accountRepository.findUserByUsername("YOLODU117").isActive(), is(true));
        assertThat(accountRepository.findUserByUsername("YOLODU117").getTokenExpirationDate(), is(0L));
    }

    @Test(expected = AssertionError.class)
    public void shouldNotSignOut() throws JsonProcessingException {
        String isSignOut = given()
                .log().all()
                .contentType(JSON)
                .queryParam("token", "999999999999999999999999999999999999999999999999999999999999999999999")
                .when()
                .post("/account/signout")
                .then()
                .statusCode(ACCEPTED.value())
                .log().all()
                .extract()
                .body().asString();

        assertThat(isSignOut, is("true"));
        assertThat(accountRepository.findUserByUsername("YOLODU117").isActive(), is(true));
        assertThat(accountRepository.findUserByUsername("YOLODU117").getTokenExpirationDate(), is(0L));
    }

    @Test
    public void shouldNotSignOutBecauseOfAnAlreadyExpiredToken() throws JsonProcessingException {
        given()
                .log().all()
                .contentType(JSON)
                .queryParam("token", "ABCDEFGHL")
                .when()
                .post("/account/signout")
                .then()
                .statusCode(INTERNAL_SERVER_ERROR.value())
                .log().all();

        assertThat(accountRepository.findUserByUsername("Ness147").isActive(), is(true));
    }

    @Test
    public void shouldDeactivateAccount() throws JsonProcessingException {
        given()
                .log().all()
                .contentType(JSON)
                .queryParam("token", "1234567")
            .when()
                .delete("/account/delete")
            .then()
                .log().all()
                .statusCode(ACCEPTED.value());

        assertThat(accountRepository.findUserByUsername("Ness147").isActive(), is(true));
        assertThat(accountRepository.findUserByUsername("YOLODU117").isNotActive(), is(true));
    }

    @Test(expected = AssertionError.class)
    public void shouldNotDeactivateAccountBecauseOfUnexistingToken() throws JsonProcessingException {
        given()
                .log().all()
                .contentType(JSON)
                .when()
                .delete("/account/delete")
                .then()
                .log().all()
                .statusCode(ACCEPTED.value());

        assertThat(accountRepository.findUserByUsername("Ness147").isActive(), is(true));
        assertThat(accountRepository.findUserByUsername("YOLODU117").isNotActive(), is(true));
    }

    @Test(expected = AssertionError.class)
    public void shouldNotDeactivateAccountBecauseOfExpiredToken() throws JsonProcessingException {
        given()
                .log().all()
                .contentType(JSON)
                .queryParam("token", "ABCDEFGHL")
        .when()
                .delete("/account/delete")
        .then()
                .log().all()
                .statusCode(ACCEPTED.value());
    }

    @Test(expected = AssertionError.class)
    public void shouldNotDeactivateAccountBecauseOfAccountIsAlreadyDeactivated() throws JsonProcessingException {
        given()
                .log().all()
                .contentType(JSON)
                .queryParam("token", "0123456789")
        .when()
                .delete("/account/delete")
        .then()
                .log().all()
                .statusCode(ACCEPTED.value());
    }
*/
}
