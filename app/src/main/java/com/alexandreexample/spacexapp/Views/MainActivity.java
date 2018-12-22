package com.alexandreexample.spacexapp.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alexandreexample.spacexapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRockets = findViewById(R.id.btnRockets);
        Button btnLaunches = findViewById(R.id.btnLaunches);
        Button btnShips = findViewById(R.id.btnShips);

        btnRockets.setOnClickListener(this);
        btnLaunches.setOnClickListener(this);
        btnShips.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnRockets:
                intent = new Intent(this, ListActivity.class);
                intent.putExtra("toShow", "rocket");
                startActivity(intent);
                break;
            case R.id.btnLaunches:
                intent = new Intent(this, ListActivity.class);
                intent.putExtra("toShow", "launch");
                startActivity(intent);
                break;
            case R.id.btnShips:
                intent = new Intent(this, ListActivity.class);
                intent.putExtra("toShow", "ship");
                startActivity(intent);
                break;
        }
    }
}