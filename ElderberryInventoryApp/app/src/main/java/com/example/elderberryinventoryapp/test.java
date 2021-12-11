package com.example.elderberryinventoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class test extends AppCompatActivity {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        loadData();
    }

    private void loadData(){
        recyclerView = findViewById(R.id.rv_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        itemlist = new ArrayList<>();
        recipeAdapter = new recipeRecyclerAdapter(this, itemlist);
        recyclerView.setAdapter(recipeAdapter);
//        dao = new DAORecipe();

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