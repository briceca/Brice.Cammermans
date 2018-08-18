package com.example.brice.spaghettify;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import DAO.CRUD;
import Data.Tafel;

public class FragmentTableAdd extends Fragment {
    TextView textViewX_as, textViewY_as, textViewNummer;
    Button btnAdd;
    int x_as, y_as, nummer;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table_add, container, false);
        textViewX_as = (TextView) view.findViewById(R.id.textView_x_as);
        textViewY_as = (TextView) view.findViewById(R.id.textView_y_as);
        textViewNummer = (TextView) view.findViewById(R.id.textView_nummer);
        btnAdd = (Button) view.findViewById(R.id.button_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tafel tafel = new Tafel(x_as, y_as, true, "",nummer);
                CRUD crud = new CRUD();
                crud.addTafel(tafel);
            }
        });
        textViewX_as.setVisibility(View.GONE);
        textViewY_as.setVisibility(View.GONE);
        textViewNummer.setVisibility(View.GONE);
        btnAdd.setVisibility(View.GONE);
        return view;
    }
    public void updateInfo(int x_as, int y_as, int nummer)
    {
        textViewX_as.setText("x_as: "+x_as);
        textViewY_as.setText("y_as: "+y_as);
        textViewNummer.setText("nummer: "+nummer);
        textViewX_as.setVisibility(View.VISIBLE);
        textViewY_as.setVisibility(View.VISIBLE);
        textViewNummer.setVisibility(View.VISIBLE);
        this.x_as = x_as;
        this.y_as = y_as;
        this.nummer = nummer;
    }
}
