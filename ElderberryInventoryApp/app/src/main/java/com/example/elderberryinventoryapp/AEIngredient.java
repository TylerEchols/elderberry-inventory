package com.example.elderberryinventoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AEIngredient extends AppCompatActivity implements AdapterInterface{

    Button btnCancel, btnSaveRecipe;
    private ArrayList<ProductHelperClass> itemsList;
    private RecyclerView recyclerView;
    ingredientRecyclerAdapter adapter;
    DAOProduct dao;
    String key =null;
//    ArrayList<ProductHelperClass> inglist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //Hide the action bar
        setContentView(R.layout.activity_aeingredient);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        itemsList = new ArrayList<>();
        adapter= new ingredientRecyclerAdapter(this, itemsList, this);

        loadData2();

        initializedButtons();
    }
    private void initializedButtons(){
       btnCancel =findViewById(R.id.btnCancel);
       btnCancel.setOnClickListener(v -> { finish();});

//        btnSaveRecipe = findViewById(R.id.btnSaveRecipe);
//        btnSaveRecipe.setOnClickListener(v -> {
//            inglist = new ArrayList<>();
//            inglist = adapter.getIngList();
//            if (inglist.size()>0){
//                Toast.makeText(this.getApplicationContext(), inglist.size()+"" , Toast.LENGTH_SHORT).show();
//            }
//            Toast toast;
//            if (inglist == null){
//                toast = Toast.makeText(getApplicationContext(),  " nullllll", Toast.LENGTH_SHORT);
//
//            }else
//                toast = Toast.makeText(getApplicationContext(),  " not nul", Toast.LENGTH_SHORT);
//            toast.show();
//        });
    }


    private void loadDate(){
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        itemsList = new ArrayList<>();
        adapter= new ingredientRecyclerAdapter(this, itemsList, this);
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

        recyclerView.setAdapter(adapter);
        dao = new DAOProduct();
        dao.getFilter("Ingredients").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ProductHelperClass> pros = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren())
                {
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

    @Override
    public void getlist(ArrayList<ProductHelperClass> ingredient) {
        if (ingredient != null)
        {

        Toast toast = Toast.makeText(getApplicationContext(), ingredient.size() + " Item is selected", Toast.LENGTH_SHORT);
        toast.show();}    }
}