package com.example.brice.spaghettify;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import android.graphics.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Data.Tafel;
import DAO.CRUD;

import static java.lang.Integer.parseInt;

//genereren van views uit firebase: https://www.simplifiedcoding.net/firebase-realtime-database-crud/
public class Table extends AppCompatActivity {

    public static final String TABLE_NUMBER = "net.simplifiedcoding.firebasedatabaseexample.tablenumber";
    public static final String TABLE_ID = "net.simplifiedcoding.firebasedatabaseexample.tableid";
    Button addBtn;
    ArrayList<Button> btns;
    ArrayList<Tafel> tafels;
    CRUD crud;
    DatabaseReference databaseTafels;
    EditText lbl;
    LinearLayout tableLayout;
    Context context;
    LinearLayout.LayoutParams layoutParams;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        crud = new CRUD();
        databaseTafels = crud.getDb().database.getReference("Tafels");
        tableLayout = (LinearLayout) findViewById(R.id.activity_table);
        btns = new ArrayList<Button>();
        tafels = new ArrayList<Tafel>();
        context = this;
        tableLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        tableLayout.setGravity(Gravity.TOP);
        tableLayout.setPadding(30,60,30,60);
        tableLayout.setOrientation(LinearLayout.VERTICAL);
        tableLayout.setBackgroundColor(Color.parseColor("#baffbf"));
        addBtn = new Button(this);
        addBtn.setText("add table");
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Table.this,AddTableActivity.class));
                finish();
            }
        });
        layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        lbl = new EditText(this);
        lbl.setMinimumWidth(700);
        lbl.setText("tables");

        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        layoutParams.setMargins(0,20,0,20);
        tableLayout.addView(addBtn, layoutParams);
        tableLayout.addView(lbl, layoutParams);
    }
   @Override
    protected void onStart()
    {
        super.onStart();
        databaseTafels.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (Button btn:btns)
                {
                    tableLayout.removeView(btn);
                }
                btns.clear();
                tafels.clear();
                for (DataSnapshot tafelsSnapshot : dataSnapshot.getChildren())
                {
                    String id;
                    int x_as;
                    int y_as;
                    boolean bezet;
                    final String transactieId;
                    int nummer;
                    if(tafelsSnapshot.hasChild("id"))
                    {
                        id = tafelsSnapshot.child("id").getValue().toString();

                    }
                    else
                    {
                        id = null;
                    }
                    if(tafelsSnapshot.hasChild("x_as"))
                    {
                        x_as = parseInt(tafelsSnapshot.child("x_as").getValue().toString());

                    }
                    else
                    {
                        x_as = 0;
                    }
                    if(tafelsSnapshot.hasChild("y_as"))
                    {
                        y_as = parseInt(tafelsSnapshot.child("y_as").getValue().toString());
                    }
                    else
                    {
                        y_as = 0;
                    }
                    if(tafelsSnapshot.hasChild("bezet"))
                    {
                        bezet = Boolean.parseBoolean(tafelsSnapshot.child("bezet").getValue().toString());
                    }
                    else
                    {
                        bezet = false;
                    }
                    if(tafelsSnapshot.hasChild("transactie"))
                    {
                        transactieId = tafelsSnapshot.child("transactie").getValue().toString();

                    }
                    else
                    {
                        transactieId = "";
                    }
                    if(tafelsSnapshot.hasChild("nummer"))
                    {
                        nummer = parseInt(tafelsSnapshot.child("nummer").getValue().toString());
                    }
                    else
                    {
                        nummer = 0;
                    }
                    final Tafel tafel = new Tafel(id, x_as, y_as, bezet,transactieId,nummer);
                    tafels.add(tafel);
                    Button btn = new Button(context);
                    btn.setText(String.valueOf(tafel.getNummer()));
                    btn.setTag(tafel);
                    btn.setHeight(50);
                    btn.setWidth(50);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Button btn = (Button)v;
                            if(btn.getTag()==null)
                            {
                                Log.d("tag", "leeg");
                            }
                            else
                            {
                                Tafel t = (Tafel) btn.getTag();
                                Intent intent = new Intent(Table.this,TransactieActivity.class);
                                intent.putExtra("Table", t);
                                startActivity(intent);
                            }



                        }
                    });
                    btns.add(btn);
                }
                for (Button btn:btns)
                {
                    tableLayout.addView(btn,layoutParams);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
