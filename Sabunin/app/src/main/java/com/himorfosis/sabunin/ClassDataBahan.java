package com.himorfosis.sabunin;

/**
 * Created by him on 4/10/2018.
 */

public class ClassDataBahan {

    private String bahan;
    private String nilai;
    private String nilaikoh;


    public ClassDataBahan(String bahan, String nilai, String nilaikoh) {
        this.bahan = bahan;
        this.nilai = nilai;
        this.nilaikoh = nilaikoh;

    }

    public String getBahan() {
        return bahan;

    }

    public String getNilai() {
       return nilai;

    }

    public String getNilaikoh() {
        return nilaikoh;
    }
}
