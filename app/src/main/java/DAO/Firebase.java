package DAO;

import com.google.firebase.database.FirebaseDatabase;

public class Firebase {
    public FirebaseDatabase database;
    public Firebase()
    {
        database = FirebaseDatabase.getInstance();
        //default constructor
    }
}
