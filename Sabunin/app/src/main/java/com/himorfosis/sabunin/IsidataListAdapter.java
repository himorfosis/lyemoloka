package com.himorfosis.sabunin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by him on 4/6/2018.
 */

public class IsidataListAdapter extends ArrayAdapter<ClassDataList> {

    ArrayList<ClassDataList> arrayisi = new ArrayList<>();

    public IsidataListAdapter(Context context, int textViewResourceId, ArrayList<ClassDataList> objects) {
        super(context, textViewResourceId, objects);
        arrayisi = objects;

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
            isisenyap.setText(arrayisi.get(position).getmenu());

            return v;

    }
}
