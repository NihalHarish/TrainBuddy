package com.example.nihal.maindriver;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View layout_view;
    static long PNRValue = 0;


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout_view = inflater.inflate(R.layout.fragment_home, container, false);
        final Button pnrButton = (Button) layout_view.findViewById(R.id.pnr_button);
        pnrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText pnrNumber = (EditText)layout_view.findViewById(R.id.pnr_text);
                Editable editable = pnrNumber.getText();
                String text;
                text = editable == null ? "" : editable.toString();
                if(text.equalsIgnoreCase(""))
                    Toast.makeText(getActivity().getApplicationContext(), "Nothing to Show" , Toast.LENGTH_LONG).show();
                else{
                    Toast.makeText(getActivity().getApplicationContext(), "PNR SET TO:"+text , Toast.LENGTH_LONG).show();
                    PNRValue = Long.parseLong(text);
                }

            }
        });
        return layout_view;
    }

}
