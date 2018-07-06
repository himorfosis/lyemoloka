package com.himorfosis.sabunin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by him on 4/6/2018.
 */

public class BahanFragKalkulasi extends Fragment {

    Fragment fragment;
    String judul;
    String jenis;

    ArrayList<ClassDataBahan> daftarbahan = new ArrayList<>();
    AlertDialog alertDialog;
    LinearLayout rowpilihbahan;
    TextView pilihbahan1;
    ArrayList<Double> deret = new ArrayList<Double>();
    Double hasilconvert;
    Double totallye = 0.0;

    String value1;
    TextView nilai;
    ArrayList<Double> oilandfats = new ArrayList<>();
    Double totaloilandfats = 0.0;

    //Double convertukuran;
    //Double convertbahan;
    //int nilaibahan;
    public static DecimalFormat df4 = new DecimalFormat("#.####");
    public static DecimalFormat df2 = new DecimalFormat("##.##");

    int index = 0;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.bahanfragkalkulasi, container, false);
        Button btnbahan = (Button) view.findViewById(R.id.tambahbahan);
        final Button hapus = (Button) view.findViewById(R.id.hapus);

        rowpilihbahan = (LinearLayout) view.findViewById(R.id.haha);

        index++;

        btnbahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dinamic(index++);
            }
        });

        return view;
    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
        getActivity().invalidateOptionsMenu();

        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        final Button back = (Button) actionBar.getCustomView().findViewById(R.id.kembali);
        back.setVisibility(View.VISIBLE);

        TextView isijudul = (TextView) view.findViewById(R.id.isijudul);
        TextView isijenis = (TextView) view.findViewById(R.id.isijenis);

        final EditText kelembapan = view.findViewById(R.id.tingkat);
        final EditText isiukuran = view.findViewById(R.id.isiukuran);
        final Button kalkulasi = (Button) view.findViewById(R.id.kalkulasi);

        nilai = (TextView) view.findViewById(R.id.isinilai);
        pilihbahan1 = (TextView) view.findViewById(R.id.pilihbahanawal);

        // Lye ( Sodium Hydroxide ), NaOH

        daftarbahan.add(new ClassDataBahan("Almond Oil", "0.1367", "0.1925"));
        daftarbahan.add(new ClassDataBahan("Aloe Vera Butter", "0.1788", "0.2518"));
        daftarbahan.add(new ClassDataBahan("Aloe Vera Oil", "0.1421", "0.2001"));
        daftarbahan.add(new ClassDataBahan("Apricot Kernel Oil", "0.1378", "0.1941"));
        daftarbahan.add(new ClassDataBahan("Avocado Butter", "0.1339", "0.1886"));
        daftarbahan.add(new ClassDataBahan("Avocado oil", "0.1337", "0.1883"));
        daftarbahan.add(new ClassDataBahan("Babassu Nut Oil", "0.1749", "0.2463"));
        daftarbahan.add(new ClassDataBahan("Beeswax Animal", "0.0689", "0.0970"));
        daftarbahan.add(new ClassDataBahan("Borage Oil", "0.1339", "0.1886"));
        daftarbahan.add(new ClassDataBahan("Candelilla Wax", "0.0322", "0.0454"));
        daftarbahan.add(new ClassDataBahan("Canola Oil", "0.1328", "0.1870"));
        daftarbahan.add(new ClassDataBahan("Canola Oil, High Oleic Acid", "0.1330", "0.1873"));
        daftarbahan.add(new ClassDataBahan("Castor Bean Oil", "0.1286", "0.1811"));
        daftarbahan.add(new ClassDataBahan("Cherry Kernel Oil", "0.1389", "0.1956"));
        daftarbahan.add(new ClassDataBahan("Chicken Fat Animal", "0.1356", "0.1910"));
        daftarbahan.add(new ClassDataBahan("Cocoa Butter", "0.1378", "0.1941"));
        daftarbahan.add(new ClassDataBahan("Coconut Oil, Refined 76°", "0.1910", "0.2690"));
        daftarbahan.add(new ClassDataBahan("Coconut Oil, Hydrogenated 92°", "0.1910", "0.2690"));
        daftarbahan.add(new ClassDataBahan("Coconut Oil, Fractionated/Saturated", "0.2321", "0.3269"));
        daftarbahan.add(new ClassDataBahan("Copha Vegetable Shortening", "0.1910", "0.2690"));
        daftarbahan.add(new ClassDataBahan("Corn Oil", "0.1368", "0.1927"));
        daftarbahan.add(new ClassDataBahan("Cottonseed Oil ", "0.1387", "0.1954"));
        daftarbahan.add(new ClassDataBahan("Crisco Vegetable Shortening", "0.1369", "0.1928"));
        daftarbahan.add(new ClassDataBahan("Emu Oil Animal", "0.1377", "0.1939"));
        daftarbahan.add(new ClassDataBahan("Evening Primrose Oil", "0.1362", "0.1918"));
        daftarbahan.add(new ClassDataBahan("Flaxseed Oil", "0.1358", "0.1913"));
        daftarbahan.add(new ClassDataBahan("Goat Fat Animal", "0.1382", "0.1946"));
        daftarbahan.add(new ClassDataBahan("Goose Fat Animal", "0.1349", "0.1900"));
        daftarbahan.add(new ClassDataBahan("Grapeseed Oil", "0.1321", "0.1861"));
        daftarbahan.add(new ClassDataBahan("Hazelnut Oil", "0.1369", "0.1928"));
        daftarbahan.add(new ClassDataBahan("Hempseed Oil", "0.1359", "0.1914"));
        daftarbahan.add(new ClassDataBahan("Jojoba Seed Oil", "0.0695", "0.0979"));
        daftarbahan.add(new ClassDataBahan("Jojoba Seed Liquid Wax", "0.0695", "0.0979"));
        daftarbahan.add(new ClassDataBahan("Karite Butter", "0.1296", "0.1825"));
        daftarbahan.add(new ClassDataBahan("Kremelta Vegetable Shortening", "0.1910", "0.2690"));
        daftarbahan.add(new ClassDataBahan("Kukui Nut Oil", "0.1351", "0.1903"));
        daftarbahan.add(new ClassDataBahan("Lanolin Animal", "0.0748", "0.1054"));
        daftarbahan.add(new ClassDataBahan("Lard Animal", "0.1399", "0.1970"));
        daftarbahan.add(new ClassDataBahan("Linseed Oil", "0.1358", "0.1913"));
        daftarbahan.add(new ClassDataBahan("Macadamia Nut Oil", "0.1391", "0.1959"));
        daftarbahan.add(new ClassDataBahan("Milk Fat Animal", "0.1599", "0.2252"));
        daftarbahan.add(new ClassDataBahan("Mink Oil Animal", "0.1403", "0.1976"));
        daftarbahan.add(new ClassDataBahan("Monoï de Tahiti Oil", "0.1796", "0.2530"));
        daftarbahan.add(new ClassDataBahan("Neem Tree Oil", "0.1372", "0.1932"));
        daftarbahan.add(new ClassDataBahan("Olive Oil", "0.1353", "0.1906"));
        daftarbahan.add(new ClassDataBahan("Ostrich Oil Animal", "0.1385", "0.1951"));
        daftarbahan.add(new ClassDataBahan("Palm Kernel Oil", "0.1777", "0.2503"));
        daftarbahan.add(new ClassDataBahan("Palm Oil", "0.1420", "0.2000"));
        daftarbahan.add(new ClassDataBahan("Peach Kernel Oil ", "0.1361", "0.1917"));
        daftarbahan.add(new ClassDataBahan("Peanut Oil", "0.1367", "0.1925"));
        daftarbahan.add(new ClassDataBahan("Pine Rosin", "0.1298", "0.1820"));
        daftarbahan.add(new ClassDataBahan("Pumpkin Seed Oil", "0.1389", "0.1956"));
        daftarbahan.add(new ClassDataBahan("Rapeseed Oil", "0.1328", "0.1870"));
        daftarbahan.add(new ClassDataBahan("Rice Bran Oil", "0.1284", "0.1808"));
        daftarbahan.add(new ClassDataBahan("Safflower Oil, High Linoleic Acid", "0.1374", "0.1935"));
        daftarbahan.add(new ClassDataBahan("Safflower Oil, High Oleic Acid", "0.1369", "0.1928"));
        daftarbahan.add(new ClassDataBahan("Sesame Seed Oil", "0.1336", "0.1882"));
        daftarbahan.add(new ClassDataBahan("Shea Butter", "0.1296", "0.1825"));
        daftarbahan.add(new ClassDataBahan("Soybean Oil", "0.1359", "0.1914"));
        daftarbahan.add(new ClassDataBahan("Soybean Oil, 27.5% Hydrogenated", "0.1361", "0.1917"));
        daftarbahan.add(new ClassDataBahan("Stearic Acid, Animal-Source Animal", "0.1413", "0.1990"));
        daftarbahan.add(new ClassDataBahan("Stearic Acid, Vegetable Source", "0.1411", "0.1987"));
        daftarbahan.add(new ClassDataBahan("Sunflower Seed Oil", "0.1358", "0.1913"));
        daftarbahan.add(new ClassDataBahan("Sunflower Seed Oil, High", "0.1351", "0.1903"));
        daftarbahan.add(new ClassDataBahan("Tallow, Beef Animal", "0.1419", "0.1999"));
        daftarbahan.add(new ClassDataBahan("Tallow, Deer Animal", "0.1382", "0.1946"));
        daftarbahan.add(new ClassDataBahan("Tallow, Sheep Animal", "0.1384", "0.1949"));
        daftarbahan.add(new ClassDataBahan("Tamanu Seed Oil", "0.1437", "0.2024"));
        daftarbahan.add(new ClassDataBahan("Tiaré Flower Oil", "0.1796", "0.2530"));
        daftarbahan.add(new ClassDataBahan("Walnut Oil", "0.1349", "0.1900"));
        daftarbahan.add(new ClassDataBahan("Wheat Germ Oil", "0.1319", "0.1858"));

        savedInstanceState = getArguments();

        if (savedInstanceState == null) {

        } else {

            judul = savedInstanceState.getString("isijudul");
            jenis = savedInstanceState.getString("isijenis");

        }

        isijudul.setText("Recipe title  : " + judul);
        isijenis.setText("Soap type     : " + jenis);


        //satuanukur.setText(satuan);

        Log.e("Recipe title ", ": " + judul);
        Log.e("Soap type ", ": " + jenis);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });


        pilihbahan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder((getContext()));
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.listisi, null);
                final ListView list = (ListView) dialogView.findViewById(R.id.listisidata);

                final IsiBahanList adapter = new IsiBahanList(getContext(), R.layout.rowisi, daftarbahan);
                list.setAdapter(adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        ClassDataBahan p = daftarbahan.get(i);
                        //pilihbahan1.setText(p.getBahan());

                        if (jenis.equals("Solid")) {

                            pilihbahan1.setText(p.getBahan());
                            //pilihbahan1.getText()p.getBahan();
                            nilai.setText(p.getNilai());

                            Log.e("keras", "" + jenis);

                        } else if (jenis.equals("Liquid")) {

                            pilihbahan1.setText(p.getBahan());
                            nilai.setText(p.getNilaikoh());

                            Log.e("cair", "" + pilihbahan1.getText());

                        }

                        alertDialog.dismiss();
                    }
                });

                builder.setTitle("Select materials\n");
                builder.setView(dialogView);

                alertDialog = builder.create();
                alertDialog.show();

            }
        });

        final int nilaibahan = nilai.getId() + 0;
        nilai.setId(nilaibahan);

        Log.e("nilai bahan", " " + nilaibahan);
        Log.e("nilai ukuran", " " + isiukuran.getId());


        kalkulasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getContext(), "kalkulasi di klik", Toast.LENGTH_SHORT).show();

                Utilities.putPrefName("tambahbahan", "text" + 0, "" + nilaibahan, getContext());
                Utilities.putPrefName("ukuranbahan", "text" + 0, "" + isiukuran.getId(), getContext());
                //Utilities.putPrefName("superfatting", "text" +0,  ""+isiukuran.getId(), getContext());

                Log.e("isi ukuran", " " + isiukuran.getText().toString());
