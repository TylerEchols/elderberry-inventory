package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private MyAdaptor adaptor;
    private RecyclerView recyclerView;
    private ArrayList<ProductHelperClass> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        recyclerView = findViewById(R.id.rView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference().child("products");

        list = new ArrayList<>();
        adaptor = new MyAdaptor(this,list);

        recyclerView.setAdapter(adaptor);

    }
}