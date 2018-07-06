package com.himorfosis.sabunin;

/**
 * Created by him on 4/20/2018.
 */

public class BookmarkClassData  {

    private Integer id_bookmark;
    private String judul;
    private String tanggal;
    private String jenis;
    private String totallye;
    private String amountofwater;

    BookmarkClassData (Integer id_bookmark, String judul, String tanggal, String jenis, String totallye, String amountofwater) {

        this.id_bookmark = id_bookmark;
        this.judul = judul;
        this.tanggal = tanggal;
        this.jenis = jenis;
        this.totallye = totallye;
        this.amountofwater = amountofwater;

    }

    public  Integer getId_bookmark() {
        return id_bookmark;
    }

    public String getJudul() {
        return judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getJenis() {
        return jenis;
    }

    public String getTotallye() {
        return totallye;
    }

    public String getAmountofwater() {
        return amountofwater;
    }

}
