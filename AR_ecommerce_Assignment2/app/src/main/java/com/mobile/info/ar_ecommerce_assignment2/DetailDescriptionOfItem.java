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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class DetailDescriptionOfItem extends AppCompatActivity {
    private Button try_on;
    final Context context = this;
    TextView txt_title, txt_desc, txt_price;
    ImageView image_detail;

    String title, desc, price;
    int image_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail_desc);
        txt_title = (TextView) findViewById(R.id.txt_item_name);
        txt_desc = (TextView) findViewById(R.id.text_desc1);
        txt_price = (TextView) findViewById(R.id.text_desc2);

        image_detail = (ImageView)findViewById(R.id.imagedetail);
        try_on = (Button) findViewById(R.id.btn_try_on);

        Intent i = getIntent();
        title = i.getStringExtra("title");
        desc = i.getStringExtra("desc");
        price = i.getStringExtra("price");
//        imageId = i.getIntExtra("image", 0);


        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        image_id = extras.getInt("image");
        txt_title.setText(title);
        txt_desc.setText(desc);
        txt_price.setText(price);
        image_detail.setImageResource(image_id);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        try_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(DetailDescriptionOfItem.this, TryOnActivity.class);
                i.putExtra("title", title);
                i.putExtra("desc", desc);
                i.putExtra("image", image_id);
                startActivity(i);

            }
        });
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.navigation_home) {
                    Intent i = new Intent(DetailDescriptionOfItem.this, MainActivity.class);
                    startActivity(i);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

