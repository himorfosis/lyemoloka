package com.himorfosis.sabunin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by him on 4/10/2018.
 */

public class IsiBahanList extends ArrayAdapter<ClassDataBahan> {

    ArrayList<ClassDataBahan> daftarbahan = new ArrayList<>();

    public IsiBahanList(Context context, int textViewResourceId, ArrayList<ClassDataBahan> objects) {
        super(context, textViewResourceId, objects);
        daftarbahan = objects;

    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.rowisi, null);
        TextView isisenyap = (TextView) v.findViewById(R.id.tvisi);
        isisenyap.setText(daftarbahan.get(position).getBahan());

        return v;

    }
}
