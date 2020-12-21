package com.example.daggerhilt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager2 locationsViewPager = findViewById(R.id.locationsViewPager);
        List<TravelLocation> travelLocationList = new ArrayList<>();

        TravelLocation travelLocationEiffelTower = new TravelLocation();
        travelLocationEiffelTower.imageUrl = "http://www.infinityandroid.com/images/france_eiffel_tower.jpg";
        travelLocationEiffelTower.title = "France";
        travelLocationEiffelTower.location = "Eiffel Tower";
        travelLocationEiffelTower.startRating = 4.8f;
        travelLocationList.add(travelLocationEiffelTower);

        TravelLocation travelLocationMountainView = new TravelLocation();
        travelLocationMountainView.imageUrl = "http://www.infinityandroid.com/images/indonesia_mountain_view.jpg";
        travelLocationMountainView.title = "Indonesia";
        travelLocationMountainView.location = "Mountain View";
        travelLocationMountainView.startRating = 4.5f;
        travelLocationList.add(travelLocationMountainView);

        TravelLocation travelLocationTajMahal = new TravelLocation();
        travelLocationTajMahal.imageUrl = "http://www.infinityandroid.com/images/india_taj_mahal.jpg";
        travelLocationTajMahal.title = "India";
        travelLocationTajMahal.location = "Taj Mahal";
        travelLocationTajMahal.startRating = 4.3f;
        travelLocationList.add(travelLocationTajMahal);

        TravelLocation travelLocationLakeView = new TravelLocation();
        travelLocationLakeView.imageUrl = "http://www.infinityandroid.com/images/canada_lake_view.jpg";
        travelLocationLakeView.title = "Canada";
        travelLocationLakeView.location = "Lake View";
        travelLocationLakeView.startRating = 4.2f;
        travelLocationList.add(travelLocationLakeView);

        locationsViewPager.setAdapter(new TravelLocationsAdapter(travelLocationList));

        locationsViewPager.setClipToPadding(false);
        locationsViewPager.setClipChildren(false);
        locationsViewPager.setOffscreenPageLimit(3);
        locationsViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.95f + r *0.05f);
            }
        });

        locationsViewPager.setPageTransformer(compositePageTransformer);
    }
}