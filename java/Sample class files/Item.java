package com.example.kaustubh.homechef.SampleClass;

import android.content.Intent;

public class Item {

    String id,itemName;

    public Item(){

    }

    public Item(String id, String itemName) {
        this.id = id;
        this.itemName = itemName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
