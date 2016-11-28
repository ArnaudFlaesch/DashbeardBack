package com.esgi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Created by valentin on 24/11/2016.
 */
@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "uuid")
//  @GeneratedValue(strategy = GenerationType.AUTO)
    private String uuid;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

/*
    @OneToOne
    @PrimaryKeyJoinColumn(name = "id")
    private Token token;
*/

    @Column(name = "token_value")
    private String tokenValue;

    @Column(name = "token_expiration_date")
    private long tokenExpirationDate;

    @Column(name = "active")
    private boolean active;

    public Account() {}

    public Account(String username, String name, String email, String password, String tokenValue, long tokenExpirationDate) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.tokenValue = tokenValue;
        this.tokenExpirationDate = tokenExpirationDate;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isNotActive() {
        return !active;
    }

    public static boolean isValid(Account account) {
        return !((account == null)
                || (account.getUuid() == null)
                || account.getUuid().isEmpty()
                || (account.getUsername() == null)
                || account.getUsername().isEmpty()
                || (account.getPassword() == null)
                || account.getPassword().isEmpty());
    }

    public static boolean isNotValid(Account account) {
        return !isValid(account);
    }

    @Override
    public String toString() {
        return "('"+uuid+"', '"+username+"', '"+password+"', '"+name+"', '"+tokenValue+"', "+ tokenExpirationDate +")";
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public long getTokenExpirationDate() {
        return tokenExpirationDate;
    }

    public void setTokenExpirationDate(long tokenExpirationDate) {
        this.tokenExpirationDate = tokenExpirationDate;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}