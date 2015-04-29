package com.example.nihal.maindriver;

/**
 * Created by nihal on 8/4/15.
 */
public class MenuItem {

    String itemName;
    double itemCost;

    MenuItem(String itemName, double itemCost){
        this.itemName = itemName;
        this.itemCost = itemCost;
    }

    public String getItemName(){ return this.itemName;}
    public double getItemCost(){ return this.itemCost;}
}
