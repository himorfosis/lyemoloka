package com.himorfosis.sabunin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by him on 4/20/2018.
 */

public class UtamaTabBookmark extends Fragment {

    Database db;
    AlertDialog alertDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        return inflater.inflate(R.layout.utamahasil, container, false);

    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
        getActivity().invalidateOptionsMenu();

        ListView listView = view.findViewById(R.id.listbookmark);

        String data[] = null;
        db = new Database(getContext());

        final List<BookmarkClassData> dataList = db.getallbookmark();
        data = new String[dataList.size()];

        int i = 0;

        for (BookmarkClassData b : dataList) {

            data[i] = b.getId_bookmark() + b.getJudul() + b.getJenis();

            i++;
        }


            final BookmarkListAdapter adapter = new BookmarkListAdapter(getContext(), dataList);


            Collections.reverse(dataList);

            listView.setAdapter(adapter);

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    final BookmarkClassData p = dataList.get(i);

                    builder.setTitle("Delete data");
                    builder.setMessage("Do you want to delete data ?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            db.deletebookmark(String.valueOf(p.getId_bookmark()));
//                        //fragment = new UtamaTabBookmark();
//                        FragmentTransaction ft = getFragmentManager().beginTransaction();
//                        ft.replace(R.id.frame_container, fragment);
//                        ft.commit();
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {


                        }
                    });

                    alertDialog = builder.create();
                    alertDialog.show();
                    return true;

                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    BookmarkClassData data = dataList.get(position);

                    //Toast.makeText(getContext(), "Data di klik", Toast.LENGTH_SHORT).show();

                    //Bundle bundle = new Bundle();
                    Intent lihat = new Intent(getContext(), LihatData.class);
                    lihat.putExtra("judul", " " + data.getJudul());
                    lihat.putExtra("jenis", " " + data.getJenis());
                    lihat.putExtra("lye", " " + data.getTotallye());
                    lihat.putExtra("amount", " " + data.getAmountofwater());

                    getActivity().startActivity(lihat);
//
//                Fragment f = new BookmarkLihatData();
//                f.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.frame_container, f).commit();

                    Log.e("judul", " " + data.getJudul());
                    Log.e("tanggal", " " + data.getTanggal());
                    Log.e("jenis", " " + data.getJenis());
                    Log.e("total lye", " " + data.getTotallye());
                    Log.e("amount of water", " " + data.getAmountofwater());


                    //Bundle bundle = new Bundle();
                }
            });


    }
}