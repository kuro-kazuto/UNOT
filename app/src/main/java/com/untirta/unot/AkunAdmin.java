package com.untirta.unot;

public class AkunAdmin {
    String id;
    String emailA;
    String passA;

    public AkunAdmin() {

    }


    public AkunAdmin(String id, String emailA, String passA) {
        this.id = id;
        this.emailA = emailA;
        this.passA = passA;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailA() {
        return emailA;
    }

    public void setEmailA(String emailA) {
        this.emailA = emailA;
    }

    public String getPassA() {
        return passA;
    }

    public void setPassA(String passA) {
        this.passA = passA;
    }
}