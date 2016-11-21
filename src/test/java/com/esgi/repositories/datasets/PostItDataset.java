package com.esgi.repositories.datasets;

import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

/**
 * Created by Arnaud on 21/11/2016.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Sql(
        statements = {
                "INSERT INTO PostIt VALUES (1, 'Nouveau Post it', 400, 500.0, 45.0, 63.0)",
                "INSERT INTO PostIt VALUES (2, 'Post it 2', 400.0, 500.0, 45.0, 63.0)",
                "INSERT INTO PostIt VALUES (3, 'Post it 3', 400.0, 500.0, 45.0, 63.0)",
                "INSERT INTO PostIt VALUES (4, 'Post it 4', 400.0, 500.0, 45.0, 63.0)",
                "INSERT INTO PostIt VALUES (5, 'Post it 5', 400.0, 500.0, 45.0, 63.0)",
                "INSERT INTO PostIt VALUES (6, 'Post it 6', 400.0, 500.0, 45.0, 63.0)",
                "INSERT INTO PostIt VALUES (7, 'Post it 7', 400.0, 500.0, 45.0, 63.0)",
                "INSERT INTO PostIt VALUES (8, 'Post it 8', 400.0, 500.0, 45.0, 63.0)",
                "INSERT INTO PostIt VALUES (9, 'Post it 9', 400.0, 500.0, 45.0, 63.0)",
                "INSERT INTO PostIt VALUES (10, 'Post it 10', 400.0, 500.0, 45.0, 63.0)",

        },
        executionPhase = BEFORE_TEST_METHOD
)
@Sql(
        statements = {
                "DELETE FROM PostIt",
        },
        executionPhase = AFTER_TEST_METHOD
)

public @interface PostItDataset {
}