package com.untirta.unot.UserSide;

public class NIM {
    String id;
    String NIM;

    public NIM() {

    }

    public NIM(String id, String NIM) {
        this.id = id;
        this.NIM = NIM;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }
}