//                Log.e("nlai", " " +nilai.getId());
//                Log.e("nlai bahan", " " +nilaibahan);
//                Log.e("nlai bahan", " " +isiukuran.getId());

//                if (kelembapan.getText().toString().length() == 0 || isiukuran.getText().toString().toString().length() == 0 || nilai.getText().toString().length() == 0 || isiukuran.getText().toString().length() == 0) {
//
//                    Toast.makeText(getContext(), "Please complete the contents", Toast.LENGTH_SHORT).show();
//
//                } else {

                    for (int i = 0; i < index; i++) {

                        Double convertbahan;
                        Double convertukuran;
                        Double convertkelembapan;

                        Log.e("index i", " :" + i);

                        String test = Utilities.getValueName("tambahbahan", "text" + i, getContext());
                        String valuebahan = Utilities.getValueName("ukuranbahan", "text" + i, getContext());

                        Log.e("test", " " + test);

                        int data = Integer.parseInt(test);

                        Log.e("data", " " + data);
                        TextView pilihbahan = view.findViewById(data);

                        Log.e(" pilih bahan", " " + pilihbahan);

                        String strBahan = pilihbahan.getText().toString().trim();
                        Log.e("str bahan", " " + strBahan);

                        int value = Integer.parseInt(valuebahan);
                        pilihbahan = view.findViewById(value);
                        String Ukuran = pilihbahan.getText().toString().trim();

                        Log.e(" pilih ukuran", " " + Ukuran);

//                        Log.e("test", " " + test.toString());
//                        Log.e("value", " " + valuebahan.toString());
//
//                        Log.e("Ukuran", " " + Ukuran.toString());
//                        Log.e("pilihbahan", " " + pilihbahan.getText().toString());
//                        Log.e("value bahan", " " + valuebahan);
//                        Log.e("Strbahan", " " + strBahan);


                        if (strBahan.equals("")  || Ukuran.equals("") || kelembapan.getText().toString().length() == 0 ) {

                            Log.e("plese comple content", "" +i);

                            Toast.makeText(getContext(), "Please complete the contents", Toast.LENGTH_SHORT).show();


                        } else {

                            Log.e("kondisi string", " " + strBahan.isEmpty());
//                            Log.e("kondisi string 2", " ");

                            convertbahan = Double.parseDouble(strBahan);

                            Log.e("convert value ke double", ": " + convertbahan + " _index : " + i);

                            convertukuran = Double.parseDouble(Ukuran);

                            Log.e("convert value ke double", ": " + convertukuran + " _index : " + i);

                            String fatting = kelembapan.getText().toString().trim() + ".0";

                            //intkelembapan = Integer.parseInt(fatting);

                            Log.e("Level super fatting", ": " + fatting);

                            convertkelembapan = Double.parseDouble(fatting);

                            Double hitungfatting;
                            //hitungfatting = 1.0 - convertkelembapan;

                            hitungfatting = 1.0 - (convertkelembapan / 100.0f);

                            Log.e("hasil hitung fatting", ": " + hitungfatting);

                            hasilconvert = convertbahan * convertukuran * hitungfatting;

//                            oilandfats.add(convertukuran);

                            totaloilandfats = totaloilandfats + convertukuran;

                            Log.e("total oil and fats", "" +totaloilandfats);

                            deret.add(hasilconvert);

                            Log.e("hasil convert", ": " + df4.format(hasilconvert) + " _index : " + i);

                            Log.e("deret", ": " + deret);

//                        }

                            for (int a = 0; a < deret.size(); a++) {

                                totallye = totallye + deret.get(a);

                                Log.e("total lye", "" +totallye);


                            }

                            Log.e("total lye", "\n " + df4.format((totallye)));

                            String hasillye = String.valueOf(df4.format(totallye));

                            Log.e("String total lye", "\n " + hasillye.toString());

                            Double weightoflye;
                            weightoflye = totallye / 0.3;

                            Log.e("weight of lye", "\n " + df4.format(weightoflye));
                            String strweightoflye = String.valueOf(df4.format(weightoflye));

                            Double amountofwater;
                            amountofwater = weightoflye - totallye;

                            Log.e("amount of water", "\n " + df4.format(amountofwater));
                            //df4.format(amountofwater);

                            String hasilamountofwater = String.valueOf(df4.format(amountofwater));

                            String hasiloilandfats = String.valueOf(df2.format(totaloilandfats));

                            Double amountlyeandliquid = weightoflye + amountofwater;

                            String hasillyeandliquid = String.valueOf(df2.format(amountlyeandliquid));

                            Log.e("String amount of water", "\n " + hasilamountofwater.toString());

                            Bundle bundle = new Bundle();
                            bundle.putString("judul", judul);
                            bundle.putString("jenis", jenis);
                            bundle.putString("totallye", hasillye.toString());
                            bundle.putString("amountofwater", hasilamountofwater.toString());
                            bundle.putString("oilandfats", hasiloilandfats.toString());
                            bundle.putString("weightoflye", strweightoflye.toString());
                            bundle.putString("amountlyeandliquid", hasillyeandliquid.toString());

                            fragment = new BahanFragHasil();

                            fragment.setArguments(bundle);
                            getFragmentManager().beginTransaction().replace(R.id.frame_bahan, fragment).commit();

                        }
                }
            }
        });

    }

    private void dinamic(final int mindex) {

        final LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View item = inflater.inflate(R.layout.rowpilihbahan, null);
        final TextView pilihbahan = (TextView) item.findViewById(R.id.pilihbahanrow);
        final EditText ukuran = (EditText) item.findViewById(R.id.isiukuranrow);
        final Button hapus = item.findViewById(R.id.hapus);

        final TextView nilai = (TextView) item.findViewById(R.id.isinilairow);

//        final LinearLayout hapusrow = item.findViewById(R.id.haha);

        LinearLayout idlistbahan = (LinearLayout) item.findViewById(R.id.id);

        int bahan = pilihbahan.getId() + mindex;

        int value = nilai.getId() + mindex;

        int ukur = ukuran.getId() + mindex;

        int id = idlistbahan.getId() + mindex;

        idlistbahan.setId(id);
        pilihbahan.setId(bahan);
        ukuran.setId(ukur);
        nilai.setId(value);

        Log.e("nomor mindex", "" + mindex);
        Log.e("masukkan ukuran", " : " + ukur);
        Log.e("masukkan nilai bahan", " : " + value);

        Utilities.putPrefName("tambahbahan", "text" + mindex, "" + value, getContext());
        Utilities.putPrefName("ukuranbahan", "text" + mindex, "" + ukur, getContext());

//        Utilities.putPrefName("tambahbahan", "text" +mindex,  ""+nilai, getContext());
//        Utilities.putPrefName("ukuranbahan", "text" +mindex,  ""+ukuran, getContext());

        //Utilities.putPrefName("nilaibahan", "text" +mindex,  ""+nilaibahan, getContext());

        //satuanukur.setText(testsatuan);

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapus(index--);

                rowpilihbahan.removeView(item);
            }
        });


        pilihbahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder((getContext()));
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.listisi, null);
                final ListView list = (ListView) dialogView.findViewById(R.id.listisidata);

                final IsiBahanList adapter = new IsiBahanList(getContext(), R.layout.rowisi, daftarbahan);
                list.setAdapter(adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ClassDataBahan p = daftarbahan.get(i);

                        if (jenis.equals("Solid")) {

                            pilihbahan.setText(p.getBahan());

                            nilai.setText(p.getNilai());
                            Log.e("keras", " : " + jenis);

                            //alertDialog.dismiss();

//                            Utilities.putPrefName("tambahbahan", "text" +mindex,  ""+nilai.getText().toString(), getContext());
//                            Utilities.putPrefName("ukuranbahan", "text" +mindex,  ""+ukuran.getText().toString(), getContext());

                        } else if (jenis.equals("Liquid")) {

                            pilihbahan.setText(p.getBahan());

                            nilai.setText(p.getNilaikoh());
                            Log.e("Liquid", " : " + jenis);

//                            alertDialog.dismiss();
//                            Log.e("pilih bahan",""+pilihbahan.getText());

//                            Utilities.putPrefName("tambahbahan", "text" +mindex,  ""+nilai.getId(), getContext());
//                            Utilities.putPrefName("ukuranbahan", "text" +mindex,  ""+ukuran.getId(), getContext());

                        }

                        alertDialog.dismiss();

                        Log.e("pilih bahan", " : " + pilihbahan.getText());
                        Log.e("nilah bahan", " : " + nilai.getText());


                    }
                });
                builder.setTitle("Select materials\n");
                builder.setView(dialogView);

                alertDialog = builder.create();
                alertDialog.show();

//                Log.e("nomor mindex 2",""+index);
//                Log.e("masukkan ukuran 2"," : "+nilai.getText().toString());
//                Log.e("masukkan nilai bahan 2"," : "+nilai.getText().toString());

            }
        });

        rowpilihbahan.addView(item);

    }

    private void hapus(int hapus) {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.rowpilihbahan, null);
        final TextView pilihbahan = (TextView) item.findViewById(R.id.pilihbahanawal);
        final EditText ukuran = (EditText) item.findViewById(R.id.isiukuran);

        LinearLayout idlistbahan = (LinearLayout) item.findViewById(R.id.id);

        int bahan = pilihbahan.getId() - hapus;

        int ukur = ukuran.getId() - hapus;

        int id = idlistbahan.getId() - hapus;

        idlistbahan.setId(id);
        pilihbahan.setId(bahan);
        ukuran.setId(ukur);

        Log.e("hapus index", "" + hapus--);

        rowpilihbahan.removeView(item);


    }

}
