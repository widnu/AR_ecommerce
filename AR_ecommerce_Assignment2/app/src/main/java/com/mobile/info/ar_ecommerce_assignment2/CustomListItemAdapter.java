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


public class CustomListItemAdapter extends RecyclerView.Adapter<CustomListItemAdapter.MyViewHolder> {

    ArrayList<String> itemName;
    ArrayList<String> itemDesc;
    ArrayList<String> itemPrice;
    ArrayList<Integer> itemImages;
    Context context;

    public CustomListItemAdapter(Context context, ArrayList<String> itemNames, ArrayList<String> itemDesc, ArrayList<String> itemPrice, ArrayList<Integer> itemImages) {
        this.context = context;
        this.itemName = itemNames;
        this.itemDesc = itemDesc;
        this.itemPrice = itemPrice;
        this.itemImages = itemImages;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_layout_item_detail, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.name.setText(itemName.get(position));
        holder.price.setText(itemPrice.get(position));
        holder.desc.setText(itemDesc.get(position));
        holder.image.setImageResource(itemImages.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, DetailDescriptionOfItem.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //add this line
                i.putExtra("title", holder.name.getText().toString());
                i.putExtra("desc", holder.desc.getText().toString());
                i.putExtra("price", holder.price.getText().toString());
                i.putExtra("image", itemImages.get(position));
                context.startActivity(i);
                //Toast.makeText(context, holder.name.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return itemName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name, price, desc;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);


            name = (TextView) itemView.findViewById(R.id.txt_title);
            price = (TextView) itemView.findViewById(R.id.txt_price);
            desc = (TextView) itemView.findViewById(R.id.txt_desc);
            image = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }
}
