package com.mobile.info.ar_ecommerce_assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> itemName = new ArrayList<>(Arrays.asList("Watch", "Ring", "Bracelet","Neckless"));
    ArrayList<Integer> itemImages = new ArrayList<>(Arrays.asList(R.drawable.watch, R.drawable.ring, R.drawable.braclet,R.drawable.neckless));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, itemName, itemImages);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

    }
}
