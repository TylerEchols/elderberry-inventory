package com.example.elderberryinventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AEProducts extends AppCompatActivity {

    EditText pName, pid, pNumberOfHave, pMinNumber;
    Button btnAdd, btnClose;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aeproducts);

        pName = (EditText) findViewById(R.id.txtname);
        pid = (EditText) findViewById(R.id.txtid);
        pNumberOfHave = (EditText) findViewById(R.id.txtNhave);
        pMinNumber = (EditText) findViewById(R.id.txtNmin);
//        pbatchResult = (EditText) findViewById(R.id.txtbatchResult);
        btnAdd = (Button) findViewById(R.id.btnAdd);

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

//        btnAdd.setBackgroundColor(Color.WHITE);
//        btnAdd.setTextColor(Color.BLACK);
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


        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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