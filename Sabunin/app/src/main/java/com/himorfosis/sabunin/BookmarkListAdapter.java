package com.himorfosis.sabunin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by him on 4/20/2018.
 */

public class BookmarkListAdapter extends ArrayAdapter<BookmarkClassData> {
    private Context context;
    private List<BookmarkClassData> list;
    Database db;

    String totallye;
    String amountoflye;

    public BookmarkListAdapter (Context context, List<BookmarkClassData> list) {

        super(context,R.layout.bookmarkrowlist, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public long getItemId ( int position) {
        return position;
    }

    @Override
    public View getView (int position, View view, ViewGroup parent) {

        View v = view;
        if ( v  == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.bookmarkrowlist, null);
        }

        BookmarkClassData p = list.get(position);

        db = new Database(getContext());

        if (p != null) {

            TextView tanggal = v.findViewById(R.id.tanggal);
            TextView judul = v.findViewById(R.id.judul);
            TextView jenis = v.findViewById(R.id.jenis);

            tanggal.setText(p.getTanggal());
            judul.setText(p.getJudul());
            jenis.setText(p.getJenis());

        }

        return v;
    }

}
