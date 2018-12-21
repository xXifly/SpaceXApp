package com.alexandreexample.spacexapp.Controls;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexandreexample.spacexapp.Models.Launch;
import com.alexandreexample.spacexapp.Models.Rocket;
import com.alexandreexample.spacexapp.R;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView myRocketName;
    private ImageView myPicture;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        myRocketName = itemView.findViewById(R.id.rocket_name);
        myPicture = itemView.findViewById(R.id.picture);
    }

    void RocketsDisplay(Rocket rocket, RequestManager glide) {
        this.myRocketName.setText(rocket.getRocketName());
        glide.load(rocket.getFlickrImages().get(0)).apply(RequestOptions.circleCropTransform()).into(myPicture);
    }

    void LaunchesDisplay(Launch launches, RequestManager glide) {
        this.myRocketName.setText(launches.getMissionName());
        glide.load(launches.getLinks().getMissionPatchSmall()).apply(RequestOptions.circleCropTransform()).into(myPicture);
    }

}
