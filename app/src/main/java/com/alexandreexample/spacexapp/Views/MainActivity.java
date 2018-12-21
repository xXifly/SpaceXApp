package com.alexandreexample.spacexapp.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alexandreexample.spacexapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRockets;
    private Button btnLaunches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRockets = findViewById(R.id.btnRockets);
        btnLaunches = findViewById(R.id.btnLaunches);

        btnRockets.setOnClickListener(this);
        btnLaunches.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btnRockets:
                intent = new Intent(this, ListActivity.class);
                intent.putExtra("toShow", 1);
                startActivity(intent);
                break;
            case R.id.btnLaunches:
                intent = new Intent(this, ListActivity.class);
                intent.putExtra("toShow", 2);
                startActivity(intent);
                break;
        }
    }
}