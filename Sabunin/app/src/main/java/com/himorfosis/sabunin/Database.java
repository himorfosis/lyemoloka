package com.himorfosis.sabunin;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by him on 4/20/2018.
 */

public class Database extends SQLiteOpenHelper {


    private static final String DatabaseName = "sabunin";
    private static final int DatabaseVersion = 2;
//    private SQLiteDatabase db;
//    public Database helper;

    public Database(Context context) {
        super(context, DatabaseName, null, DatabaseVersion);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE tabelbookmark ( id_bookmark INTEGER PRIMARY KEY AUTOINCREMENT, judul TEXT NOT NULL, tanggal TEXT NOT NULL, jenis TEXT NOT NULL, totallye TEXT NOT NULL, amountofwater TEXT NOT NULL ); ");
        db.execSQL("CREATE TABLE tabelbahan ( id_bahan INTEGER PRIMARY KEY AUTOINCREMENT, bahan TEXT NOT NULL, nilai TEXT NOT NULL, nilaikoh TEXT NOT NULL );");
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS tabelbookmark");
        db.execSQL("DROP TABLE IF EXISTS tabelbahan");

        onCreate(db);
    }

    public void insertbookmark(BookmarkClassData classdata) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("id_bookmark", classdata.getId_bookmark());
        cv.put("judul", classdata.getJudul());
        cv.put("tanggal", classdata.getTanggal());
        cv.put("jenis", classdata.getJenis());
        cv.put("totallye", classdata.getTotallye());
        cv.put("amountofwater", classdata.getAmountofwater());

        db.insert("tabelbookmark", null, cv);
        db.close();

    }

    public void deletebookmark(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = {id};
        ContentValues cv = new ContentValues();

        db.delete("tabelbookmark", "id_bookmark=?", args);
        db.close();
    }

    public List<BookmarkClassData> getallbookmark() {
        List<BookmarkClassData> dataArray = new ArrayList<BookmarkClassData>();
        String query = "SELECT * FROM tabelbookmark DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                BookmarkClassData datalist = new BookmarkClassData(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                dataArray.add(datalist);

            } while (cursor.moveToNext());
        }
        return dataArray;
    }

    public void insertbahan(BahanDataClass classdata) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("id_bahan", classdata.getId_bahan());
        cv.put("bakan", classdata.getBahan());
        cv.put("nilai", classdata.getNilai());
        cv.put("nilaikoh", classdata.getNilaikoh());

        db.insert("tabelbahan", null, cv);
        db.close();

    }

    public List<BahanDataClass> getallbahan() {

        List<BahanDataClass> dataArray = new ArrayList<BahanDataClass>();
        String query = "SELECT * FROM tabelbahan";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                BahanDataClass datalist = new BahanDataClass(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                dataArray.add(datalist);

            } while (cursor.moveToNext());

        }
            return dataArray;


    }
}
