package com.alexandreexample.spacexapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Height implements Serializable {

    public Height(float meters, float feet) {
        this.meters = meters;
        this.feet = feet;
    }

    @SerializedName("meters")
    @Expose
    private float meters;

    @SerializedName("feet")
    @Expose
    private float feet;

    public float getMeters() {
        return meters;
    }

    public float getFeet() {
        return feet;
    }


}
