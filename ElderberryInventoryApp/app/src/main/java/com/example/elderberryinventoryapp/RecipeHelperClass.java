package com.example.elderberryinventoryapp;

public class RecipeHelperClass {
    String id ,name ,amount , batchresult;

    public RecipeHelperClass(String id, String name, String amount, String batchresult) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.batchresult = batchresult;
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
}
