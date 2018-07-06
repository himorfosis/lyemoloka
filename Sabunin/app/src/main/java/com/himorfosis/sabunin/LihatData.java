package com.himorfosis.sabunin;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LihatData extends AppCompatActivity {

    Fragment fragment;
    String isijudul, isijenis, totallye, amountofwater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihat_data);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);
//
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.toolbar);

        TextView nama = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        nama.setText("Bookmark");
//
//        Button back = (Button)getSupportActionBar().getCustomView().findViewById(R.id.kembali);
//        back.setVisibility(View.VISIBLE);

        Intent intent = getIntent();

            isijudul = intent.getStringExtra("judul");
            isijenis = intent.getStringExtra("jenis");
            totallye = intent.getStringExtra("lye");
            amountofwater = intent.getStringExtra("amount");

            Log.e("total lye", " " +String.format(totallye));
            Log.e("amount of water", " " +String.format(totallye));


        Bundle bundle = new Bundle();
        bundle.putString("isijudul", isijudul);
        bundle.putString("isijenis", isijenis);
        bundle.putString("isilye", totallye);
        bundle.putString("isiamount", amountofwater);

        LihatDataFrag pindah = new LihatDataFrag();
        pindah.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, pindah);

        transaction.commit();

    }

}
