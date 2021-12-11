package com.example.elderberryinventoryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class goGetRecylcerAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private ArrayList<ProductHelperClass> itemsList;
    Context context;

    public void setItems(ArrayList<ProductHelperClass> pro)
    {
        itemsList.addAll(pro);
    }
    public goGetRecylcerAdapter( Context context, ArrayList<ProductHelperClass> itemsList) {
        this.itemsList = itemsList;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // Member Variables for each widget in inventory_item_card.xml
        private TextView textItemName;
        private TextView textMinNumber;

        public MyViewHolder(final View view){
            super(view);
            textItemName = view.findViewById(R.id.txtIngName);
            textMinNumber = view.findViewById(R.id.txtMinNum);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_gogetlist, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductHelperClass model = itemsList.get(position);
        MyViewHolder vh = (MyViewHolder) holder;
        long need = Long.parseLong(model.getMinNumber())- Long.parseLong(model.getNumberOfHave()) ;

        vh.textItemName.setText(model.getName());
        vh.textMinNumber.setText( Long.toString(need));
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
