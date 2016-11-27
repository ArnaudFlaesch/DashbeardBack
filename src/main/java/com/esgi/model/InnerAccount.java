package com.esgi.model;

/**
 * Created by valentin on 24/11/2016.
 */
public class InnerAccount {

    private String username;

    private String password;

    private String email;

    private String name;

    public InnerAccount(String username, String password, String email, String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public static boolean isValid(InnerAccount innerAccount) {
        return !(innerAccount == null
                || innerAccount.getUsername() == null
                || innerAccount.getUsername().isEmpty()
                || innerAccount.getPassword() == null
                || innerAccount.getPassword().isEmpty());
    }

    public static Account toAccount(InnerAccount innerAccount) {
        Account account = new Account(
                innerAccount.getUsername(),
                innerAccount.getName(),
                innerAccount.getEmail(),
                innerAccount.getPassword(),
                "",
                0
                );
        return account;
    }

    public static boolean isNotValid(InnerAccount innerAccount) {
        return !isValid(innerAccount);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
