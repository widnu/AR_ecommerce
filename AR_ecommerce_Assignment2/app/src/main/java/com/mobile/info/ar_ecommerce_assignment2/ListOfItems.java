package com.mobile.info.ar_ecommerce_assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NavUtils;

public class ListOfItems extends AppCompatActivity
{
    private CardView carditem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_item_detail);
        carditem = (CardView)findViewById(R.id.card_item1);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        carditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListOfItems.this,DetailDescriptionOfItem.class);
                startActivity(i);
            }
        });
    }

}
