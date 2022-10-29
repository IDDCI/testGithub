package testgithub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
 *
 * @author jennylim
 */
public class Store {
    //instance variables
    HashMap food, bed, toy;
    private ArrayList allItems;
    private int level, purchaseInput;
    
    private Scanner scanner;
    Inventory inventory;
    Money money;
    
    public Store(int level) {
        this.inventory = new Inventory();
        this.money = new Money();
        this.scanner = new Scanner(System.in);
        this.level = level;
        
        this.food = new HashMap();
        this.bed = new HashMap();
        this.toy = new HashMap();
        
        //get saved store items
    }
    
    public void setFoods(HashMap food) {
        this.food = food;
    }
    
    public void setBeds(HashMap bed) {
        this.bed = bed;
    }
    
    public void setToys(HashMap toy) {
        this.toy = bed;
    }
    
    //used to update level when user levels up
    public void updateLevel(int level) {
        this.level = level;
    }
    
    public boolean buyItem(String item) {
        //check if item is in food list
        for (Object foodItem: this.food.keySet()) {
            if (foodItem.equals(item)) {
                
                if ((Double)this.food.get(foodItem) <= this.money.getAmount())
                {
                    this.money.withdrawAmount((Double)this.food.get(foodItem));
                    this.inventory.addFood(item);
                    return true;
                }
            }
        }
        
        //check if item is in toy list
        for (Object toyItem: this.toy.keySet()) {
            if (toyItem.equals(item)) {
                if ((Double)this.toy.get(toyItem) <= this.money.getAmount())
                {
                    this.money.withdrawAmount((Double)this.toy.get(toyItem));
                    this.inventory.addToy(item);
                    return true;
                }
            }
        }
        
        //check if item is in bed list
        for (Object bedItem: this.bed.keySet()) {
            if (bedItem.equals(item)) {
                if ((Double)this.bed.get(bedItem) <= this.money.getAmount())
                {
                    this.money.withdrawAmount((Double)this.bed.get(bedItem));
                    this.inventory.addBed(item);
                    return true;
                }
            }
        }
        
        //if no matches are found return false
        return false;
    }
    
    //get all items return as string with prices
    public String[] getAllItemsString() {
        //return as string
        String[] items = new String[20];
        int i=0;
        
        for (Object bedItem: this.bed.keySet()) {
            items[i] = (String)bedItem + "\t$" + this.bed.get(bedItem);
            i++;
        }
        for (Object foodItem: this.food.keySet()) {
            items[i] = (String)foodItem + "\t$" + this.food.get(foodItem);
            i++;
        }
        for (Object toyItem: this.toy.keySet()) {
            items[i] = (String)toyItem + "\t$" + this.toy.get(toyItem);
            i++;
        }
        
        return items;
    }
    
    
    
    //get individual items
    public String[] getFoods() {
        //return as string array
        String[] foods = new String[this.food.size()];
        int i=0;
        //traverse and add foods to string array
        for (Object fooditem: this.food.keySet())
        {
            foods[i] = fooditem.toString() + "\t$" +this.food.get(fooditem);
            i++;
        }
        
        return foods;
    }
    
    public String[] getToys() {
        //return as string array
        String[] toys = new String[this.toy.size()];
        int i=0;
        //traverse and add toy to string array
        for (Object toyItem: this.toy.keySet())
        {
            toys[i] = toyItem.toString() + "\t$" +this.toy.get(toyItem);
            i++;
        }
        
        return toys;
    }
    
    public String[] getBeds() {
        //return as string array
        String[] beds = new String[this.bed.size()];
        int i=0;
        //traverse and add toy to string array
        for (Object bedItem: this.bed.keySet())
        {
            beds[i] = bedItem.toString() + "\t$" +this.bed.get(bedItem);
            i++;
        }
        
        return beds;
    }
    
    //return items combined as hashmap
    public HashMap getAllItemsHashMap() {
        //combine all items into new hashmap
        HashMap combined = new HashMap();
        combined.putAll(bed);
        combined.putAll(food);
        combined.putAll(bed);
        //return hashmap
        return combined;
    }
    
    
}