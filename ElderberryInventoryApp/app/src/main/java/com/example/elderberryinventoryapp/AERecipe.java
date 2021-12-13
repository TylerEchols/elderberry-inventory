package com.example.elderberryinventoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
//    private ArrayList<ProductHelperClass> ingredientsList;
    private ArrayList<RecipeHelperClass> itemlist;
    private RecyclerView recyclerView;
//    recyclerAdapter adapter;
    recipeRecyclerAdapter recipeAdapter;
    String pid;
    DAORecipe dao;

//    private ArrayList<RecipeHelperClass> itemsList;



    // Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //Hide the action bar
        setContentView(R.layout.activity_aerecipe);

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
        loadData();

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
            String Amount ="1";
            String batchResult = "10";
            RecipeHelperClass helperClass = new RecipeHelperClass(pid ,name, Amount, batchResult);
//            referenceR.child(pid).setValue(helperClass);
            referenceR.push().setValue(helperClass); // Generate primary key randomly

//            finish();
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
            String p_id = pid;
            String Amount ="1";
            String batchResult = "10";
            RecipeHelperClass helperClass = new RecipeHelperClass( p_id, name, Amount, batchResult);
//            reference.child(id).setValue(helperClass);//Use id as the primary key
                referenceR.push().setValue(helperClass); // Generate primary key randomly


            Toast toast = Toast.makeText(getApplicationContext(), name + "Saved successfully", Toast.LENGTH_SHORT);
            toast.show();
            finish();

        });
    }


    private void loadData(){
        recyclerView = findViewById(R.id.in_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        itemlist = new ArrayList<>();
        recipeAdapter = new recipeRecyclerAdapter(this, itemlist);
        recyclerView.setAdapter(recipeAdapter);
        dao = new DAORecipe();

//        dao.getFilter(pid).addListenerForSingleValueEvent(new ValueEventListener() {
        referenceR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<RecipeHelperClass> recipes = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren())
                {
                    RecipeHelperClass re = data.getValue(RecipeHelperClass.class);
                    recipes.add(re);
//                    Toast toast = Toast.makeText(getApplicationContext(), re.getI_name()+ "Saved successfully", Toast.LENGTH_SHORT);
//                    toast.show();
                }
                recipeAdapter.setItems(recipes);
                recipeAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}