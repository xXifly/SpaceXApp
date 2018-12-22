package com.alexandreexample.spacexapp.Views;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.widget.TextView;

import com.alexandreexample.spacexapp.Models.Rocket;
import com.alexandreexample.spacexapp.R;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.alexandreexample.spacexapp.Controls.GithubService.githubService;

public class RocketActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private TextView tv2;
    private SliderLayout sliderLayout;
    private HashMap<String, String> HashMapForURL;
    private String rocketId;
    private Rocket rocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rocket_activity);
        tv2 = findViewById(R.id.textView2);
        sliderLayout = findViewById(R.id.slider);

        Intent intent = getIntent();
        rocketId = intent.getStringExtra("rocketId");
        executeRequest();

    }

    public void executeRequest() {
        githubService.getOneRocket(rocketId).enqueue(new Callback<Rocket>() {
            @Override
            public void onResponse(Call<Rocket> call, Response<Rocket> response) {
                rocket = response.body();
                setTitle(rocket.getRocketName());
                tv2.setText(rocket.getDescription());
                initialiserSlider();
            }
            @Override
            public void onFailure(Call<Rocket> call, Throwable t) {
            }
        });
    }

    public void initialiserSlider() {
        sliderLayout.stopAutoCycle();

        // On définie la hauteur du slider pour qu'il soit proportionnel aux photos
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = (int) (width * 0.667);

        sliderLayout.getLayoutParams().height = height;

        addImageUrl();
        for (String name : HashMapForURL.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .image(HashMapForURL.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(this);
    }

    public void addImageUrl() {
        HashMapForURL = new HashMap<>();
        for (int i = 0; i < rocket.getFlickrImages().size(); i++) {
            HashMapForURL.put("Image " + i, rocket.getFlickrImages().get(i));
        }
    }

    @Override
    protected void onStop() {
        sliderLayout.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

}

