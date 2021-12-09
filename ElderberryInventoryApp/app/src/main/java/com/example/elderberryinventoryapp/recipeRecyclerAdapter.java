package com.example.elderberryinventoryapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elderberryinventoryapp.ProductHelperClass;

import java.util.ArrayList;

public class recipeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // Member Variables
    private ArrayList<ProductHelperClass> itemsList;
    Context context;

    public void setItems(ArrayList<ProductHelperClass> pro)
    {
        itemsList.addAll(pro);
    }

    // Constructor
    public recipeRecyclerAdapter(Context context_in, ArrayList<ProductHelperClass> itemsList_in){
        this.itemsList = itemsList_in;
        this.context = context_in;
    }


    public static class RecipeViewHolder extends RecyclerView.ViewHolder{
        // Member Variables based on ingredient_card.xml
        private TextView itemNameText;
        private TextView itemQuantityText;
        private TextView editOption;

        // RecipeViewHolder Constructor
        // Sara, I've set this up the same way as recyclerAdapter.java populates
            // its fields for textMinNumber, textItemName, etc.
            // but I realize this RecyclerView may not work quite the same.
            // When you load the Recipe for whatever Product the user chose, please
            // populate these fields with the data from that Product.
        public RecipeViewHolder(final View view){
            super(view);
            itemNameText = view.findViewById(R.id.textName);
            itemQuantityText = view.findViewById(R.id.textQuantity);
            editOption = view.findViewById(R.id.txt_option);
        }
    }


    @NonNull
    @Override
    public recipeRecyclerAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.ingredient_card, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductHelperClass p = null;
        this.onBindViewHolder(holder,position,p);
    }

    // Populate "cards" to display using data from itemsList
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, ProductHelperClass p){
        // Sara, please bind this RecyclerView to show all of the Ingredients in
        // the current Recipe. The argument ProductHelperClass may not be the right
        // one, so change this method as you see fit

        // Please also set OnClickListener for 3-dot edit button
            // Should be almost the same as the one for Inventory Items, but
            // changing Ingredients in the Recipe instead of Items in the Inventory
        RecipeViewHolder vh = (RecipeViewHolder) holder;

        ProductHelperClass pro = p==null? itemsList.get(position):p;
        vh.itemQuantityText.setText(pro.getMinNumber());
        vh.itemNameText.setText(pro.getName());

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
