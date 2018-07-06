package com.himorfosis.sabunin;

/**
 * Created by him on 4/23/2018.
 */

public class BahanDataClass {


    private Integer id_bahan;
    private String bahan;
    private String nilai;
    private String nilaikoh;


    public BahanDataClass(Integer id_bahan, String bahan, String nilai, String nilaikoh) {

        this.id_bahan = id_bahan;
        this.bahan = bahan;
        this.nilai = nilai;
        this.nilaikoh = nilaikoh;

    }

    public Integer getId_bahan() {
        return id_bahan;
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
