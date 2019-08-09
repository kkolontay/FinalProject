package com.udacity.gradle.builditbigger.free;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.kkolontay.joke.JokeMaker;
import com.udacity.gradle.builditbigger.R;


public class MainActivityFragment extends Fragment {
    private Context context;
    private InterstitialAd mInterstitialAd;
    private Button tellJokeButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main_free, container, false);

        MobileAds.initialize(context, "ca-app-pub-7723881646850908~2569874788");
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        tellJokeButton = root.findViewById(R.id.tell_joke_button);
        tellJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tellJoke();
            }
        });
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    private void tellJoke() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        Toast.makeText(context, JokeMaker.maker(), Toast.LENGTH_SHORT).show();
    }
}
