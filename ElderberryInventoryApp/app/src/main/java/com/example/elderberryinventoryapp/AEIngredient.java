package com.example.elderberryinventoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AEIngredient extends AppCompatActivity {

    Button btnCancel;
    private ArrayList<ProductHelperClass> itemsList;
    private RecyclerView recyclerView;
    recyclerAdapter adapter;
    DAOProduct dao;
    String key =null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //Hide the action bar
        setContentView(R.layout.activity_aeingredient);

        loadData2();
        initializedButtons();
    }
    private void initializedButtons(){
       btnCancel =findViewById(R.id.btnCancel);
       btnCancel.setOnClickListener(v -> { finish();});
    }


    private void loadDate(){
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        itemsList = new ArrayList<>();
        adapter= new recyclerAdapter(this, itemsList);
        recyclerView.setAdapter(adapter);
        dao = new DAOProduct();
        dao.get(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ProductHelperClass> pros = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren())
                {
                    ProductHelperClass pro = data.getValue(ProductHelperClass.class);
                    pro.setId(data.getKey());
                    pros.add(pro);
                    key = data.getKey();
                }
                adapter.setItems(pros);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadData2(){
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        itemsList = new ArrayList<>();
        adapter= new recyclerAdapter(this, itemsList);
        recyclerView.setAdapter(adapter);
        dao = new DAOProduct();


//        dao.get(key).addListenerForSingleValueEvent(new ValueEventListener() {
        dao.getFilter("Ingredients").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ProductHelperClass> pros = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren())
                {
//                    ProductHelperClass pro = data.getValue(ProductHelperClass.class);
//                    pro.setId(data.getKey());
//                    pros.add(pro);
//                    key = data.getKey();
                    ProductHelperClass pro = data.getValue(ProductHelperClass.class);
                    pros.add(pro);
                }
                adapter.setItems(pros);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}