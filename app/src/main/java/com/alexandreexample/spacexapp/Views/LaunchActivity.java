package com.alexandreexample.spacexapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alexandreexample.spacexapp.Models.Launch;
import com.alexandreexample.spacexapp.Models.Rocket;
import com.alexandreexample.spacexapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.alexandreexample.spacexapp.Controls.GithubService.githubService;

public class LaunchActivity extends AppCompatActivity {

    private TextView mTextView;
    private int flightNumber;
    private Launch launch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity);
        mTextView = findViewById(R.id.textView);

        Intent intent = getIntent();
        flightNumber = intent.getIntExtra("flightNumber", -1);
        executeRequest();

    }

    public void executeRequest() {
        githubService.getOneLaunch(flightNumber).enqueue(new Callback<Launch>() {
            @Override
            public void onResponse(Call<Launch> call, Response<Launch> response) {
                launch = response.body();
                String details = launch.getDetails();
                mTextView.setText(details);
            }
            @Override
            public void onFailure(Call<Launch> call, Throwable t) {
            }
        });
    }

}
