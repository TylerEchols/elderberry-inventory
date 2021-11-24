package com.example.elderberryinventoryapp;

public class ProductHelperClass {
    String name , id , numberOfHave , minNumber, batchResult;

    public ProductHelperClass() {
    }

    public ProductHelperClass(String name, String id, String numberOfHave, String minNumber, String batchResult) {
        this.name = name;
        this.id = id;
        this.numberOfHave = numberOfHave;
        this.minNumber = minNumber;
        this.batchResult = batchResult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumberOfHave() {
        return numberOfHave;
    }

    public void setNumberOfHave(String numberOfHave) {
        this.numberOfHave = numberOfHave;
    }

    public String getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(String minNumber) {
        this.minNumber = minNumber;
    }

    public String getBatchResult() {
        return batchResult;
    }

    public void setBatchResult(String batchResult) {
        this.batchResult = batchResult;
    }
}
