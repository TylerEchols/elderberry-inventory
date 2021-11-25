package com.example.elderberryinventoryapp;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    // Member variables
    Spinner categorySpinner; // Spinner is the term for dropdown menu
    Button btnProduct;
    private ArrayList<InventoryItem> itemsList;
    private RecyclerView recyclerView;


    // Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.items_recycler_view);
        itemsList = new ArrayList<>();

        // TESTING
        setTestData();

        setSpinnerAdapter();
        setRecyclerAdapter();
        initializeNewItemButton();

    }


    // Initialize Spinner for item category drop-down
    private void setSpinnerAdapter(){
        // Spinner code borrows heavily from https://code.tutsplus.com/tutorials/how-to-add-a-dropdown-menu-in-android-studio--cms-37860
        categorySpinner = findViewById(R.id.category_spinner); // Dropdown menu for choosing item category
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.item_categories, android.R.layout.simple_spinner_item);
        // ArrayAdapter responsible for rendering items in item_categories
        // to screen when dropdown menu is pressed
        categorySpinner.setAdapter(adapter); // Bind ArrayAdapter to Spinner
    }


    // Initialize RecyclerView's adapter for list of items
    private void setRecyclerAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(itemsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


    // Initialize button for added new item
    private void initializeNewItemButton(){
        // I did not want change the layout so I used go-get-nave-button to add new Item to Product table
        // Tyler rebound from go_get_nav_button to addItemButton Nov/24/2021
        btnProduct = (Button) findViewById(R.id.addItemButton);
        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the Intent object of this class Context() to Second_activity class
                Intent intent = new Intent(getApplicationContext(), AEProducts.class);

                // start the Intent
                startActivity(intent);
            }
        });
    }

    // TESTING
    // Initialize hard-coded data to use for testing purposes
    // This code should be removed or disabled for final product
    private void setTestData(){
        // Items for RecyclerView to display
        itemsList.add(new InventoryItem("Juice Powder", "5", "10"));
        itemsList.add(new InventoryItem("Dried Berries", "8", "10"));
        itemsList.add(new InventoryItem("Syrup Cases", "2", "1"));
    }
}