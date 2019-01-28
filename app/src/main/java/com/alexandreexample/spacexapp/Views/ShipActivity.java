package com.alexandreexample.spacexapp.Views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexandreexample.spacexapp.Models.Ship;
import com.alexandreexample.spacexapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ShipActivity extends AppCompatActivity {

    Ship ship;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ship_activity);

        TextView mTextView = findViewById(R.id.textView);
        mImageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        ship = (Ship) intent.getSerializableExtra("data");

        setTitle(ship.getShipName());

        //setPicture();

    }

    public void setPicture() {
        try {
            URL url = new URL(ship.getImage());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            //connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            mImageView.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
