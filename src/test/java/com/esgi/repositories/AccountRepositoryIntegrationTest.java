package com.esgi.repositories;

import com.esgi.DashbeardApiApplication;
import com.esgi.model.Account;
import com.esgi.model.TimestampMillis;
import com.esgi.repositories.datasets.AccountDataset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by valentin on 26/11/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DashbeardApiApplication.class)
@AccountDataset
public class AccountRepositoryIntegrationTest {

    private static final org.slf4j.Logger LOGGER = getLogger(DashbeardApiApplication.class);

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void should_find_all_accounts() {
        assertThat(accountRepository.findAll(), hasSize(3));
    }

    @Test
    public void should_find_by_uuid() {
        final Account account = accountRepository.findOne("9d7478a8-b8c2-4db0-9ae3-6fc9d2413ff2");
        assertThat(account, notNullValue());
        assertThat(account.getUsername(), is("MISTERDUDE"));
        assertThat(account.getPassword(), is("dude_passwd"));
        assertThat(account.getName(), is("Johnson"));
    }

    @Test
    public void should_find_by_username() {
        final Account account = accountRepository.findUserByUsername("MISTERDUDE");
        assertThat(account, notNullValue());
        assertThat(account.getUsername(), is("MISTERDUDE"));
        assertThat(account.getPassword(), is("dude_passwd"));
        assertThat(account.getName(), is("Johnson"));
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }

    @Test
    public void should_create_new_account() {
        long expirationDate = System.currentTimeMillis() + TimestampMillis.WEEK * 2;
        Account account = new Account(
                "PATATE",
                "patate name",
                "patate@email.com",
                "passwd",
                "8DYSHG38YHDU03DJ9H3HKMNSLNCOCJPSJPDJPJZDKJZP!",
                expirationDate);
        account.setUuid(generateUUID());
        account.setActive(true);

        accountRepository.saveAndFlush(account);

        final List<Account> accounts = accountRepository.findAll();
        assertThat(accounts.size(), is(4));
        accounts.forEach(accountRetrieved -> assertThat(accountRetrieved.getUuid(), notNullValue()));

        final Account assertAccount = accountRepository.findAll().get(3);

        LOGGER.info("Account infos ::: " + assertAccount.toString());

        assertThat(assertAccount.getTokenExpirationDate(), is(expirationDate));
    }

    @Test
    public void should_indicate_an_existing_uuid(){
        assertThat(accountRepository.exists("9d7478a8-b8c2-4db0-9ae3-6fc9d2413ff2"), is(true));
    }

    @Test
    public void should_indicate_an_non_existing_uuid(){
        assertThat(accountRepository.exists("9999999999999999999999999999999999999999999999999999999999"), is(false));
    }


    @Transactional
    @Test
    public void should_update_account() {
        final Account account = accountRepository.findAll().get(0);
        final String newName = "Sgt. Johnson";

        LOGGER.info("Account infos ::: " + accountRepository.getOne(account.getUuid()).toString());

        account.setName(newName);

        accountRepository.saveAndFlush(account);
        LOGGER.info("Account infos ::: " + accountRepository.getOne(account.getUuid()).toString());

        assertThat(accountRepository.getOne(account.getUuid()).getName(), is(newName));
        LOGGER.info("Account infos ::: " + accountRepository.getOne(account.getUuid()).toString());
    }

    @Test
    public void should_find_active_accounts() {
        final List<Account> accounts = accountRepository.findByActiveTrue();
        assertThat(accounts.size(), is(2));
    }

}
