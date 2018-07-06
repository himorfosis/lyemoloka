package com.himorfosis.sabunin;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

/**
 * Created by him on 4/26/2018.
 */

public class LihatDataFrag extends Fragment {

    String isijudul;
    String isijenis;
    String totallye;
    String amountofwater;
    AlertDialog alertDialog;
    public static String df4 = new String(".####");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        return inflater.inflate(R.layout.lihatdatafrag, container, false);

    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
        getActivity().invalidateOptionsMenu();

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

        final Button back = (Button) actionBar.getCustomView().findViewById(R.id.kembali);
        back.setVisibility(View.VISIBLE);

        final TextView judul = view.findViewById(R.id.judul);
        final TextView jenis = view.findViewById(R.id.jenis);
        TextView lyeliquid = view.findViewById(R.id.lye);
        TextView oilfats = view.findViewById(R.id.gram);
        Button send = view.findViewById(R.id.send);

        savedInstanceState = getArguments();

        if (savedInstanceState == null) {

        } else {

            isijudul = savedInstanceState.getString("isijudul");
            isijenis = savedInstanceState.getString("isijenis");
            totallye = savedInstanceState.getString("isilye");
            amountofwater = savedInstanceState.getString("isiamount");

        }

        judul.setText("Recipe Title : " + isijudul);
        jenis.setText("A " + isijenis + " soap, measured in grams");
        lyeliquid.setText(totallye + " g");
        oilfats.setText(amountofwater + " g");


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder((getContext()));
                LayoutInflater inflater = getLayoutInflater();
                final View dialogview = inflater.inflate(R.layout.includemail, null);
                final Button kirim = dialogview.findViewById(R.id.kirim);
                final EditText email = dialogview.findViewById(R.id.email);

                kirim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String subject = isijudul;
                        String message = "Nama sabun " + isijudul + "\n\n" + "Jenis sabun = " + isijenis + "\n" + "Total lye = " + totallye + " g" + "\n" + "Amount of water = " + amountofwater + " g";
                        String from = email.getText().toString(); //from

                        KirimEmail sm = new KirimEmail(getContext(), from, subject, message);

                        sm.execute();

                    }
                });

                builder.setView(dialogview);

                alertDialog = builder.create();
                alertDialog.show();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().finish();

            }
        });
    }
}
