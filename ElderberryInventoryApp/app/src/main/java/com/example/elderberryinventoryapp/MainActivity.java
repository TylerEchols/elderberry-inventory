package com.example.elderberryinventoryapp;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    // Member variables
    Spinner categorySpinner; // Spinner is the term for dropdown menu
    Button btnProduct;

    // Constructor

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Spinner code borrows heavily from https://code.tutsplus.com/tutorials/how-to-add-a-dropdown-menu-in-android-studio--cms-37860
        categorySpinner = findViewById(R.id.category_spinner); // Dropdown menu for choosing item category
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.item_categories, android.R.layout.simple_spinner_item);
                // ArrayAdapter responsible for rendering items in item_categories
                // to screen when dropdown menu is pressed
        categorySpinner.setAdapter(adapter); // Bind ArrayAdapter to Spinner


        // I did not want change the layout so I used go-get-nave-button to add new Item to Product table
        btnProduct = (Button) findViewById(R.id.go_get_nav_button);
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

}