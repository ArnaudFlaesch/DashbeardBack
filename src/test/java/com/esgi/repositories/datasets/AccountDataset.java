package com.esgi.repositories.datasets;

import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Sql(
        statements = {
                "INSERT INTO account (uuid, username, password, name, email, token_value, token_expiration_date, active) " +
                        "VALUES ('9d7478a8-b8c2-4db0-9ae3-6fc9d2413ff2', 'MISTERDUDE', 'dude_passwd', 'Johnson', 'johnson@mail.com', '0123456789', 2460569814465, false)",
                "INSERT INTO account (uuid, username, password, name, email, token_value, token_expiration_date, active) " +
                        "VALUES ('91deefe0-16bd-4f8c-bd59-bdb738226d3d', 'Ness147', 'ness_passwd', 'Ness', 'ness@mail.com', 'ABCDEFGHL', 0, true)",
                "INSERT INTO account (uuid, username, password, name, email, token_value, token_expiration_date, active) " +
                        "VALUES ('ed05768b-068c-4f9a-aaed-3c0c441972f1', 'YOLODU117', 'yolo_passwd', 'MC John', 'john117@mail.com', '1234567', 2460669814465, true)"
        },
        executionPhase = BEFORE_TEST_METHOD
)
@Sql(
        statements = {
                "DELETE FROM account"
        },
        executionPhase = AFTER_TEST_METHOD
)
public @interface AccountDataset {
}
