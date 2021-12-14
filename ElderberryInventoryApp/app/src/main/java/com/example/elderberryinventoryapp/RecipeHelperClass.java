package com.example.elderberryinventoryapp;

import java.io.Serializable;

public class RecipeHelperClass implements Serializable {
    String id , iid ,name ,amount , batchresult , key;

    public RecipeHelperClass() {
    }

    public RecipeHelperClass(String id , String iid, String name, String amount, String batchresult) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.batchresult = batchresult;
        this.iid = iid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBatchresult() {
        return batchresult;
    }

    public void setBatchresult(String batchresult) {
        this.batchresult = batchresult;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
