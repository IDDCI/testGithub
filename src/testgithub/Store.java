/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testgithub;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author jennylim
 */
public class Store {
    //instance variables
    HashMap food, bed, toy;
    ArrayList allItems;
    private int level, purchaseInput;
    

    private Scanner scanner;
    Inventory inventory;
    Money money;
    
    //constructor
    public Store(int level) {
        this.inventory = new Inventory();
        this.money = new Money();
        this.scanner = new Scanner(System.in);
        this.level = level;
        this.food = new HashMap();
        this.bed = new HashMap();
        this.toy = new HashMap();
        
        this.allItems = new ArrayList();
        
        this.addFileItems();

    }

    //used to update level when user levels up
    public void updateLevel(int level) {
        this.level = level;
    }
    
    //to add or update store items based on level
    public void addFileItems() {
        try {
            //read from file
            FileReader fr = new FileReader("./src/testgithub/items.txt");
            BufferedReader inputStream = new BufferedReader(fr);

            String line;
            //while the end of the line is not reached yet..
            
            while ((line = inputStream.readLine()) != null) {

                
                if (!line.equals(String.valueOf(this.level+1))) {
                    //split line into type and price
                    String[] lineSplit = line.split(",", 3);
                    if (lineSplit[0] != null) {
                        
                        //add value as item and money as value
                        switch (lineSplit[0]) {
                            case "toy":
                                this.toy.put(lineSplit[1], lineSplit[2]);
                                this.allItems.add(lineSplit[1]);
                                break;
                            case "bed":
                                this.bed.put(lineSplit[1], lineSplit[2]);
                                this.allItems.add(lineSplit[1]);
                                break;
                            case "food":
                                this.food.put(lineSplit[1], lineSplit[2]);
                                this.allItems.add(lineSplit[1]);
                                break;
                            default:
                                break;
                        }
                    }

                }
                else
                    break;
            }
            
        }
        //catch file errors
        catch (FileNotFoundException e)
        {
            System.out.println("Store Items File not found");
        }
        catch (IOException e) {
            System.out.println("Error reading from file");
        }

    }

    //method called to enter store's user interface and buy items
    public void buyItem() {
        int input;
        boolean loop = true; //to loop until user chooses to exit
        
        //title and explanation
        System.out.println("Welcome to the Pet Store:\n"
                        + "Press x at any time to exit the Pet Store"
                        + "\nPress any non-number key to return to the item category menu");
        
        while (loop) {
            try {
                //display details
                System.out.println("\nYour current money balance is: $" +money.getAmount());
                System.out.print("\nItem Category Menu: \n1. Food\n2. Bed\n3. Toy\n"
                        + "\nWhat category would you like to browse? \n"
                        + "Please enter the corresponding number: ");
                input = scanner.nextInt();
                
                //get user data
                if (input == 1)
                {
                    this.buyFood();
                }
                else if (input == 2)
                {
                    this.buyBed();
                }
                else if (input == 3)
                {
                    this.buyToy();
                }
                else
                    System.out.print("\nError: Number out of range\nPlease enter a corresponding number!\n ");
            //if user enters incorrect input
            } catch (InputMismatchException e) {
                String exit;
                exit = scanner.nextLine();
                //if user enters 'x', then exit the loop and return to Animal menu
                if (exit.equalsIgnoreCase("x"))
                {
                    loop = false;
                }
                else
                {
                    System.out.println("Returning to category menu..");
                }
                
            //if user enters value outside range of array   
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error: Input out of range, returning to category menu");
            }
            
        }
        System.out.println("Returning to Animal Menu...\n");
    } 
    
