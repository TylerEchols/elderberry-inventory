package com.example.elderberryinventoryapp.ui.main;

public class RecipeHelperClass {
    String  p_id ,i_name ,Amount , batch_result;

    public RecipeHelperClass(String p_id, String i_name, String amount, String batch_result) {
        this.p_id = p_id;
        this.i_name = i_name;
        Amount = amount;
        this.batch_result = batch_result;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getI_name() {
        return i_name;
    }

    public void setI_name(String i_name) {
        this.i_name = i_name;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getBatch_result() {
        return batch_result;
    }

    public void setBatch_result(String batch_result) {
        this.batch_result = batch_result;
    }
}
