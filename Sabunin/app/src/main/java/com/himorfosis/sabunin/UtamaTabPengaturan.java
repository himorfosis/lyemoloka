package com.himorfosis.sabunin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by him on 4/5/2018.
 */

public class UtamaTabPengaturan extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        return inflater.inflate(R.layout.utamapengaturan, container, false);

    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
        getActivity().invalidateOptionsMenu();

        TextView tentang = (TextView) view.findViewById(R.id.isitentang);


        tentang.setText("Moloka Farm Living is a goat milk base artisanal product with the spirit of promoting healthy and sustainable food and eco-friendly products. Established in 2012 with goat milk soap as a market entry and to educate the market about natural soap in order to promote our product, start teaching soap making class in 2014. Now with over hundreds of student, we hope we contribute to increase awareness of creating a healthy product with less chemical and taking care our environment even from the smallest step!");

    }
}
