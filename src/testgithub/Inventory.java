/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testgithub;

import java.util.ArrayList;

/**
 *
 * @author jennylim
 */
public class Inventory {
    //instance variables
    ArrayList food;
    ArrayList bed;
    ArrayList toy;
    
    ArrayList allItems;
    
    //constructor
    public Inventory() {
        this.food = new ArrayList();
        this.bed = new ArrayList();
        this.toy = new ArrayList();
        
        this.allItems = new ArrayList();
        
    }
    
    //add methods
    public void addFood(String food) {
        this.food.add(food);
        
        this.allItems.add(food);
    }
    
    public void addBed(String bed) {
        this.bed.add(bed);
        
        this.allItems.add(bed);
    }
    
    public void addToy(String toy) {
        this.toy.add(toy);
        
        this.allItems.add(toy);
    }
    
    //get methods for loading
    public ArrayList getFoodArray() {
        return this.food;
    }
    
    public ArrayList getBedArray() {
        return this.bed;
    }
    
    public ArrayList getToyArray() {
        return this.toy;
    }
    
    public ArrayList getAllItems() {
        return this.allItems;
    }
    
    public String[] getAllItemsString() {
        String[] items = new String[20];
        
        for (int i=0; i<this.allItems.size(); i++) {
            items[i] = (String)this.allItems.get(i);
        }
        
        return items;
    }
    
}