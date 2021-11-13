package com.example.elderberryinventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AEProducts extends AppCompatActivity {

    EditText pName , pid , pNumberOfHave , pMinNumber;
    Button btnAdd , btnClose;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aeproducts);

        pName =(EditText) findViewById(R.id.txtname);
        pid =(EditText) findViewById(R.id.txtid);
        pNumberOfHave =(EditText) findViewById(R.id.txtNhave);
        pMinNumber =(EditText) findViewById(R.id.txtNmin);
        btnAdd =(Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("products");

                String name = pName.getText().toString();
                String id = pid.getText().toString();
                String numberOfHave = pNumberOfHave.getText().toString();
                String minNumber = pMinNumber.getText().toString();
                ProductHelperClass helperClass = new ProductHelperClass(name,id, numberOfHave, minNumber);
//                reference.child(id).setValue(helperClass);
                reference.push().setValue(helperClass);


                Toast toast = Toast.makeText(getApplicationContext(), name+"Saved successfully", Toast.LENGTH_SHORT);
                toast.show();
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
}