package com.alexandreexample.spacexapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alexandreexample.spacexapp.Models.Capsule;
import com.alexandreexample.spacexapp.R;


public class CapsuleActivity extends AppCompatActivity {

    private TextView mTextView;
    private Capsule capsule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity);
        mTextView = findViewById(R.id.textView);

        Intent intent = getIntent();
        capsule = (Capsule) intent.getSerializableExtra("data");

        setTitle(capsule.getCapsuleId());
        mTextView.setText(capsule.getDetails());

    }
}