    //method to purchase food and withdraw money amount
    private void buyFood() {
        //set size for temp depending on food hashmap size
        String[] temp = new String[this.food.size()];
        int i=0;
        //printing food options
        System.out.println("\nCategory: Food ");
        
        //check that the food category is not empty, otherwise display a message
        if (!this.food.keySet().isEmpty()) {
            for (Object s: this.food.keySet())
            {
                System.out.println((i) + ". " + s + " $" + this.food.get(s));
                temp[i] = s.toString();  //add items to list
                i++;
            }


            //user choice input
            System.out.print("Please enter which food you would like to purchase: ");
            purchaseInput = scanner.nextInt();
            //to allow users to enter corresponding number (Because sets are unordered
            //and don't have index numbers
            for (Object s: this.food.keySet())
            {
                //go through hashmap to find item user purchased
                if (s.equals(temp[purchaseInput]))
                {
                    //check if user has enough balance to make purchase
                    if (this.money.getAmount() >= Integer.valueOf((String)this.food.get(s)))
                    {
                        //add item to inventory
                        this.inventory.addFood((String)s);
                        System.out.println("\n" + (String)s + " food successfully added to inventory!");
                        //withdraw money from user's money account
                        this.money.withdrawAmount(Integer.valueOf((String)this.food.get(s)));
                        //print remaining balance
                        System.out.println("Current Balance: " + money.getAmount());

                    }

                    //if user does not have enough money
                    else
                    {
                        //display message
                        System.out.println("\nYou don't have enough money to purchase this item!");
                    }
                }
            }
        }
        else
            System.out.println("There are currently no items in this category!\nLevel up your pet to get more items");
        
    }
    
    //method to purchase bed and withdraw money amount
    private void buyBed() {
        //set temp size depending on bed hashmap size
        String[] temp = new String[this.bed.size()];
        int i=0;
        //print bed options
        System.out.println("\nCategory: Bed ");
        
        //check that the food category is not empty, otherwise display a message
        if (!this.bed.keySet().isEmpty()) {
            for (Object s: this.bed.keySet())
            {
                System.out.println((i) + ". " + s + " $" + this.bed.get(s));
                temp[i] = s.toString();  //add items to list
                i++;
            }


            //user choice input
            System.out.print("Please enter which bed you would like to purchase: ");
            purchaseInput = scanner.nextInt();
            //search hashmap for user's purchased bed
            for (Object s: this.bed.keySet())
            {
                if (s.equals(temp[purchaseInput]))
                {
                    if (this.money.getAmount() >= Integer.valueOf((String)this.bed.get(s)))
                    {
                        //add bed to inventory 
                        this.inventory.addBed((String)s);
                        System.out.println("\n" + (String)s + " bed successfully added to inventory!");
                        //withdraw from balance
                        this.money.withdrawAmount(Integer.valueOf((String)this.bed.get(s)));
                        //print remaining balance
                        System.out.println("Current Balance: " + money.getAmount());

                    }
                    else
                    {
                        System.out.println("\nYou don't have enough money to purchase this item!");
                    }
                }
            }
        }
        else
            System.out.println("There are currently no items in this category!\nLevel up your pet to get more items");
                        
    }
    
    //method to purchase toy and withdraw money amount
    private void buyToy() {
        //set size of temp depending on toy hashmap size
        String[] temp = new String[this.toy.size()];
        int i=0;
        
        //printing toy options
        System.out.println("\nCategory: Toy ");
        
        //check that the food category is not empty, otherwise display a message
        if (!this.toy.keySet().isEmpty()) {
            for (Object s: this.toy.keySet())
            {
                System.out.println(i + ". " + s + " $" + this.toy.get(s));
                temp[i] = s.toString();
                i++;
            }

            //user choice input
            System.out.print("Please enter which toy you would like to purchase: ");
            purchaseInput = scanner.nextInt();
            //search hashmap for user's entered toy choice
            for (Object s: this.toy.keySet())
            {
                if (s.equals(temp[purchaseInput]))
                {
                    if (this.money.getAmount() >= Integer.valueOf((String)this.toy.get(s)))
                    {
                        //add toy to inventory
                        this.inventory.addToy((String)s);
                        System.out.println("\n" + (String)s + " successfully added to inventory!");
                        //withdraw from balance
                        this.money.withdrawAmount(Integer.valueOf((String)this.toy.get(s)));
                        //print remaining balance
                        System.out.println("Current Balance: " + money.getAmount());

                    }
                    else
                    {
                        System.out.println("\nYou don't have enough money to purchase this item!");
                    }
                }
            }
        }       
        else
            System.out.println("There are currently no items in this category!\nLevel up your pet to get more items");
    }
    
    //get all items
    public String[] getAllItems() {
        //return as string
        String[] items = new String[this.allItems.size()];
        int i=0;
        for (int j=0; j<this.allItems.size(); j++) {
            items[i] = (String)this.allItems.get(i);
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
            foods[i] = fooditem.toString();
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
            toys[i] = toyItem.toString();
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
            beds[i] = bedItem.toString();
            i++;
        }
        
        return beds;
    }
}