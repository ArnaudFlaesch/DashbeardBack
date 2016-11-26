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


}