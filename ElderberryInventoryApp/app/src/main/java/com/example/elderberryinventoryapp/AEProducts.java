package com.example.elderberryinventoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AEProducts extends AppCompatActivity {

    EditText pName, pid, pNumberOfHave, pMinNumber;
    Button btnAdd, btnClose, btnAddRecipt;
    Spinner categorySpinner; // Spinner is the term for dropdown menu

//    FirebaseDatabase rootNode;
//    DatabaseReference reference;
    private long maxid = 0;
    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference = rootNode.getReference("products");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aeproducts);

        setSpinnerAdapter();

        bindButtons();
        setListeners();
        spinnerChange();

    }

    private void bindButtons(){
        pName = findViewById(R.id.txtname);
        pid = findViewById(R.id.txtid);
        pNumberOfHave = findViewById(R.id.txtNhave);
        pMinNumber = findViewById(R.id.txtNmin);
        btnAdd = findViewById(R.id.btnAdd);
        btnClose = findViewById(R.id.btnClose);
        btnAddRecipt = findViewById(R.id.btnAddRecipe);

        categorySpinner = findViewById(R.id.category_select_spinner);

        DAOProduct dao =new DAOProduct();
        ProductHelperClass emp_edit = (ProductHelperClass) getIntent().getSerializableExtra("EDIT");
        if(emp_edit !=null)
        {
            btnAdd.setText("UPDATE");
            pName.setText(emp_edit.getName());
            pid.setText(emp_edit.getId());
            pNumberOfHave.setText(emp_edit.getNumberOfHave());
            pMinNumber.setText(emp_edit.getMinNumber());
            for (int i = 0; i < categorySpinner.getCount(); i++) {
                if (categorySpinner.getItemAtPosition(i).equals(emp_edit.getCategory())) {
                    categorySpinner.setSelection(i);
                    break;
                }
              }
            categorySpinner.setEnabled(false);
            }
        else
        {
            // Generate the ID (Auto increment)
            reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            maxid = snapshot.getChildrenCount() + 1;
                            pid.setText(Long.toString(maxid));
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            btnAdd.setText("Add");
            categorySpinner.setEnabled(true);

        }
    }

    private void setListeners(){
        //Save Data into the firebase database
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                First we should create the database using this link:
                https://console.firebase.google.com/u/0/
                and then in Android studio from Tools>firebase and click on realtime database
                we should connect the App to the database which we created on firebase.
                 */

                String name = pName.getText().toString();
                String id = pid.getText().toString();
                String numberOfHave = pNumberOfHave.getText().toString();
                String minNumber = pMinNumber.getText().toString();
                String category = categorySpinner.getSelectedItem().toString();
                ProductHelperClass helperClass = new ProductHelperClass(name, id, numberOfHave, minNumber, category);
                reference.child(id).setValue(helperClass);//Use id as the primary key
//                reference.push().setValue(helperClass); // Generate primary key randomly


                Toast toast = Toast.makeText(getApplicationContext(), name + "Saved successfully", Toast.LENGTH_SHORT);
                toast.show();
                finish();

            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Initialize Spinner for item category drop-down
    private void setSpinnerAdapter(){
        // Spinner code borrows heavily from https://code.tutsplus.com/tutorials/how-to-add-a-dropdown-menu-in-android-studio--cms-37860
        categorySpinner = findViewById(R.id.category_select_spinner); // Dropdown menu for choosing item category
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.item_categories, android.R.layout.simple_spinner_item);
        // ArrayAdapter responsible for rendering items in item_categories
        // to screen when dropdown menu is pressed
        categorySpinner.setAdapter(adapter); // Bind ArrayAdapter to Spinner
    }


    // only for ingredients we can see the Add Recipe Button
    public void spinnerChange(){
        int iCurrentSelection = categorySpinner.getSelectedItemPosition();
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 4 )
                    btnAddRecipt.setVisibility(View.VISIBLE);
                else
                    btnAddRecipt.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}