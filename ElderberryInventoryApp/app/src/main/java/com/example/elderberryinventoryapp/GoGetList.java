package com.example.elderberryinventoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GoGetList extends AppCompatActivity {

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("products");

    // Member variables
//    Button btnProduct ;
    private ArrayList<ProductHelperClass> itemsList;
    private RecyclerView recyclerView;
    goGetRecylcerAdapter adapter;
    DAOProduct dao;



    // Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_get_list);
        getSupportActionBar().hide(); //Hide the action bar
        recyclerView = findViewById(R.id.go_get_recycler_view);
    }

    // Called when activity is resumed, not just started
    @Override
    protected void onResume() {
        super.onResume();

        // Refresh RecyclerView
        setRecyclerAdapter();
    }


    // Initialize RecyclerView's adapter for list of items

    private void setRecyclerAdapter() {
        //Database
        itemsList = new ArrayList<>();
        adapter = new goGetRecylcerAdapter(this,itemsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        dao = new DAOProduct();

//        root.addValueEventListener(new ValueEventListener() {
        dao.getFilter("Ingredients").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ProductHelperClass> pros = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren())
                {
                    ProductHelperClass pro = data.getValue(ProductHelperClass.class);
                    long need = Long.parseLong(pro.getNumberOfHave())- Long.parseLong(pro.getMinNumber()) ;
//                    Toast toast = Toast.makeText(getApplicationContext(), Long.toString(need)+ "Saved successfully", Toast.LENGTH_SHORT);
//                    toast.show();
                    if (need <= 0 )
                        pros.add(pro);
                }
                adapter.setItems(pros);
                adapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void navigateInventory(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}