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

import java.util.ArrayList;

public class recipeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<RecipeHelperClass> itemsList;
    Context context;

    public void setItems(ArrayList<RecipeHelperClass> pro)
    {
        itemsList.addAll(pro);
    }

    public recipeRecyclerAdapter(Context context , ArrayList<RecipeHelperClass> itemsList) {
        this.itemsList = itemsList;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // Member Variables for each widget in inventory_item_card.xml
        private TextView textItemName;
        private TextView quantity;
        private TextView textOption;


        public MyViewHolder(final View view){
            super(view);
            textItemName = view.findViewById(R.id.textName);
            quantity = view.findViewById(R.id.textQuantity);
            textOption = view.findViewById(R.id.txt_option);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.ingredient_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecipeHelperClass model = itemsList.get(position);
        MyViewHolder vh = (MyViewHolder) holder;
        vh.textItemName.setText(model.getName());
        vh.quantity.setText(model.getAmount());

        vh.textOption.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, vh.textOption);
            popupMenu.inflate(R.menu.options_menu);


            popupMenu.setOnMenuItemClickListener(item->
            {
                switch (item.getItemId())
                {
                    case R.id.menu_edit:
//                        Intent intent=new Intent(context,AEProducts.class);
//                        intent.putExtra("EDIT",pro);
//                        context.startActivity(intent);
                        break;
                    case R.id.menu_remove:
                        DAORecipe dao=new DAORecipe();
                        dao.remove(model.getKey()).addOnSuccessListener(suc->
                        {
                            Toast.makeText(context, "Record is removed", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                            itemsList.remove(model);
                        }).addOnFailureListener(er->
                        {
                            Toast.makeText(context, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                        });

                        break;
                }
                return false;
            });
            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();

    }
}
