package com.example.mybookmarks;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdMobAdListenerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdMobAdListenerFragment extends Fragment {
    private AdView adView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ad_mob_ad_listener, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adView = getView().findViewById(R.id.bannerView);
        adView.setAdListener(
                new AdListener(){
                    private void showToast(String message){
                        View view = getView();
                        if(view != null){
                            Toast.makeText(getView().getContext(), ""+ message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onAdLoaded() {
                        showToast("Ad loaded.");
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        String error = String.format("domain: %s, code: %d, message: %s",
                                loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());
                        showToast(String.format("Ad failed to load with error %s", error));
                    }

                    @Override
                    public void onAdOpened() {
                        showToast("Ad opened.");
                    }

                    @Override
                    public void onAdClosed() {
                        showToast("Ad closed.");
                    }

                }
        );
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }
}