package com.example.elderberryinventoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Member variables
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    public DatabaseReference root = db.getReference().child("products");

    Spinner categorySpinner; // Spinner is the term for dropdown menu
    Button btnAddProduct;
    private ArrayList<ProductHelperClass> itemsList;
    private RecyclerView recyclerView;
    recyclerAdapter adapter;
    DAOProduct dao;
    boolean tt = true;



    // Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //Hide the action bar

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.items_recycler_view);

        setSpinnerAdapter();
        initializeNewItemButton();
//        spinnerChange();

    }

    // Called when activity is resumed, not just started
    @Override
    protected void onResume() {
        super.onResume();

        // Refresh RecyclerView
        if (tt == true) {
           tt = false;
        }
        else
            loadData2(categorySpinner.getSelectedItem().toString());
        spinnerChange();

     }


    // Initialize Spinner for item category drop-down
    private void setSpinnerAdapter(){
        // Spinner code borrows heavily from
        // https://code.tutsplus.com/tutorials/how-to-add-a-dropdown-menu-in-android-studio--cms-37860
        categorySpinner = findViewById(R.id.category_filter_spinner); // Dropdown menu for choosing item category
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.item_categories, android.R.layout.simple_spinner_item);
        // ArrayAdapter responsible for rendering items in item_categories
        // to screen when dropdown menu is pressed
        categorySpinner.setAdapter(adapter); // Bind ArrayAdapter to Spinner
    }


    // Initialize RecyclerView's adapter for list of items
    private void setRecyclerAdapter(String cat) {
        //Database
        itemsList = new ArrayList<>();
        adapter = new recyclerAdapter(this,itemsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        root = FirebaseDatabase.getInstance().getReference();
        Query query = root.child("products").orderByChild("category").equalTo(cat);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do with your result
                        ProductHelperClass model = issue.getValue(ProductHelperClass.class);
                        itemsList.add(model);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void loadData2(String cat){
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        itemsList = new ArrayList<>();
        adapter= new recyclerAdapter(this, itemsList);
        recyclerView.setAdapter(adapter);
        dao = new DAOProduct();

        dao.getFilter(cat).addListenerForSingleValueEvent(new ValueEventListener() {

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

    // Initialize button for added new item
    private void initializeNewItemButton(){
        btnAddProduct = findViewById(R.id.addItemButton);
        btnAddProduct.setOnClickListener(v -> {
            // Create the Intent object of this class Context() to Second_activity class
            Intent intent = new Intent(getApplicationContext(), AEProducts.class);

            // start the Intent
            startActivity(intent);
            spinnerChange();
        });
    }

    // Navigate to GoGet Activity
    public void navigateGoGet(View view) {
        Intent intent = new Intent(getApplicationContext(), GoGetList.class);
        startActivity(intent);
    }
    // Navigate to Main Activity
    public void navigateInventory(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void navigateCalculator(View view){
        Intent intent = new Intent(getApplicationContext(), test.class);
        startActivity(intent);
    }

    public void spinnerChange(){
//        int iCurrentSelection = categorySpinner.getSelectedItemPosition();
//        setRecyclerAdapter(categorySpinner.getSelectedItem().toString());
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cat = categorySpinner.getSelectedItem().toString();
                loadData2(cat);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }



}