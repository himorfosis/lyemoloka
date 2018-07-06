package com.himorfosis.sabunin;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by him on 4/5/2018.
 */

public class UtamaTabHome extends Fragment {

    Fragment fragment;
    AlertDialog alertDialog;
    ArrayList<ClassDataList> arrayjenis = new ArrayList<>();
    TextView isijenis, isisatuan;
    EditText isijudul;
    Database db;
    String title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        return inflater.inflate(R.layout.utamahome, container, false);

    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
        getActivity().invalidateOptionsMenu();

        db = new Database(getContext());

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

        isijudul = (EditText) view.findViewById(R.id.nama);
        isijenis = (TextView) view.findViewById(R.id.jenis);
        Button buat = (Button) view.findViewById(R.id.buat);


        arrayjenis.add(new ClassDataList("Solid"));
        arrayjenis.add(new ClassDataList("Liquid"));

        isijenis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //final AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());
                final AlertDialog.Builder builder = new AlertDialog.Builder((getContext()));
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.listisi, null);
                final ListView list = (ListView) dialogView.findViewById(R.id.listisidata);

                final IsidataListAdapter adapter = new IsidataListAdapter(getContext(),R.layout.rowisi, arrayjenis);
                list.setAdapter(adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ClassDataList p = arrayjenis.get(i);
                        isijenis.setText(p.getmenu());
                        alertDialog.dismiss();
                    }
                });
                builder.setTitle("choose the soap type\n");
                builder.setView(dialogView);

                alertDialog = builder.create();
                alertDialog.show();

            }
        });

//        final int nilaijenis= isijenis.getId();
//        isijenis.setId(nilaijenis);

        Log.e("isi jenis", " " +isijenis.getText().toString().length());

        buat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (isijudul.getText().toString().length()==0 || isijenis.length()==0 ) {
                if (isijudul.getText().toString().length() == 0 || isijenis.getText().toString().equals("Type")) {

                    Toast.makeText(getContext(), "Please complete the contents", Toast.LENGTH_SHORT).show();

                } else {

                    Intent kirim = new Intent(getContext(), HitungBahan.class);
                    kirim.putExtra("judul", isijudul.getText().toString());
                    kirim.putExtra("jenis", isijenis.getText().toString());

                    getActivity().startActivity(kirim);

                    Log.e("judul", " " +isijudul.getText().toString());
                    Log.e("jenis", " " +isijenis.getText().toString());

                }

            }
        });

    }
}
