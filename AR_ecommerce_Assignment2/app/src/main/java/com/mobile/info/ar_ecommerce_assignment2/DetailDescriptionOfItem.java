package com.mobile.info.ar_ecommerce_assignment2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;


public class DetailDescriptionOfItem extends AppCompatActivity {
    private Button try_on;
    final Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail_desc);

        try_on = (Button) findViewById(R.id.btn_try_on);
       // getActionBar().setDisplayHomeAsUpEnabled(true);


        try_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(DetailDescriptionOfItem.this, TryOnActivity.class);
                startActivity(i);

            }
        });
    }
   }

