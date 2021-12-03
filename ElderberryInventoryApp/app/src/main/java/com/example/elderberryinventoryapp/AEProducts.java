package com.example.elderberryinventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AEProducts extends AppCompatActivity {

    EditText pName, pid, pNumberOfHave, pMinNumber;
    Button btnAdd, btnClose;
    Spinner categorySpinner; // Spinner is the term for dropdown menu

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aeproducts);

        bindButtons();
        setListeners();
        setSpinnerAdapter();
    }


    private void bindButtons(){
        pName = (EditText) findViewById(R.id.txtname);
        pid = (EditText) findViewById(R.id.txtid);
        pNumberOfHave = (EditText) findViewById(R.id.txtNhave);
        pMinNumber = (EditText) findViewById(R.id.txtNmin);
//        pbatchResult = (EditText) findViewById(R.id.txtbatchResult);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnClose = (Button) findViewById(R.id.btnClose);

        DAOProduct dao =new DAOProduct();
        ProductHelperClass emp_edit = (ProductHelperClass) getIntent().getSerializableExtra("EDIT");
        if(emp_edit !=null)
        {
            btnAdd.setText("UPDATE");
            pName.setText(emp_edit.getName());
            pid.setText(emp_edit.getId());
            pNumberOfHave.setText(emp_edit.getNumberOfHave());
            pMinNumber.setText(emp_edit.getMinNumber());
        }
        else
        {
            btnAdd.setText("Add");
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
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("products");

                String name = pName.getText().toString();
                String id = pid.getText().toString();
                String numberOfHave = pNumberOfHave.getText().toString();
                String minNumber = pMinNumber.getText().toString();
//                String batchResult = pbatchResult.getText().toString();
                ProductHelperClass helperClass = new ProductHelperClass(name, id, numberOfHave, minNumber);
                reference.child(id).setValue(helperClass);//Use id as the primary key
//                reference.push().setValue(helperClass); // Generate primary key randomly


                Toast toast = Toast.makeText(getApplicationContext(), name + "Saved successfully", Toast.LENGTH_SHORT);
                toast.show();
                finish();
//                clearTexts();

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

    public void clearTexts() {
        pName.setText("");
        pid.setText("");
        pNumberOfHave.setText("");
        pMinNumber.setText("");
//        pbatchResult.setText("");
    }


    public void edit(){


    }
}