package com.esgi.repositories;

import com.esgi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by valentin on 24/11/2016.
 */
public interface AccountRepository extends JpaRepository<Account, String> {

@SuppressWarnings("all")
@Query("select a from Account a where a.username = :username_param")
    Account findUserByUsername(@Param("username_param") String username);

@SuppressWarnings("all")
@Query("select a from Account a where a.uuid = :uuid_param")
    Account findUserByUuid(@Param("uuid_param") String uuid);

        List<Account> findAll();

        List<Account> findByActiveTrue();

        Account save(Account account);

@Override
    boolean exists(String uuid);

}
