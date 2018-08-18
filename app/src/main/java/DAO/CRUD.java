package DAO;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Data.Artikel;
import Data.Tafel;
import Data.Transactie;
import Data.Type;

public class CRUD {
    private Firebase db;
    public CRUD()
    {
        db = new Firebase();
    }
    public void addArtikel(Artikel artikel)
    {
        DatabaseReference artikelRef = db.database.getReference("Artikels");
        artikel.setId(artikelRef.push().getKey());
        assert artikel.getId() != null;
        artikelRef.child(artikel.getId()).setValue(artikel);
    }

    public void addTafel(Tafel tafel)
    {
        DatabaseReference tafelRef = db.database.getReference("Tafels");
        tafel.setId(tafelRef.push().getKey());
        assert tafel.getId() != null;
        tafelRef.child(tafel.getId()).setValue(tafel);
    }
    public void updateTafelTransactie(Tafel tafel)
    {
        DatabaseReference tafelRef = db.database.getReference("Tafels/"+tafel.getId());
        tafelRef.setValue(tafel);

    }
    public void addTransactie(Transactie transactie)
    {
        DatabaseReference transactieRef = db.database.getReference("Transacties");
        transactie.setId(transactieRef.push().getKey());
        assert transactie.getId() != null;
        transactieRef.child(transactie.getId()).setValue(transactie);
    }

    public Firebase getDb() {
        return db;
    }
}
