package com.mobile.info.ar_ecommerce_assignment2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<String> itemName;
    ArrayList<Integer> itemImages;
    Context context;

    public CustomAdapter(Context context, ArrayList<String> itemNames, ArrayList<Integer> itemImages) {
        this.context = context;
        this.itemName = itemNames;
        this.itemImages = itemImages;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_layout_item_list, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.name.setText(itemName.get(position));
        holder.image.setImageResource(itemImages.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context,ListOfItemDetails.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //add this line
                i.putExtra("item",holder.name.getText().toString());
                context.startActivity(i);
                //Toast.makeText(context, itemName.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return itemName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);


            name = (TextView) itemView.findViewById(R.id.txt_list_item_name);
            image = (ImageView) itemView.findViewById(R.id.image);

        }
    }
}
