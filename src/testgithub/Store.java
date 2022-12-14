
package testgithub;

import java.util.HashMap;
/**
 *
 * @author jennylim
 */
public class Store {
    //instance variables
    HashMap food, bed, toy;
    
    Inventory inventory;
    Money money;
    
    //constructor
    public Store(int level) {
        this.inventory = new Inventory();
        this.money = new Money();
        
        this.food = new HashMap();
        this.bed = new HashMap();
        this.toy = new HashMap();
        
    }
    
    //set methods to set items from database
    public void setFoods(HashMap food) {
        this.food = food;
    }
    
    public void setBeds(HashMap bed) {
        this.bed = bed;
    }
    
    public void setToys(HashMap toy) {
        this.toy = bed;
    }
    
    //method to buy items
    public boolean buyItem(String item) {
        //check if item is in food list
        for (Object foodItem: this.food.keySet()) {
            //if found:
            if (foodItem.equals(item)) {
                //check if user has enough money
                if ((Double)this.food.get(foodItem) <= this.money.getAmount())
                {
                    //withdraw amount and add item to inventory
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
        
        //add bed to array from arraylist
        for (Object bedItem: this.bed.keySet()) {
            items[i] = (String)bedItem + "\t$" + this.bed.get(bedItem);
            i++; //increment to get next indexes
        }
        //add food to array from arraylist
        for (Object foodItem: this.food.keySet()) {
            items[i] = (String)foodItem + "\t$" + this.food.get(foodItem);
            i++;
        }
        //add toy to array from arraylist
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
    
    
}