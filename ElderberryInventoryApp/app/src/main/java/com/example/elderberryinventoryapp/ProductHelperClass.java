package com.example.elderberryinventoryapp;

import java.io.Serializable;

class ProductHelperClass implements Serializable {
    String name , id , numberOfHave , minNumber;

    public ProductHelperClass() {
    }

    public ProductHelperClass(String name, String id, String numberOfHave, String minNumber) {
        this.name = name;
        this.id = id;
        this.numberOfHave = numberOfHave;
        this.minNumber = minNumber;
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




}
