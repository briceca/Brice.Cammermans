package com.example.brice.spaghettify;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import DAO.CRUD;
import Data.Artikel;
import Data.ArtikelList;
import Data.Tafel;
import Data.Transactie;
import Data.User;

public class TransactieActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    Transactie transactie;
    List<Artikel> artikels;
    CRUD crud;

    //our database reference object
    DatabaseReference databaseArtikels;
    DatabaseReference getDatabaseTransactie;
    ListView listViewArtikels;
    TextView lbl;
    Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactie);
        auth= FirebaseAuth.getInstance();


        crud = new CRUD();
        databaseArtikels = crud.getDb().database.getReference("Artikels");
        LinearLayout transactieLayout = (LinearLayout) findViewById(R.id.activity_transactie);

        artikels = new ArrayList<>();
        transactieLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        transactieLayout.setGravity(Gravity.CENTER_VERTICAL);
        transactieLayout.setPadding(30,60,30,60);
        transactieLayout.setOrientation(LinearLayout.VERTICAL);
        transactieLayout.setBackgroundColor(Color.parseColor("#baffbf"));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        listViewArtikels = new ListView(this);
        listViewArtikels.setMinimumWidth(700);
        backBtn = new Button(this);
        backBtn.setText("Back");
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransactieActivity.this,Table.class));
                finish();
            }
        });
        lbl = new TextView(this);
        lbl.setMinimumWidth(700);

        Tafel tafel = getIntent().getParcelableExtra("Table");

        User user=new User(auth.getCurrentUser().getUid());

        if(tafel.getTransactieId()!= "" && !tafel.getTransactieId().isEmpty())
        {
            //getDatabaseTransactie = crud.getDb().database.getReference("Transacties").child(tafel.getTransactieId());
        }
        else
        {
            transactie = new Transactie(String.valueOf(tafel.getNummer()), user, tafel);
            tafel.setTransactieId(transactie.getId());
            crud.addTransactie(transactie);
            crud.updateTafelTransactie(tafel);
        }
        TextView trans = new TextView(this);
        trans.setText("tafel "+String.valueOf(tafel.getNummer()));
        layoutParams.gravity = Gravity.LEFT;
        layoutParams.setMargins(0,20,0,20);

        transactieLayout.addView(lbl, layoutParams);
        transactieLayout.addView(backBtn, layoutParams);
        transactieLayout.addView(trans, layoutParams);
        transactieLayout.addView(listViewArtikels, layoutParams);
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        //attaching value event listener
        databaseArtikels.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LoadThread load = new LoadThread(dataSnapshot);
                load.start();
                while (load.isAlive())
                {
                    //wachten op thread
                }
                ArtikelList artikelAdapter = new ArtikelList(TransactieActivity.this, artikels);
                listViewArtikels.setAdapter(artikelAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(TransactieActivity.this,Table.class));
    }
    class LoadThread extends Thread{

        DataSnapshot dataSnapshot;
        public LoadThread(DataSnapshot d) {
            this.dataSnapshot = d;
        }

        @Override
        public void run()
        {
            artikels.clear();

            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                Artikel artikel = postSnapshot.getValue(Artikel.class);
                artikels.add(artikel);
            }

        }
    }
}
