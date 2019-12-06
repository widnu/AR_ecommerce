package com.mobile.info.ar_ecommerce_assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class ListOfItemDetails extends AppCompatActivity {
    ArrayList<String> itemName = new ArrayList<>(Arrays.asList("MICHAEL KORS", "MICHAEL KORS", "MICHAEL KORS", "MICHAEL KORS", "BOSS"));
    ArrayList<String> itemPrice = new ArrayList<>(Arrays.asList("NZ $399.00", "NZ $439.00", "NZ $439.00", "NZ $399.00", "NZ $178.25"));
    ArrayList<String> itemdesc = new ArrayList<>(Arrays.asList(Constants.WATCH_DETAIL_0, Constants.WATCH_DETAIL_1, Constants.WATCH_DETAIL_2, Constants.WATCH_DETAIL_3, Constants.WATCH_DETAIL_4));
    ArrayList<Integer> itemImages = new ArrayList<>(Arrays.asList(R.drawable.w1, R.drawable.w2, R.drawable.w3, R.drawable.w4, R.drawable.w5));

    /*Ring deatils*/
    ArrayList<String> RingitemName = new ArrayList<>(Arrays.asList("Delicacy In White Gold (18k)", "Kew In White Gold (18k)", "Diamond Cluster Ring", "Classic Round Cut Sterling Silver Ring", "Classic Round Cut Sterling Silver Ring Set"));
    ArrayList<String> RingitemPrice = new ArrayList<>(Arrays.asList("NZ $644.53", "NZ$ 1,844.18", "NZ$9,460.00", "NZ$148.50", "NZ$187.50"));
    ArrayList<String> Ringitemdesc = new ArrayList<>(Arrays.asList("Round Cut diamonds", "Round Cut diamonds", "HALO AND CLUSTER RINGS", "Diamond White(Jeulia® Stone)", "Diamond White(Jeulia® Stone)"));
    ArrayList<Integer> RingitemImages = new ArrayList<>(Arrays.asList(R.drawable.ring1, R.drawable.ring2, R.drawable.ring3, R.drawable.ring4, R.drawable.ring5));

    String item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        item = i.getStringExtra("item");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        if (item.equalsIgnoreCase("Watch")) {
            CustomListItemAdapter customAdapter = new CustomListItemAdapter(ListOfItemDetails.this, itemName, itemdesc, itemPrice, itemImages);
            recyclerView.setAdapter(customAdapter);
            // set the Adapter to RecyclerView
        } else if (item.equalsIgnoreCase("Ring")) {
            CustomListItemAdapter customAdapter = new CustomListItemAdapter(ListOfItemDetails.this,
                    RingitemName, Ringitemdesc, RingitemPrice, RingitemImages);
            recyclerView.setAdapter(customAdapter);

        } else {
            Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
            finish();
            startActivity(i1);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
