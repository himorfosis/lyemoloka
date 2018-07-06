package com.himorfosis.sabunin;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by him on 4/20/2018.
 */

public class BookmarkLihatData extends Fragment {

    String isijudul;
    String isijenis;
    String totallye;
    String amountofwater;
    AlertDialog alertDialog;
    public static String df4 = new String(".####");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.bookmarklihatdata, container, false);
    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
        getActivity().invalidateOptionsMenu();
        savedInstanceState = getArguments();

//        Button back = (Button) view.findViewById(R.id.kembali);
//        back.setVisibility(View.VISIBLE);

        final TextView judul = view.findViewById(R.id.judul);
        final TextView jenis = view.findViewById(R.id.jenis);
        TextView lyeliquid = view.findViewById(R.id.lyeliquid);
        TextView oilfats = view.findViewById(R.id.oilfats);
        Button send = view.findViewById(R.id.send);

        if (savedInstanceState == null) {

        } else {

            isijudul = savedInstanceState.getString("judul");
            isijenis = savedInstanceState.getString("jenis");
            totallye = savedInstanceState.getString("lye");
            amountofwater = savedInstanceState.getString("amount");

            Log.e("total lye", " " +String.format(totallye));
            Log.e("amount of water", " " +String.format(totallye));

        }

        judul.setText(isijudul);
        jenis.setText("A " + isijenis + " soap, measured in Grams");
        lyeliquid.setText(totallye + " g");
        oilfats.setText(amountofwater + " g");


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder((getContext()));
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View dialogview = inflater.inflate(R.layout.includemail, null);
                final Button kirim = dialogview.findViewById(R.id.kirim);
                final EditText email = dialogview.findViewById(R.id.email);

                kirim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String subject = isijudul;
                        String message = isijudul + "\n\n" + "Jenis sabun = " + isijenis + "\n" + "Total lye = " + totallye + " g" + "\n" + "Amount of water = " + amountofwater + " g";
                        String from = email.getText().toString(); //from

                        KirimEmail sm = new KirimEmail(getContext(), from, subject, message);

                        sm.execute();


                    }
                });

                builder.setView(dialogview);

                alertDialog = builder.create();
                alertDialog.show();

//                String subject = isijudul;
//                String message = isijudul +"\n\n" +"Jenis sabun = " + isijenis + "\n" + "Total lye = " + totallye + " g" + "\n" + "Amount of water = " + amountofwater +" g";
//                String from = "haimar404@gmail.com"; //from
//
//                KirimEmail sm = new KirimEmail(getContext(), from, subject, message);
//
//                sm.execute();

            }
        });
//
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Fragment fragment = new UtamaTabBookmark();
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.frame_container, fragment);
//                ft.commit();
//
//            }
//        });

    }
}
