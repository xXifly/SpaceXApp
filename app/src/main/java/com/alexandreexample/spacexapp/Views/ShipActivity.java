package com.alexandreexample.spacexapp.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexandreexample.spacexapp.Models.Ship;
import com.alexandreexample.spacexapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;


public class ShipActivity extends AppCompatActivity implements View.OnClickListener {

    private Ship ship;
    private ImageView mImageView;
    private RequestManager glide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ship_activity);

        TextView tvPort = findViewById(R.id.port);
        TextView tvStatus = findViewById(R.id.status);
        Button btnDetails = findViewById(R.id.details);
        mImageView = findViewById(R.id.imageView);

        btnDetails.setOnClickListener(this);

        Intent intent = getIntent();
        ship = (Ship) intent.getSerializableExtra("data");

        setTitle(ship.getShipName());

        setPicture();

        if(ship.getHomePort() != null){
            tvPort.setText(String.format("Port : %s", ship.getHomePort()));
        }
        if(ship.getStatus() != null) {
            tvStatus.setText(String.format("Status : %s", ship.getStatus()));
        }
        if(ship.getUrl() == null) {
            btnDetails.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.details:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(ship.getUrl()));
                startActivity(intent);
                break;
        }
    }

    public void setPicture() {
        Glide.with(this).load(ship.getImage()).into(mImageView);
    }

}
