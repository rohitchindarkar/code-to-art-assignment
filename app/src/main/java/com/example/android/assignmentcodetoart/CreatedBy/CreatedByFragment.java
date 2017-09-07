/*

* Purpose – Class summary.

* @author

* Created on

* Modified on

*/
package com.example.android.assignmentcodetoart.CreatedBy;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.assignmentcodetoart.R;



public class CreatedByFragment extends Fragment {
    ViewGroup rootLayout;
    View mCustomView;
    private Toolbar toolbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootLayout = (ViewGroup)inflater.inflate(R.layout.fragment_created_by, container, false);

        // Attaching the layout to the toolbar object
        toolbar = (Toolbar) rootLayout.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);
        mCustomView =   ((AppCompatActivity) getActivity()).getLayoutInflater().inflate(R.layout.created_by_toolbar, null);
        toolbar.addView(mCustomView);



        return rootLayout;
    }

}
