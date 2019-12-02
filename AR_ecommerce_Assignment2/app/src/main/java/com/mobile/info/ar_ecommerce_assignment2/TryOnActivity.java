package com.mobile.info.ar_ecommerce_assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mobile.info.ar_ecommerce_assignment2.sceneform.ARDropObjectActivity;

public class TryOnActivity extends AppCompatActivity
{
    private Button img_btn_camera;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet);

        img_btn_camera = (Button) findViewById(R.id.img_btn_camera);
        img_btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(TryOnActivity.this, ARDropObjectActivity.class);
                startActivity(i);
            }
        });
    }
}
