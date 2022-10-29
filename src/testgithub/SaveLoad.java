/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testgithub;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author jennylim
 */
public class SaveLoad {

    //instance variables
    File userFile;
    FileReader reader;
    BufferedReader inputStream;
    FileWriter writer;
    BufferedWriter outputStream;

    String userFileName;
    String userName;
    String animalType;
    Animal animal;
    
    String imgFileName;
    
    //constructor
    public SaveLoad(String animaltype, String userName, Animal animal) {
        this.animalType = animaltype;
        this.userName = userName;
        this.animal = animal;
        //store the file name when user data is saved
        this.userFileName = animalType.toUpperCase() + "_" + userName.toUpperCase() + "_save.txt";
        
    }

    
    public void createNewFile() {
        //if user does not exist, create a new file with the username
        try {
            userFile = new File("./src/virtualpet/UserFiles/" + this.userFileName);
            //if user does not exist, create a new file
            userFile.createNewFile();


        } //catch file errors
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("/Error reading from file");
        }
    }

    //save user data when user closes pet game
    public void saveUserData() {
        try {
            
            //overwrite file and add new saved data
            this.writer = new FileWriter("./src/virtualpet/UserFiles/" + this.userFileName);
            this.outputStream = new BufferedWriter(writer);

            //get user information
            String name = this.userName;
            String animalType = this.animal.getAnimalType(); 
            int lvl = this.animal.lvl.getLevel();
            int xp = this.animal.lvl.getXp();
            int health = this.animal.getHealth();
            int happiness = this.animal.getHappiness();
            int sleep = this.animal.getSleep();
            int hunger = this.animal.getHunger();
            
            //get max stat values
            int xpCap = this.animal.lvl.getLevelXpCap();
            int healthCap = this.animal.getHealthCap();
            int happinessCap = this.animal.getHappinessCap();
            int sleepCap = this.animal.getSleepCap();
            int hungerCap = this.animal.getHungerCap();
            
            //get inventory items
            ArrayList food = this.animal.store.inventory.getFoodArray();
            ArrayList bed = this.animal.store.inventory.getBedArray();
            ArrayList toy = this.animal.store.inventory.getToyArray();
            
            //writing user data to file
            //user data
            outputStream.write("name:" + name
                    + "\nanimal:" + animalType
                    + "\nlevel:" + lvl
                    + "\nxp:" + xp
                    + "\nhealth:" + health
                    + "\nhappiness:" + happiness
                    + "\nsleep:" + sleep
                    + "\nhunger:" + hunger + "\n");
            
            //current max stats
            outputStream.write("xpCap:" + xpCap
                    + "\nhealthCap:" + healthCap
                    + "\nhappinessCap:" + happinessCap
                    + "\nsleepCap:" + sleepCap
                    + "\nhungerCap:" +hungerCap + "\n");
            
            //items
            outputStream.write("food:");
            
            if (!food.isEmpty()) {
                for (int i = 0; i < food.size(); i++) {
                    outputStream.write(food.get(i).toString());
                    if ((i != food.size()-1))
                        outputStream.write(",");
                }
            }
            
            outputStream.write("\nbed:");
            if (!bed.isEmpty()) {
                for (int i = 0; i < bed.size(); i++) {
                    outputStream.write(bed.get(i).toString());
                    if ((i != bed.size()-1))
                        outputStream.write(",");
                }
            }
            
            outputStream.write("\ntoy:");
            if (!toy.isEmpty()) {
                for (int i = 0; i < toy.size(); i++) {
                    outputStream.write(toy.get(i).toString());
                     if ((i != toy.size()-1))
                        outputStream.write(",");
                }
            }
            
            //writing money anount to file
            outputStream.write("\nmoney: " +animal.store.money.getAmount());
            
            //close bufferedwriter
            outputStream.close();
            
            System.out.println("Data saved successfully!");
            
        } //catch file errors
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }

    }

    //check if the filename exists, if it does then the user exists
    public boolean checkUserExist() {
        //check if the file exists
        File file = new File("./src/virtualpet/UserFiles/" + userFileName);
        
        return file.exists();
    }

    //load user data if existing user is found
    public void loadUserData() {
        try {
            this.reader = new FileReader("./src/virtualpet/UserFiles/" + this.userFileName);
            this.inputStream = new BufferedReader(reader);
        }
        //catch file errors
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        
        //read from user file
        String line;
        try {
            while ((line = inputStream.readLine()) != null) {
                //split when : is found
                String[] lineSplit = line.split(":", 2);
                if (lineSplit[0] != null) {
                    //load user information and stat information
                    if (lineSplit[0].equals("name")) {
                        this.animal.setName(lineSplit[1]);
                    }
                    if (lineSplit[0].equals("animal")) {
                        this.animal.setAnimalType(lineSplit[1]); 
                    }
                    if (lineSplit[0].equals("level")) {
                        this.animal.lvl.setLevel(Integer.valueOf(lineSplit[1]));
                        //update level in store so that items are also updated accordingly
                        this.animal.store.updateLevel(Integer.valueOf(lineSplit[1]));
                        //this.animal.store.addFileItems();
                    }
                    if (lineSplit[0].equals("xp")) {
                        this.animal.lvl.setXp(Integer.valueOf(lineSplit[1]));
                    }
                    if (lineSplit[0].equals("health")) {
                        this.animal.setHealth(Integer.valueOf(lineSplit[1]));
                    }
                    if (lineSplit[0].equals("happiness")) {
                        this.animal.setHappiness(Integer.valueOf(lineSplit[1]));
                    }
                    if (lineSplit[0].equals("sleep")) {
                        this.animal.setSleep(Integer.valueOf(lineSplit[1]));
                    }
                    if (lineSplit[0].equals("hunger")) {
                        this.animal.setHunger(Integer.valueOf(lineSplit[1]));
                    }
                    if (lineSplit[0].equals("xpCap")) {
                        this.animal.lvl.setLevelXpCap(Integer.valueOf(lineSplit[1]));
                    }
                    if (lineSplit[0].equals("healthCap")) {
                        this.animal.setHealthCap(Integer.valueOf(lineSplit[1]));
                    }
                    if (lineSplit[0].equals("happinessCap")) {
                        this.animal.setHappinessCap(Integer.valueOf(lineSplit[1]));
                    }
                    if (lineSplit[0].equals("sleepCap")) {
                        this.animal.setSleepCap(Integer.valueOf(lineSplit[1]));
                    }
                    if (lineSplit[0].equals("hungerCap")) {
                        this.animal.setHungerCap(Integer.valueOf(lineSplit[1]));
                    }
                    
                    //load the items
                    if (lineSplit[0].equals("food")) {
                        String[] foodLineSplit = lineSplit[1].split(",");
                        //if the food array contains items
                        if (!lineSplit[1].isEmpty()) {
                            //add the food items to user inventory
                            for (String foodLineSplit1 : foodLineSplit) {
                                this.animal.store.inventory.addFood(foodLineSplit1);
                            }
                        }
                    }

                    if (lineSplit[0].equals("bed")) {
                        String[] bedLineSplit = lineSplit[1].split(",");
                        
                        //if the bed array contains items
                        if (!lineSplit[1].isEmpty()) {
                            //add the bed items to user inventory
                            for (String bedLineSplit1 : bedLineSplit) {
                                this.animal.store.inventory.addBed(bedLineSplit1);
                            }
                        }

                    }

                    if (lineSplit[0].equals("toy")) {
                        String[] toyLineSplit = lineSplit[1].split(",");
                        
                        //if the toy array contains items
                        if (!lineSplit[1].isEmpty()) {
                            //add the toy items to user inventory
                            for (String toyLineSplit1 : toyLineSplit) {
                                this.animal.store.inventory.addToy(toyLineSplit1);
                            }
                        }

                    }
                    //load user's money amount
                    if (lineSplit[0].equals("money")) {
                        this.animal.store.money.setAmount(Double.valueOf(lineSplit[1]));
                    }
                    
                }
            }
        //catch file errors
        } catch (IOException e) {
            System.out.println("Error reading from file");
        }
    }
    
    //print animal image
    public void loadAnimalImage(String animalType, String emotions) {
        //ensure file name/folder has first letter capitilized
        this.imgFileName = (animalType.substring(0,1).toUpperCase() + animalType.substring(1).toLowerCase())+ "/"+
                (emotions.substring(0,1).toUpperCase() + emotions.substring(1).toLowerCase())+".txt";
        File file = new File("./src/virtualpet/Images/"+this.imgFileName);
        
        //print image line by line
        String line;
        try {
            this.reader = new FileReader(file);
            this.inputStream = new BufferedReader(reader);
            
            //print the image line by line
            while ((line = inputStream.readLine()) != null) {
                     System.out.println(line);
            }
            //catch file errors
        } catch (FileNotFoundException e) {
            System.out.println("Image file not found");
        } catch (IOException e) {
            System.out.println("Error reading from file");
        }
    }
    
    public void loadFoodImage(String foodType) {
        //ensure file name/folder has first letter capitilized
        this.imgFileName = (foodType.substring(0,1).toUpperCase() + foodType.substring(1).toLowerCase())+".txt";
        File file = new File("./src/virtualpet/Images/Food/"+this.imgFileName);
        
        //print image line by line
        String line;
        try {
            this.reader = new FileReader(file);
            this.inputStream = new BufferedReader(reader);
            
            //print the image line by line
            while ((line = inputStream.readLine()) != null) {
                     System.out.println(line);
            }
            //catch file errors
        } catch (FileNotFoundException e) {
            System.out.println("Image file not found");
        } catch (IOException e) {
            System.out.println("Error reading from file");
    }
    }
    
    public void loadToyImage(String toyType) {
        //ensure file name/folder has first letter capitilized
        this.imgFileName = (toyType.substring(0,1).toUpperCase() + toyType.substring(1).toLowerCase())+".txt";
        File file = new File("./src/virtualpet/Images/Toys/"+this.imgFileName);
        
        //print image line by line
        String line;
        try {
            this.reader = new FileReader(file);
            this.inputStream = new BufferedReader(reader);
            
            //print the image line by line
            while ((line = inputStream.readLine()) != null) {
                     System.out.println(line);
            }
            //catch file errors
        } catch (FileNotFoundException e) {
            System.out.println("Image file not found");
        } catch (IOException e) {
            System.out.println("Error reading from file");
        }
    }
    
    //to permanently erase animal data
    public void deleteAnimal(){
        File file = new File("./src/virtualpet/UserFiles/" + userFileName);
        file.delete();
    }
}