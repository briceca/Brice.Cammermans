package com.example.brice.spaghettify;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AddTableActivity extends AppCompatActivity implements FragmentTableInput.OnInputSetListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddTableActivity.this,Table.class));
        finish();
    }

    @Override
    public void setVars(int x_as, int y_as, int nummer) {
        FragmentTableAdd f2 = (FragmentTableAdd) getFragmentManager().findFragmentById(R.id.fragment_add);
        f2.updateInfo(x_as, y_as, nummer);
    }

}
