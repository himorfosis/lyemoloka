package com.himorfosis.sabunin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by him on 7/5/2018.
 */

public class HasilHitungBahan extends AppCompatActivity {

    //get data from hitungbahan
    String getjudul, getjenis, amountOfLye, amountOfWater, isiTanggal;
    TextView judul, jenis, lye, water;
    Button kembali, simpan;

    Database db;
    public static DecimalFormat df4 = new DecimalFormat(".####");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bahanfraghasil);

        db = new Database(getApplicationContext());

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        TextView toolbarText = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        toolbarText.setText("Ingredients");

        kembali = (Button) getSupportActionBar().getCustomView().findViewById(R.id.kembali);
        kembali.setVisibility(View.VISIBLE);

        judul = (TextView) findViewById(R.id.judul);
        jenis = (TextView) findViewById(R.id.jenis);
        lye = (TextView) findViewById(R.id.lye);
        water = (TextView) findViewById(R.id.gram);
        simpan = (Button) findViewById(R.id.simpan);


        Intent intent = getIntent();
        getjudul = intent.getStringExtra("judul");
        getjenis = intent.getStringExtra("jenis");
        amountOfLye = intent.getStringExtra("amountoflye");
        amountOfWater = intent.getStringExtra("amountofwater");

        judul.setText(getjudul);
        jenis.setText("A " +getjenis +" soap, measured in Grams");
        lye.setText(amountOfLye);
        water.setText(amountOfWater);


        Calendar c = Calendar.getInstance();

        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate1 = df2.format(c.getTime());
        String[] daysArray = new String[]{"", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday ", "Saturday", "Sunday"};
        String[] monthArray = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        String hari = formattedDate1.substring(formattedDate1.indexOf("-") + 1);
        String tanggalbulan = formattedDate1.substring(formattedDate1.indexOf("-") + 1);
        String tanggal = tanggalbulan.substring(tanggalbulan.lastIndexOf("-") + 1);
        String bulan = tanggalbulan.substring(0, tanggalbulan.indexOf("-"));
        String tahun = formattedDate1.substring(0, formattedDate1.indexOf("-"));

        int intbulan = Integer.parseInt(bulan);
        int date = c.get(Calendar.DAY_OF_WEEK);

        String fixtanggal = daysArray[date]+", " +tanggal+ " " + monthArray[intbulan - 1] + " " + tahun;

        isiTanggal = fixtanggal.toString();

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.insertbookmark(new BookmarkClassData(null, judul.getText().toString(), isiTanggal.toString(), jenis.getText().toString(), lye.getText().toString(), water.getText().toString() ));

                Toast.makeText(getApplicationContext(), "Data saved succesfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), Utama.class);
                startActivity(intent);


            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        Utilities.clearSharedPreferance("tambahbahan", getApplicationContext());
        Utilities.clearSharedPreferance("ukuranbahan", getApplicationContext());

    }
}
