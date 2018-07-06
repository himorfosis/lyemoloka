package com.himorfosis.sabunin;

import android.content.Intent;

/**
 * Created by him on 4/8/2018.
 */

public class BahanClassData {

    private String bahan;
    private Integer ukuran;

    public BahanClassData(String bahan, Integer ukuran) {
        this.bahan = bahan;
        this.ukuran = ukuran;

    }

    public String getBahan() {
        return bahan;
    }

    public Integer getUkuran() {
        return ukuran;
    }


}
