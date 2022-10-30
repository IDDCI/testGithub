/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testgithub;

import javax.swing.JOptionPane;

/**
 *
 * @author DDC
 */
public abstract class Animal{

    // Instance variables that all animals will have 
    private String animalName;
    private int happiness;
    private int happinessCap;
    private int health;
    private int healthCap;
    private int hunger;
    private int hungerCap;
    private int sleeping;
    private int sleepingCap;
    private String animalType;
    
    private boolean play;
    private boolean feed;
    private boolean sleep;
    
    //to determine whether animal needs to be erased
    private boolean deleteAnimal;
    
    // Determines if user exits
    private boolean userExits;
    
    // Object connecting to level system
    public Level lvl;
    // Object connecting to store, inventory and money class
    public Store store;
    
    // Initialize thread objects
    Thread happyThread;
    Thread healthThread;
    Thread hungerThread;
    Thread sleepThread;
    
    AnimalDB animalDB;
    
    // Default constructor
    public Animal(String animalName) {
        this.animalName = animalName;
        lvl = new Level(this);
        store = new Store(lvl.getLevel());
        
        // Creating animal stats
        animalStats();
        lvl.levelStats();
        
        // Start threads
        startingThreads();
        
        // Set delete animal to false to not delete it (will be used later on to delete)
        this.deleteAnimal = false;
        
        // Set userExit to false until they want to exit
        this.userExits = false;
        
        // Connect to Database
        animalDB = new AnimalDB(animalName, getAnimalType(), this);
        animalDB.createAnimalDB();
        animalDB.createStoreDB();
        
         //get store items if there are saved user items
        animalDB.retrieveStoreDB();
        animalDB.createAnimalInvenDB();
        animalDB.retrieveAnimalInvenDB();
    }
    
    public void startingThreads(){
        // Making and starting threads
        // Happiness Thread
        Happiness happy = new Happiness(this);
        this.happyThread = new Thread(happy);
        this.happyThread.start();
        
        // Health Thread
        Health healthy = new Health(this);
        this.healthThread = new Thread(healthy);
        this.healthThread.start();
        
        // Hunger Thread 
        Hunger hunger = new Hunger(this);
        this.hungerThread = new Thread(hunger);
        this.hungerThread.start();
        
        // Sleeping Thread
        Sleep sleep = new Sleep(this);
        this.sleepThread = new Thread(sleep);
        this.sleepThread.start();
    }

    // Contains animal base stats and will only be accessed if animal levels up
    public void animalStats() {
        switch (lvl.getLevel()) {
            case 1:
                this.happiness = 5;
                this.happinessCap = 10;
                this.health = 30;
                this.healthCap = 30;
                this.hunger = 10;
                this.hungerCap = 20;
                this.sleeping = 20;
                this.sleepingCap = 20;
                break;
            case 2:
                this.happiness = 15;
                this.happinessCap = 15;
                this.health = 35;
                this.healthCap = 35;
                this.hunger = 20;
                this.hungerCap = 20;
                this.sleeping = 20;
                this.sleepingCap = 20;
                break;
            case 3:
                this.happiness = 20;
                this.happinessCap = 20;
                this.health = 40;
                this.healthCap = 40;
                this.hunger = 25;
                this.hungerCap = 25;
                this.sleeping = 25;
                this.sleepingCap = 25;
                break;
            case 4:
                this.happiness = 30;
                this.happinessCap = 30;
                this.health = 40;
                this.healthCap = 40;
                this.hunger = 30;
                this.hungerCap = 30;
                this.sleeping = 30;
                this.sleepingCap = 30;
                break;
            case 5:
                this.happiness = 30;
                this.happinessCap = 30;
                this.health = 100;
                this.healthCap = 100;
                this.hunger = 30;
                this.hungerCap = 30;
                this.sleeping = 30;
                this.sleepingCap = 30;
                break;
        }
    }
    
    // Abstract methods
    public abstract void feed();

    public abstract void play();

    public abstract void sleeping();

