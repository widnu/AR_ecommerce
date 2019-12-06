package com.mobile.info.ar_ecommerce_assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LandingPageViedo extends AppCompatActivity {
    private VideoView mVideoView;
    MediaController mediaControls;
    private Handler mHandler = new Handler();
    private Animation animFadeIn;
    private TextView text_a;
    private TextView text_r;
    private TextView text_jwellery;
    private ImageView img_ar;
    private Animation animFadeOUT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_layout);
        text_a = (TextView) findViewById(R.id.a);
        text_r = (TextView) findViewById(R.id.r);
        text_jwellery = (TextView) findViewById(R.id.jwellery);
        img_ar = (ImageView) findViewById(R.id.landing_image);
        /*
         * fade in animation on textview
         */
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        animFadeOUT = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_out);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                text_a.startAnimation(animFadeIn);
                text_r.startAnimation(animFadeIn);
                text_jwellery.startAnimation(animFadeIn);


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        img_ar.startAnimation(animFadeOUT);

                        Intent i = new Intent(LandingPageViedo.this, MainActivity.class);
                        startActivity(i);
                    }
                }).start();


            }
        }, 4000);

    }
}
