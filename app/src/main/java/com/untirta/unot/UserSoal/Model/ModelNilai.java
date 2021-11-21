package com.untirta.unot.UserSoal.Model;

public class ModelNilai {
    String id, identitas, nilai;

    public ModelNilai() {
    }

    public ModelNilai(String id, String identitas, String nilai) {
        this.id = id;
        this.identitas = identitas;
        this.nilai = nilai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentitas() {
        return identitas;
    }

    public void setIdentitas(String identitas) {
        this.identitas = identitas;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }
}
