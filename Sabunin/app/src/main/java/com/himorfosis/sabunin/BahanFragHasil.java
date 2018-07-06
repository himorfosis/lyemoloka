package com.himorfosis.sabunin;


import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by him on 4/6/2018.
 */

public class BahanFragHasil extends Fragment {

    String isijudul, isijenis, totallye, amountofwater, isitanggal, isilye, isigramof, weightoflye, strlye, strgrams;
    Fragment fragment;
    Database db;

    public static DecimalFormat df4 = new DecimalFormat(".####");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.bahanfraghasil, container, false);
    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
        getActivity().invalidateOptionsMenu();
        savedInstanceState = getArguments();

        db = new Database(getContext());

       ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

        final Button back = (Button) actionBar.getCustomView().findViewById(R.id.kembali);
        back.setVisibility(View.VISIBLE);

        Button save = view.findViewById(R.id.simpan);

        final TextView judul = view.findViewById(R.id.judul);
        final TextView jenis = view.findViewById(R.id.jenis);
//        TextView lyeliquid = view.findViewById(R.id.lyeliquid);
//        TextView oilfats = view.findViewById(R.id.oilfats);
        TextView lye = view.findViewById(R.id.lye);
        TextView grams = view.findViewById(R.id.gram);
//        TextView gramof = view.findViewById(R.id.gramof);
//        TextView gramofvalue = view.findViewById(R.id.gramofvalue);
//        TextView lye = view.findViewById(R.id.lye);
//        TextView lyevalue = view.findViewById(R.id.lyevalue);


        //savedInstanceState = getArguments();
        if (savedInstanceState == null) {

        } else {

            isijudul = savedInstanceState.getString("judul");
            isijenis = savedInstanceState.getString("jenis");
            totallye = savedInstanceState.getString("totallye");
            amountofwater = savedInstanceState.getString("amountofwater");
//            amountofwater = savedInstanceState.getString("oilandfats");
//            isilye = savedInstanceState.getString("lye");
//            isigramof = savedInstanceState.getString("gramof");
//            weightoflye = savedInstanceState.getString("weightoflye");
//            weightoflye = savedInstanceState.getString("amountlyeandliquid");
//            strlye = savedInstanceState.getString("weightoflye");
//            strgrams = savedInstanceState.getString("amountofwater");


        }

        Log.e("isi judul", ": " + isijudul);
        Log.e("isi jenis", ": " + isijenis);
        Log.e("total lye liquid", ": " + totallye);
        Log.e("total amount of water", ": " +amountofwater);

        judul.setText(isijudul);
        jenis.setText("A " +isijenis +" soap, measured in Grams");
//        lyevalue.setText(isilye);
//        gramof.setText("Gram of " +isijenis);
//        gramofvalue.setText(isigramof);
//        lyeliquid.setText(totallye + " g");
//        lyeliquid.setText(weightoflye + " g");
//        oilfats.setText(amountofwater + " g");

        lye.setText(totallye);
        grams.setText(amountofwater);

//        DecimalFormat formatter = new DecimalFormat();
//        DecimalFormatSymbols symbol = new DecimalFormatSymbols();
//        // Specify the decimal separator symbol
//        symbol.setDecimalSeparator(',');
//        formatter.setDecimalFormatSymbols(symbol);


//        try {
//
//            Double hasillye = formatter.parse(totallye).doubleValue();
//            Double hasilamountofwater = formatter.parse(amountofwater).doubleValue();



//        }catch (ParseException e) {
//            e.printStackTrace();
//
//        }


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

//        int date = c.get(Calendar.DAY_OF_WEEK);
        int intbulan = Integer.parseInt(bulan);
        int date = c.get(Calendar.DAY_OF_WEEK);

//        String fixtanggal = daysArray[date]+", "+tanggal+" "+monthArray[intbulan-1]+" "+tahun;
        String fixtanggal = daysArray[date]+", " +tanggal+ " " + monthArray[intbulan - 1] + " " + tahun;

        Log.d("TANGGALAN", " = " + fixtanggal);
        //tvtanggalskrng.setText(fixtanggal);

        isitanggal = fixtanggal.toString();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().finish();

//                fragment = new BahanFragKalkulasi();
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.frame_bahan, fragment);
//                ft.commit();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.insertbookmark(new BookmarkClassData(null, isijudul.toString(), isitanggal.toString(), isijenis.toString(), totallye.toString(), amountofwater.toString() ));

                Toast.makeText(getContext(), "Data saved succesfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), Utama.class);
                startActivity(intent);


            }
        });

       // Utilities.putPrefName("tambahbahan", "text" + 0, "" + nilaibah, getContext());
        //Utilities.putPrefName("ukuranbahan", "text" + 0, "" + isiukuran.getId(), getContext());

        Utilities.clearSharedPreferance("tambahbahan", getContext());
        Utilities.clearSharedPreferance("ukuranbahan", getContext());

    }
}
