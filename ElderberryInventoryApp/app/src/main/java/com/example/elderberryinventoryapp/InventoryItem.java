package com.example.elderberryinventoryapp;

/*
This class represents the information on the "card" of layout that will
be repeated by the RecyclerView in MainActivity (inventory)
 */

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class InventoryItem {
    // Member Variables for each widget in inventory_item_card.xml
    private String textMinNumber;
    private String textMinLabel;
    //private ImageView imageView;
    private String textItemName;
    private String textHaveNumber;
    private String textHaveLabel;


    // Constructor
    public InventoryItem(String textItemName, String textMinNumber, String textHaveNumber) {
        this.textItemName = textItemName;
        this.textMinNumber = textMinNumber;
        this.textMinLabel = "Min";
        this.textHaveNumber = textHaveNumber;
        this.textHaveLabel = "Have";
        //this.imageView = imageView;
    }


    // Getters and Setters
    public String getTextMinNumber() {
        return textMinNumber;
    }

    public void setTextMinNumber(String textMinNumber) {
        this.textMinNumber = textMinNumber;
    }

    public String getTextMinLabel() {
        return textMinLabel;
    }

    public void setTextMinLabel(String textMinLabel) {
        this.textMinLabel = textMinLabel;
    }

//    public ImageView getImageView() {
//        return imageView;
//    }
//
//    public void setImageView(ImageView imageView) {
//        this.imageView = imageView;
//    }

    public String getTextItemName() {
        return textItemName;
    }

    public void setTextItemName(String textItemName) {
        this.textItemName = textItemName;
    }

    public String getTextHaveNumber() {
        return textHaveNumber;
    }

    public void setTextHaveNumber(String textHaveNumber) {
        this.textHaveNumber = textHaveNumber;
    }

    public String getTextHaveLabel() {
        return textHaveLabel;
    }

    public void setTextHaveLabel(String textHaveLabel) {
        this.textHaveLabel = textHaveLabel;
    }
}