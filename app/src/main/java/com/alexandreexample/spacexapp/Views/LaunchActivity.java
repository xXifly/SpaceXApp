package com.alexandreexample.spacexapp.Views;

import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexandreexample.spacexapp.Models.Launch;
import com.alexandreexample.spacexapp.Models.Rocket;
import com.alexandreexample.spacexapp.R;
import com.bumptech.glide.Glide;
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

public class LaunchActivity extends AppCompatActivity implements ViewPagerEx.OnPageChangeListener, View.OnClickListener {

    private SliderLayout sliderLayout;
    private HashMap<String, String> HashMapForURL;
    private Launch launch;
    private ImageView youtube, wikipedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity);
        TextView mTextView = findViewById(R.id.textView);
        sliderLayout = findViewById(R.id.launchSlider);
        youtube = findViewById(R.id.youtube);
        wikipedia = findViewById(R.id.wikipedia);

        youtube.setOnClickListener(this);
        wikipedia.setOnClickListener(this);

        Intent intent = getIntent();
        launch = (Launch) intent.getSerializableExtra("data");

        setTitle(launch.getMissionName());
        mTextView.setText(launch.getDetails());

        initialiserSlider();


    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.youtube:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(launch.getLinks().getVideoLink()));
                startActivity(intent);
                break;
            case R.id.wikipedia:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(launch.getLinks().getWikipedia()));
                startActivity(intent);
                break;
        }
    }


    public void initialiserSlider() {
        sliderLayout.stopAutoCycle();

        // On définie la hauteur du slider pour qu'il soit proportionnel aux photos
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        sliderLayout.getLayoutParams().height = (int) (width * 0.667);

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
        HashMapForURL.put("Image " + 1, launch.getLinks().getMissionPatch());
        if(launch.getLinks().getFlickrImages().size() > 0){
            for (int i = 0; i < launch.getLinks().getFlickrImages().size(); i++) {
                HashMapForURL.put("Image " + (i+1), launch.getLinks().getFlickrImages().get(i));
            }
        }

    }


    @Override
    protected void onStop() {
        sliderLayout.stopAutoCycle();
        super.onStop();
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
