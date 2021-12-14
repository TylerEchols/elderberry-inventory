package com.example.elderberryinventoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AEIngredient extends AppCompatActivity implements AdapterInterface{

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference referenceR = db.getReference().child("recipe");
    Button btnCancel, btnSaveRecipe;
    private ArrayList<ProductHelperClass> itemsList;
    private RecyclerView recyclerView;
    ingredientRecyclerAdapter adapter;
    EditText edtNum;
    DAOProduct dao;
    boolean saveClicked = false;
    String pid;

    ArrayList<ProductHelperClass> inglist;
    ArrayList<String> fetchList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //Hide the action bar
        setContentView(R.layout.activity_aeingredient);
        fetchList= new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("pid")!= null) {
            pid = bundle.getString("pid");
            fetchList = bundle.getStringArrayList("idlist");
        }

        edtNum = findViewById(R.id.editTextNumber);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        itemsList = new ArrayList<>();
        adapter= new ingredientRecyclerAdapter(this, itemsList, this);

        inglist = new ArrayList<>();
        loadData2();

        initializedButtons();
    }
    private void initializedButtons(){
       btnCancel =findViewById(R.id.btnCancel);
       btnCancel.setOnClickListener(v -> {
           saveClicked = false;
           finish();
       });

        btnSaveRecipe = findViewById(R.id.btnSaveIngredient);
        btnSaveRecipe.setOnClickListener(v -> {
            Toast toast;
            if (inglist != null ) {
                for (ProductHelperClass pro : inglist) {
                    String name = pro.getName();
                    String iid = pro.getId();
                    String Amount = edtNum.getText().toString();
                    String batchResult = "10";
                    RecipeHelperClass helperClass = new RecipeHelperClass(pid, iid, name, Amount, batchResult);
                    referenceR.push().setValue(helperClass); // Generate primary key randomly
                }
            }
            saveClicked = true;
            finish();
        });
    }



    private void loadData2(){

        recyclerView.setAdapter(adapter);
        dao = new DAOProduct();
        dao.getFilter("Ingredients").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ProductHelperClass> pros = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren())
                {
                    ProductHelperClass pro = data.getValue(ProductHelperClass.class);
                    if (fetchList.indexOf(pro.getId()) == -1)
                        pros.add(pro);
                }
                adapter.setItems(pros);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public void getlist(ProductHelperClass ingredient, boolean isChecked) {
        if (ingredient != null  )
        {
            if (isChecked == true) inglist.add(ingredient);
            else inglist.remove(inglist.indexOf(ingredient));
                Toast toast = Toast.makeText(getApplicationContext(),"Name= "+ ingredient.getName() + isChecked +", Id= "+ingredient.getId(), Toast.LENGTH_SHORT);
                toast.show();
            }
    }
}