package com.example.nihal.maindriver;

/**
 * Created by nihal on 6/4/15.
 */
public class Model{
    String name;
    int value;
    int cost;/* 0 -&gt; checkbox disable, 1 -&gt; checkbox enable */

    Model(String name, int value, int cost){
        this.name = name;
        this.value = value;
        this.cost = cost;

    }
    public String getName(){
        return this.name;
    }
    public int getValue(){
        return this.value;
    }
    public int getCost() {return this.cost;}

}