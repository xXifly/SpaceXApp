package com.alexandreexample.spacexapp.Controls;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexandreexample.spacexapp.Models.Launch;
import com.alexandreexample.spacexapp.Models.Rocket;
import com.alexandreexample.spacexapp.R;
import com.bumptech.glide.RequestManager;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>  {

    private RequestManager glide;
    private List<Rocket> rockets;
    private List<Launch> launches;
    private int toShow;

    // CONSTRUCTOR
    public RecyclerViewAdapter(RequestManager glide, List<Rocket> rockets, List<Launch> launches, int toShow) {
        this.rockets = rockets;
        this.launches = launches;
        this.glide = glide;
        this.toShow = toShow;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list, parent, false);
        return new RecyclerViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        switch (toShow) {
            case 1 :
                holder.RocketsDisplay(rockets.get(position), this.glide);
                break;
            case 2 :
                holder.LaunchesDisplay(launches.get(position), this.glide);
                break;
        }

    }

    @Override
    public int getItemCount() {
        int count = 0;
        switch (toShow) {
            case 1 :
                count = rockets.size();
                break;
            case 2 :
                count = launches.size();
                break;
        }
        return count;
    }


}
