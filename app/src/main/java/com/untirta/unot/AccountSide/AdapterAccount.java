package com.untirta.unot.AccountSide;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class AdapterAccount {

    public String username;
    public String email;

    public AdapterAccount() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public AdapterAccount(String username, String email) {
        this.username = username;
        this.email = email;
    }

}