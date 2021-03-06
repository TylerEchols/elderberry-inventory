package com.example.elderberryinventoryapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ProductHelperClass> itemsList;
    Context context;


    // Constructor
    public recyclerAdapter(Context context , ArrayList<ProductHelperClass> itemsList){
        this.itemsList = itemsList;
//        this.itemsList.addAll(itemsList);

        this.context = context;
    }

    public void setItems(ArrayList<ProductHelperClass> pro)
    {
        itemsList.addAll(pro);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // Member Variables for each widget in inventory_item_card.xml
        private TextView textMinNumber;
        private TextView textItemName;
        private TextView textHaveNumber;
        private TextView textOption;
        private Spinner categorySpinner;


        public MyViewHolder(final View view){
            super(view);
            textMinNumber = view.findViewById(R.id.etMinNumber);
            textItemName = view.findViewById(R.id.etItemName);
            textHaveNumber = view.findViewById(R.id.etHaveNumber);
            textOption = view.findViewById(R.id.txt_option);
            categorySpinner = view.findViewById(R.id.category_select_spinner);
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.inventory_item_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductHelperClass p = null;
        this.onBindViewHolder(holder,position,p);
    }

    // Populate "cards" to display using data from itemsList
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, ProductHelperClass p) {
        MyViewHolder vh = (MyViewHolder) holder;

        ProductHelperClass pro = p==null? itemsList.get(position):p;
        vh.textMinNumber.setText(pro.getMinNumber());

        // Placeholder line for if we decide to include thumbnails
        vh.textItemName.setText(pro.getName());
        vh.textHaveNumber.setText(pro.getNumberOfHave());


        vh.textOption.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, vh.textOption);
            popupMenu.inflate(R.menu.options_menu);

            popupMenu.setOnMenuItemClickListener(item->
            {
                switch (item.getItemId())
                {
                      case R.id.menu_edit:
                        Intent intent=new Intent(context,AEProducts.class);
                        intent.putExtra("EDIT",pro);
                        context.startActivity(intent);
                        break;
                    case R.id.menu_remove:
                        DAOProduct dao=new DAOProduct();
                        dao.remove(pro.getId()).addOnSuccessListener(suc->
                        {
                            Toast.makeText(context, "Record is removed", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                            itemsList.remove(pro);
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
