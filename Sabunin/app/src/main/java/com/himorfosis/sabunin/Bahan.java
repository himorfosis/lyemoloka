package com.himorfosis.sabunin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by him on 4/6/2018.
 */

public class Bahan extends AppCompatActivity {

    String isijudul, isijenis, isisatuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bahan);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        TextView judul = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        judul.setText("Ingredients");

        Intent intent = getIntent();
        isijudul = intent.getStringExtra("judul");
        isijenis = intent.getStringExtra("jenis");
        isisatuan = intent.getStringExtra("satuan");

        Log.e("bahan judul ", ": "+isijudul);
        Log.e("bahan jenis ", ": "+isijenis);

        Bundle bundle = new Bundle();
        bundle.putString("isijudul", isijudul);
        bundle.putString("isijenis", isijenis);
        bundle.putString("isisatuan", isisatuan);

        BahanFragKalkulasi kelas = new BahanFragKalkulasi();
        kelas.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_bahan, kelas);

        transaction.commit();


    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();

            Log.e("if", " ");
            //additional code
        } else {
            getFragmentManager().popBackStack();

            Log.e("else", " ");
        }
    }

}
