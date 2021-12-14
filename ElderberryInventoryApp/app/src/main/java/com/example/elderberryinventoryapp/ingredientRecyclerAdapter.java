package com.example.elderberryinventoryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ingredientRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<ProductHelperClass> itemsList;
    private AdapterInterface adapterInterface;
//    private ArrayList<ProductHelperClass> ingList;
    Context context;

    public ingredientRecyclerAdapter( Context context ,ArrayList<ProductHelperClass> itemsList, AdapterInterface adapterInterface) {
        this.itemsList = itemsList;
        this.context = context;
        this.adapterInterface = adapterInterface;
    }

    public void setItems(ArrayList<ProductHelperClass> pro)
    {
        itemsList.addAll(pro);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;

        public MyViewHolder(final View view){
            super(view);
            checkBox = view.findViewById(R.id.checkbox);
//            textMinNumber = view.findViewById(R.id.txtMinNum);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.ingredient_checkbox, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductHelperClass model = itemsList.get(position);
        MyViewHolder vh = (MyViewHolder) holder;

//        ingList = new ArrayList<>();
        vh.checkBox.setText(model.getName());
        vh.checkBox.setOnClickListener(v -> {
            adapterInterface.getlist(model, vh.checkBox.isChecked());

        });

    }


    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
