package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText pName , pid;
    Button btnAdd, btnshow ;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pName =(EditText) findViewById(R.id.txtname1);
        pid =(EditText) findViewById(R.id.txtid1);
        btnAdd =(Button) findViewById(R.id.btnAdd);
        btnshow = (Button) findViewById(R.id.btnShow);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("products");

                String name = pName.getText().toString();
                String id = pid.getText().toString();
                ProductHelperClass helperClass = new ProductHelperClass(name,id);
                reference.child(id).setValue(helperClass);
//                reference.push().setValue(helperClass);

            }
        });

        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , ShowActivity.class));

            }
        });


    }
}