/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testgithub;

/**
 *
 * @author DDC
 */
public class Level {

    // Instance variables
    private int level;
    private int levelXpCap;
    private int xp;
    Animal animal;
    
    // Default Constructor
    public Level(Animal animal){
        this.level = 1;
        this.animal = animal;
    }
    
    // Sets the xp and xp cap depending on the level
    public void levelStats(){
        switch(this.getLevel()){
            case 1:
                this.xp = 0;
                this.levelXpCap = 20;
                break;
            case 2:
                this.xp = 0;
                this.levelXpCap = 30;
            case 3:
                this.xp = 0;
                this.levelXpCap = 100;
                break;
            case 4:
                this.xp = 0;
                this.levelXpCap = 320;
                break;
            case 5:
                this.xp = 0;
                this.levelXpCap = 0;
                break;
        }
    }
    
    // Getters and Setters
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }
    
    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevelXpCap() {
        return this.levelXpCap;
    }
    
    public void setLevelXpCap(int xpCap) {
        this.levelXpCap = xpCap;
    }
    // Add xp to animal
    public void updateXp() {
        // if xp is lower than xp cap
        if (this.xp < this.levelXpCap){
            this.xp++;
        }
        // if xp is equals to lvl cap and lower than 0
        else if (this.getLevel() < 5){
            this.level++;
            //update store's user level and add appropriate items depending on new level
            animal.store.updateLevel(this.level);
            animal.store.addFileItems();
            levelStats();
            animal.animalStats();
        } 
        // This msg will only display if the pet is lvl as lvl 5 pets cannot gain anymore xp
        else {
            System.out.println("Pet is already at max level and cannot earn more xp.");
        }
    }

}