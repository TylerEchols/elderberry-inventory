package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText pName , pid;
    Button btnAdd;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pName =(EditText) findViewById(R.id.txtname);
        pid =(EditText) findViewById(R.id.txtid);
        btnAdd =(Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("products");

                String name = pName.getText().toString();
                String id = pid.getText().toString();
                ProductHelperClass helperClass = new ProductHelperClass(name,id);
//                reference.child(id).setValue(helperClass);
                reference.push().setValue(helperClass);

            }
        });

    }
}