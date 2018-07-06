package com.himorfosis.sabunin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HitungBahan extends AppCompatActivity {


    //menampilkan nilai
    public static DecimalFormat df4 = new DecimalFormat("#.####");
    public static DecimalFormat df2 = new DecimalFormat("##.##");

    AlertDialog alertDialog;

    Double amountlye = 0.0;
    int index = 0;

    //get data from utamafraghom
    String getjudul, getjenis;

    TextView judul, jenis, pilihBahan, isiNilai;
    EditText fatting, beratbahan;
    Button kalkulasi;

    AlertDialog.Builder builder;
    LayoutInflater inflater;

    //pilihbahan
//    String nilaiBahan;

    // tambah bahan
    LinearLayout tambahBahan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hitungbahan);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        TextView toolbarText = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbartext);
        toolbarText.setText("Ingredients");

        final Intent intent = getIntent();
        getjudul = intent.getStringExtra("judul");
        getjenis = intent.getStringExtra("jenis");

        judul = (TextView) findViewById(R.id.isijudul);
        jenis = (TextView) findViewById(R.id.isijenis);
        fatting = findViewById(R.id.tingkat);
        beratbahan = findViewById(R.id.isiukuran);
        kalkulasi = (Button) findViewById(R.id.kalkulasi);
        pilihBahan = (TextView) findViewById(R.id.pilihbahanawal);
        Button btnbahan = (Button) findViewById(R.id.tambahbahan);
        tambahBahan = (LinearLayout) findViewById(R.id.addbahan);
        isiNilai = (TextView) findViewById(R.id.isinilai);

        judul.setText("Recipe title  : " +getjudul);
        jenis.setText("Soap type     : " +getjenis);

        index++;

        btnbahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dinamic(index++);
            }
        });

        pilihBahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder((HitungBahan.this));
                inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.listisi, null);
                ListView list = (ListView) dialogView.findViewById(R.id.listisidata);

                IsiBahanList adapter = new IsiBahanList(getApplicationContext(), R.layout.rowisi, DaftarBahan.daftarbahan());
                list.setAdapter(adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        ClassDataBahan data = DaftarBahan.daftarbahan().get(position);

                        if (getjenis.equals("Solid")) {

                            pilihBahan.setText(data.getBahan());
                            isiNilai.setText(data.getNilai());

                            Log.e("keras", "" + getjenis);
                            Log.e("nilai bahan", "" + isiNilai.getText().toString());

                        } else if (getjenis.equals("Liquid")) {

                            pilihBahan.setText(data.getBahan());
                            isiNilai.setText(data.getNilaikoh());

                            Log.e("cair", "" + pilihBahan.getText());
                            Log.e("nilai bahan", "" + isiNilai.getText().toString());

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

        final int nilaiBahan = isiNilai.getId() + 0;
        isiNilai.setId(nilaiBahan);


        kalkulasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utilities.putPrefName("nilaibahan", "text" + 0, "" + nilaiBahan, getApplicationContext());
                Utilities.putPrefName("beratbahan", "text" + 0, "" + beratbahan.getId(), getApplicationContext());

                ArrayList<Double> lye = new ArrayList<Double>();

                Double convertMaterial;
                Double convertWeight;
                Double convertFatting;
                Double hitungFatting;
                Double hitungLye;
                Double amountOfLye = 0.0;
                Double totalOilAndFats = 0.0;

                Double weightLyeWaterSolution;
                Double amountOfWater;

                if (fatting.getText().toString().equals("") || isiNilai.getText().toString().equals("") || pilihBahan.getText().toString().equals("") || isiNilai.getText().toString().equals("") || beratbahan.getText().toString().equals("")) {

                    Toast.makeText(getApplicationContext(), "Please complete the contents", Toast.LENGTH_SHORT).show();

                } else {

                    for (int i = 0; i < index; i++) {

                        String hasilNilaiBahan = Utilities.getValueName("nilaibahan", "text" + i, getApplicationContext());
                        String hasilBeratBahan = Utilities.getValueName("beratbahan", "text" + i, getApplicationContext());

                        if (hasilNilaiBahan.equals("") || hasilBeratBahan.equals("") ) {

                            Toast.makeText(getApplicationContext(), "Please complete the contents", Toast.LENGTH_SHORT).show();

                        }

                        Log.e("hasil nilai bahan","" +hasilNilaiBahan +" index : " +i);
                        Log.e("hasil berat bahan","" +hasilBeratBahan +" index : " +i);

                    }

                    for (int i = 0; i < index; i++) {

                        Log.e("index", "" +index);

                        String dataNilaiBahan = Utilities.getValueName("nilaibahan", "text" + i, getApplicationContext());
                        String dataBeratBahan = Utilities.getValueName("beratbahan", "text" + i, getApplicationContext());

                        // get data from preference

                        int dataNilai = Integer.parseInt(dataNilaiBahan);
                        int dataBerat = Integer.parseInt(dataBeratBahan);

                        // convert data to ID

                        TextView hasilNilaiBahan = findViewById(dataNilai);
                        TextView hasilBeratBahan = findViewById(dataBerat);

                        String strFatting = fatting.getText().toString().trim() + ".0";

                        convertMaterial = Double.parseDouble(hasilNilaiBahan.getText().toString());
                        convertWeight = Double.parseDouble(hasilBeratBahan.getText().toString());
                        convertFatting = Double.parseDouble(strFatting);

                        Log.e("convert material", "" + convertMaterial);
                        Log.e("convert weight", "" + convertWeight);
                        Log.e("convert fatting", "" + convertFatting);

                        hitungFatting = 1.0 - (convertFatting / 100.0f);

                        Log.e("hitung fatting", "" +df4.format(hitungFatting));

                        hitungLye = convertMaterial * convertWeight * hitungFatting;

                        Log.e("hitung lye", "" +df4.format(hitungLye));

                        lye.add(hitungLye);

                        Log.e("hasil lye", "" +df4.format(hitungLye));

                        Log.e("deret", "" +lye);

                    }

                    for (int i = 0; i < lye.size(); i++) {

                        amountOfLye = amountOfLye + lye.get(i);

                        Log.e("amount of lye", "" +amountOfLye);

                    }


                    weightLyeWaterSolution = amountOfLye / 0.3;

                    Log.e("weight lye water", "" +df4.format(weightLyeWaterSolution));

                    amountOfWater = weightLyeWaterSolution - amountOfLye;

                    Log.e("amount of water", ""+amountOfWater);

                    String strAmountOfWater = String.valueOf(df2.format(amountOfWater));
                    String strAmountOfLye= String.valueOf(df2.format(amountOfLye));

                    Log.e("str amount of water", "" +strAmountOfWater);
                    Log.e("str amount of lye", "" +strAmountOfLye);

                    Intent intent = new Intent(getApplicationContext(), HasilHitungBahan.class);
                    intent.putExtra("judul", getjudul);
                    intent.putExtra("jenis", getjenis);
                    intent.putExtra("amountoflye", strAmountOfLye);
                    intent.putExtra("amountofwater", strAmountOfWater);

                    startActivity(intent);

                }

            }
        });

    }

    private void dinamic(final int mindex) {

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View item = inflater.inflate(R.layout.rowpilihbahan, null);
        final TextView pilihBahanDinamic = (TextView) item.findViewById(R.id.pilihbahanrow);
        final EditText ukuran = (EditText) item.findViewById(R.id.isiukuranrow);
        final Button hapus = item.findViewById(R.id.hapus);

        final TextView nilai = (TextView) item.findViewById(R.id.isinilairow);
        final LinearLayout hapusrow = item.findViewById(R.id.addbahan);
        LinearLayout idlistbahan = (LinearLayout) item.findViewById(R.id.id);

        int bahan = pilihBahanDinamic.getId() + mindex;
        int value = nilai.getId() + mindex;
        int ukur = ukuran.getId() + mindex;
        int id = idlistbahan.getId() + mindex;

        idlistbahan.setId(id);
        pilihBahanDinamic.setId(bahan);
        ukuran.setId(ukur);
        nilai.setId(value);

        Log.e("nomor mindex", "" + mindex);
        Log.e("masukkan ukuran", " : " + ukur);
        Log.e("masukkan nilai bahan", " : " + value);

        Utilities.putPrefName("nilaibahan", "text" + mindex, "" + value, getApplicationContext());
        Utilities.putPrefName("beratbahan", "text" + mindex, "" + ukur, getApplicationContext());


        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapus(index--);

                tambahBahan.removeView(item);
            }
        });


        pilihBahanDinamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder((HitungBahan.this));
                inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.listisi, null);
                final ListView list = (ListView) dialogView.findViewById(R.id.listisidata);

                final IsiBahanList adapter = new IsiBahanList(getApplicationContext(), R.layout.rowisi, DaftarBahan.daftarbahan());
                list.setAdapter(adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        ClassDataBahan p = DaftarBahan.daftarbahan().get(i);

                        if (getjenis.equals("Solid")) {

                            pilihBahanDinamic.setText(p.getBahan());
                            nilai.setText(p.getNilai());

                            Log.e("keras", " : " + getjenis);


                        } else if (getjenis.equals("Liquid")) {

                            pilihBahanDinamic.setText(p.getBahan());
                            nilai.setText(p.getNilaikoh());

                            Log.e("Liquid", " : " + getjenis);


                        }

                        alertDialog.dismiss();

                        Log.e("pilih bahan", " : " + pilihBahanDinamic.getText());
                        Log.e("nilah bahan", " : " + nilai.getText());


                    }
                });

                builder.setTitle("Select materials\n");
                builder.setView(dialogView);

                alertDialog = builder.create();
                alertDialog.show();

            }
        });

        tambahBahan.addView(item);

    }

    private void hapus(int hapus) {

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.rowpilihbahan, null);
        final TextView pilihbahan = (TextView) item.findViewById(R.id.pilihbahanrow);
        final EditText ukuran = (EditText) item.findViewById(R.id.isiukuranrow);

        LinearLayout idlistbahan = (LinearLayout) item.findViewById(R.id.id);

        int bahan = pilihbahan.getId() - hapus;
        int ukur = ukuran.getId() - hapus;
        int id = idlistbahan.getId() - hapus;
//
//        int dihapus = idlistbahan.getBaseline() - hapus;

//        ((ViewGroup) item.getParent()).removeView(item);

        idlistbahan.setId(id);
        pilihbahan.setId(bahan);
        ukuran.setId(ukur);

        Log.e("hapus index", "" + hapus--);

        tambahBahan.removeView(item);


    }
}
