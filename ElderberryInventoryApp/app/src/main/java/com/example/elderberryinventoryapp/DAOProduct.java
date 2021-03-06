package com.example.elderberryinventoryapp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOProduct {

        private DatabaseReference databaseReference;
        public DAOProduct()
        {
            FirebaseDatabase db =FirebaseDatabase.getInstance();
//            databaseReference = db.getReference(ProductHelperClass.class.getSimpleName());

            databaseReference = db.getReference("products");

        }
        public Task<Void> add(ProductHelperClass pro)
        {
            return databaseReference.push().setValue(pro);
        }

        public Task<Void> update(String id, HashMap<String ,Object> hashMap)
        {
            return databaseReference.child(id).updateChildren(hashMap);
        }
        public Task<Void> remove(String id)
        {
            return databaseReference.child(id).removeValue();
        }

        public Query get(String id)
        {
            if(id == null)
            {
                return databaseReference.orderByKey().limitToFirst(8);
            }
            return databaseReference.orderByKey().startAfter(id).limitToFirst(8);
        }

        public Query get()
        {
            return databaseReference;
        }

    public Query getFilter(String str)
    {
        if (str == null)
        {
            return databaseReference.orderByKey().limitToFirst(8);
        }
        return databaseReference.orderByChild("category").equalTo(str);
    }

}
