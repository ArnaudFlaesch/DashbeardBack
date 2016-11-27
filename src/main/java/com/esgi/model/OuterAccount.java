package com.esgi.model;

/**
 * Created by valentin on 24/11/2016.
 */
public class OuterAccount {

    private String username;

    private String name;

    private String email;

    private String tokenvalue;

    private long tokenexpirationdate;

    public OuterAccount(String username, String name, String email, String tokenvalue, long tokenexpirationdate) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.tokenvalue = tokenvalue;
        this.tokenexpirationdate = tokenexpirationdate;
    }

    static public OuterAccount fromAccount(Account account) {
         OuterAccount outerAccount = new OuterAccount(
                 account.getUsername(),
                 account.getName(),
                 account.getEmail(),
                 account.getTokenValue(),
                 account.getTokenExpirationDate()
         );
        return outerAccount;
    }

    public OuterAccount(Account account){
        username = account.getUsername();
        name = account.getName();
        tokenvalue = account.getTokenValue();
        tokenexpirationdate = account.getTokenExpirationDate();
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

    public String getTokenvalue() {
        return tokenvalue;
    }

    public void setTokenvalue(String tokenvalue) {
        this.tokenvalue = tokenvalue;
    }

    public long getTokenexpirationdate() {
        return tokenexpirationdate;
    }

    public void setTokenexpirationdate(long tokenexpirationdate) {
        this.tokenexpirationdate = tokenexpirationdate;
    }
}