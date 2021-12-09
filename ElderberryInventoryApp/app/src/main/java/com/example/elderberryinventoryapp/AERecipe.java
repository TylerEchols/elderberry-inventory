package com.example.elderberryinventoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elderberryinventoryapp.ui.main.RecipeHelperClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AERecipe extends AppCompatActivity {

    // Member variables
    // Sara, please convert the Database statements here to import Recipe instead of Product
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference referenceR = db.getReference().child("recipe");
//    private DatabaseReference root = db.getReference().child("Product");

    Spinner categorySpinner; // Spinner is the term for dropdown menu
    Button btnAddIngredient , btnClose , btnSaveRecipe;
    TextView textProductName;
    private ArrayList<ProductHelperClass> ingredientsList;
    private RecyclerView recyclerView;
    recyclerAdapter adapter;
    String pid;


    // Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //Hide the action bar
        setContentView(R.layout.activity_aerecipe);
        recyclerView = findViewById(R.id.items_recycler_view);

//        setSpinnerAdapter();
        initializeNewIngredientButton();
        fillUpItems();

//        initializeSaveRecipeButton();
    }

    // Called when activity is resumed, not just started
    @Override
    protected void onResume() {
        super.onResume();


        // Refresh RecyclerView
//        setRecyclerAdapter();
    }


    // Initialize Spinner for item category drop-down
    private void setSpinnerAdapter(){
        // Spinner code borrows heavily from
        // https://code.tutsplus.com/tutorials/how-to-add-a-dropdown-menu-in-android-studio--cms-37860
        categorySpinner = findViewById(R.id.category_filter_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.item_categories, android.R.layout.simple_spinner_item);
        categorySpinner.setAdapter(adapter);
    }


    // Initialize RecyclerView's adapter for list of items
    // Sara, please adjust this method to work with Recipes instead of Products
    private void setRecyclerAdapter() {
        //Database
        ingredientsList = new ArrayList<>();
        adapter = new recyclerAdapter(this,ingredientsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

//        root.addValueEventListener(new ValueEventListener() {
        referenceR.addValueEventListener(new ValueEventListener() {
        @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ProductHelperClass model = dataSnapshot.getValue(ProductHelperClass.class);
                    ingredientsList.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }


    // Set OnClickListener for button to add Ingredient
    private void initializeNewIngredientButton(){

        btnAddIngredient = findViewById(R.id.addIngredientButton);
        btnAddIngredient.setOnClickListener(v -> {
            // Create the Intent object of this class Context() to Second_activity class
            Intent intent = new Intent(getApplicationContext(), AEIngredient.class);

            // start the Intent
            startActivity(intent);
        });
        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> {finish();});
        btnSaveRecipe = findViewById(R.id.btnSaveRecipe);
        btnSaveRecipe.setOnClickListener(v -> {
            String name = textProductName.getText().toString();
            String id = pid;
            String Amount ="1";
            String batchResult = "10";
            RecipeHelperClass helperClass = new RecipeHelperClass(id ,name, Amount, batchResult);
            referenceR.child(id).setValue(helperClass);
//            referenceR.push().setValue(helperClass); // Generate primary key randomly

            finish();
        });



    }


    // Set OnClickListener for button to save Recipe
    // Sara, please set this button to save the Recipe to whatever
        // Product the user selected when we opened this Activity
    private void initializeSaveRecipeButton(){

    }
    private void fillUpItems(){
        textProductName = findViewById(R.id.textProductName);
        Bundle bundle = getIntent().getExtras();
        //TODO here get the string stored in the string variable and do
        // setText() on userName
        String pName = bundle.getString("pname");
        pid = bundle.getString("pid");
        if(pName != null) {
            textProductName.setText(pName);
        }

    }

    private void setListeners(){
        //Save Data into the firebase database
        btnSaveRecipe = findViewById(R.id.btnAddRecipe);
        btnSaveRecipe.setOnClickListener(v -> {

            String name = textProductName.getText().toString();
            String id = pid;
            String Amount ="1";
            String batchResult = "10";
            RecipeHelperClass helperClass = new RecipeHelperClass(name, id, Amount, batchResult);
//            reference.child(id).setValue(helperClass);//Use id as the primary key
                referenceR.push().setValue(helperClass); // Generate primary key randomly


            Toast toast = Toast.makeText(getApplicationContext(), name + "Saved successfully", Toast.LENGTH_SHORT);
            toast.show();
            finish();

        });


    }

}