    // Check if animal has 0 happiness
    public void sadness() {
        //if (this.getHappiness() <= 5 && this.getHappiness() > 0) { // Inform user when pet is getting low happiness
            //JOptionPane.showMessageDialog(null, this.getAnimalName() + " is growing sad, make them happy");
        //}
        if (this.getHappiness() == 0) { // Deletes animal from txt files if happiness is 0
            //JOptionPane.showMessageDialog(null, this.getAnimalName() + " has grown too sad and has died (x.x)");
            this.setDeleteAnimal(true);
            this.setUserExits(true);
        }
    }

    // Check if animal has 0 health
    public void unwell() {
        //if (this.getHealth() <= 5 && this.getHealth() > 0) { // Infrom user when pet has low health
            //JOptionPane.showMessageDialog(null, this.getAnimalName() + " is becoming unwell, help them get better.");
        //}
        if (this.getHealth() == 0) {// Deletes animal from txt files if health is 0
            //JOptionPane.showMessageDialog(null, this.getAnimalName() + " has become unwell and has died (x.x)");
            this.setDeleteAnimal(true);
            this.setUserExits(true);
        }
    }

    // Check if animal has 0 hunger
    public void starve() {
        //if (this.getHunger() <= 5 && this.getHunger() > 0) { // Inform user when pet has low hunger
            //JOptionPane.showMessageDialog(null, this.getAnimalName() + " is going to starve, feed them.");
        //}
        if (this.getHunger() == 0) { // Deletes animal from txt files if hunger is 0
            //JOptionPane.showMessageDialog(null, this.getAnimalName() + " has starved to death and died (x.x)");
            this.setDeleteAnimal(true);
            this.setUserExits(true);
        }
    }

    // Check if animal has 0 sleep
    public void notSleeping() {
        // sets sleep to 0 so that it's not shown as a negative number
        if (this.getSleep() == 0) {
            this.setSleep(0);
        }
    }

    @Override
    public String toString() {
        // Display animal's name and lvl, current xp and xp cap
        return this.getAnimalName() + " is level " + lvl.getLevel()+" |current xp: "+lvl.getXp()+"/"+lvl.getLevelXpCap()+"|";
    }
    
    public void diconnectDB(){
        animalDB.disconnect();
    }
    
    // Getters and setters
    public String getAnimalName() {
        return animalName;
    }

    public void setName(String animalName) {
        this.animalName = animalName;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public int getHappinessCap() {
        return happinessCap;
    }

    public void setHappinessCap(int happinessCap) {
        this.happinessCap = happinessCap;
    }
    
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealthCap() {
        return healthCap;
    }

    public void setHealthCap(int healthCap) {
        this.healthCap = healthCap;
    }
    
    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getHungerCap() {
        return hungerCap;
    }

    public void setHungerCap(int hungerCap) {
        this.hungerCap = hungerCap;
    }

    public int getSleep() {
        return sleeping;
    }

    public void setSleep(int sleep) {
        this.sleeping = sleep;
    }

    public int getSleepCap() {
        return sleepingCap;
    }

    public void setSleepCap(int sleepCap) {
        this.sleepingCap = sleepCap;
    }
    
    public String getAnimalType() {
        //returns the name of the subclass of this animal class
        return this.getClass().getSimpleName();
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }
    
    public void setDeleteAnimal(boolean delete) {
        this.deleteAnimal = delete;
    }
    
    public boolean getDeleteAnimal() {
        return this.deleteAnimal;
    }

    public boolean isUserExits() {
        return userExits;
    }

    public void setUserExits(boolean userExits) {
        this.userExits = userExits;
    }

    /**
     * @return the play
     */
    public boolean isPlay() {
        return play;
    }

    /**
     * @param play the play to set
     */
    public void setPlay(boolean play) {
        this.play = play;
    }

    /**
     * @return the feed
     */
    public boolean isFeed() {
        return feed;
    }

    /**
     * @param feed the feed to set
     */
    public void setFeed(boolean feed) {
        this.feed = feed;
    }

    /**
     * @return the sleep
     */
    public boolean isSleep() {
        return sleep;
    }

    /**
     * @param sleep the sleep to set
     */
    public void setSleep(boolean sleep) {
        this.sleep = sleep;
    }
}