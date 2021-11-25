package com.example.elderberryinventoryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<ProductHelperClass> itemsList;
    Context context;

    // Constructor
    public recyclerAdapter(Context context , ArrayList<ProductHelperClass> itemsList){
        this.itemsList = itemsList;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // Member Variables for each widget in inventory_item_card.xml
        private EditText textMinNumber;
//        private EditText textMinLabel;
//        private ImageView imageView;
        private EditText textItemName;
        private EditText textHaveNumber;
//        private EditText textHaveLabel;

        
        public MyViewHolder(final View view){
            super(view);
            //nameTxt = view.findViewById(R.id.textView2);
            textMinNumber = view.findViewById(R.id.etMinNumber);
//            textMinLabel = view.findViewById(R.id.etMinLabel);
//            imageView = view.findViewById(R.id.imageView);
            textItemName = view.findViewById(R.id.etItemName);
            textHaveNumber = view.findViewById(R.id.etHaveNumber);
//            textHaveLabel = view.findViewById(R.id.etHaveLabel);
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_item_card, parent, false);
        return new MyViewHolder(itemView);
    }

    // Populate "cards" to display using data from itemsList
    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        ProductHelperClass model = itemsList.get(position);
        holder.textMinNumber.setText(model.getMinNumber());
        // Placeholder line for if we decide to include thumbnails
        // holder.imageView.setImageResource(itemsList.get(position).getImageView());
        holder.textItemName.setText(itemsList.get(position).getName());
        holder.textHaveNumber.setText(itemsList.get(position).getNumberOfHave());

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
