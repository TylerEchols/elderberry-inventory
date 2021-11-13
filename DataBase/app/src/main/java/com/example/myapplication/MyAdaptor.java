package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.MyViewHolder> {

    ArrayList <ProductHelperClass> mList;
    Context context;

    public MyAdaptor(Context context, ArrayList<ProductHelperClass> mList){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent , false);
//        return null;
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProductHelperClass model = mList.get(position);
        holder.name.setText(model.getName());
        holder.id.setText(model.getId());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, id;

        public MyViewHolder(@NonNull View ItemView){
            super(ItemView);
            name = ItemView.findViewById(R.id.txtname);
            id = ItemView.findViewById(R.id.txtid);
        }


    }
}
