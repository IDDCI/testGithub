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
public class Cat extends Animal {

    // Constructor
    public Cat(String name) {
        super(name);
        super.setAnimalType("Cat");
        
    }
    
    // Method for when the user want to feed pet
    @Override
    public void feed() {
        // Makes sure that the stats are less than the cap
        if (this.getHunger() < this.getHungerCap() && this.getHappiness() < this.getHappinessCap()) {
            // Add onto stats
            this.setHappiness(this.getHappiness() + 1);
            this.setHunger(this.getHunger() + 2);
            // Adds xp to animal
            lvl.updateXp();
            // Sets feed to true for the animal panel
            this.setFeed(true);
        } 
        // If statements for when values are over or equal to their cap
        if (this.getHunger() >= this.getHungerCap()) {
            this.setHunger(this.getHungerCap());
            this.setFeed(false);
            JOptionPane.showMessageDialog(null, this.getAnimalName() + " is too full to eat.");
        } 
        if (this.getHappiness() >= this.getHappinessCap()) {
            this.setHappiness(this.getHappinessCap());
            this.setFeed(false);
            JOptionPane.showMessageDialog(null, this.getAnimalName() + " has reached max happiness.");
        }

    }

    // Method for when the user wants to play with pet
    @Override
    public void play() {
        // Makes sure that the stats are less than the cap
        if (this.getHappiness() < this.getHappinessCap() && this.getHunger() != 1 && this.getSleep() != 1) {
            if (!(this.getHunger() - 2 <= 0) && !(this.getSleep() - 2 <= 0)) {
                // Add onto stats
                this.setHappiness(this.getHappiness() + 1);
                this.setHunger(this.getHunger() - 2);
                this.setSleep(this.getSleep() - 2);
                // Add money to user
                this.store.money.addAmount(5);
                // Adds xp to animal
                lvl.updateXp();
                // Sets play to true for the animal panel
                this.setPlay(true);
            // If values are going below 0 or above the cap after the method runs
            } else if (this.getHunger() - 2 <= 0) {
                this.setPlay(false);
                JOptionPane.showMessageDialog(null, this.getAnimalName() + " is going to be too hungry to play.");
            } else if (this.getSleep() - 2 <= 0) {
                this.setPlay(false);
                JOptionPane.showMessageDialog(null, this.getAnimalName() + " is going to be too sleepy to play.");
            }
        // If statement for when values are over or equal to their cap    
        } if (this.getHunger() == 1) {
            this.setPlay(false);
            JOptionPane.showMessageDialog(null, this.getAnimalName() + " is too hungry to play.");
        } if (this.getSleep() == 1) {
            this.setPlay(false);
            JOptionPane.showMessageDialog(null, this.getAnimalName() + " is too sleepy to play.");
        } if (this.getHappiness() >= this.getHappinessCap()) {
            this.setHappiness(this.getHappinessCap());
            this.setPlay(false);
            JOptionPane.showMessageDialog(null, this.getAnimalName() + " has reached max happiness.");
        }

    }

    // Method for when the user wants to make the pet go to sleep
    @Override
    public void sleeping() {
        // Makes sure that the stats are less than the cap
        if (this.getSleep() < this.getSleepCap() && this.getHealth() < this.getHealthCap()) {
            if (!(this.getSleep() + 2 > this.getSleepCap()) && !(this.getHealth() + 2 > this.getHealthCap())) {
                // Add onto stats
                this.setSleep(true);
                this.setSleep(this.getSleep() + 2);
                this.setHealth(this.getHealth() + 2);
                // Sets sleep to true for the animal panel
                this.setSleep(true);
             // If values are going below 0 or above the cap after the method runs
            } else if (this.getSleep() + 2 > this.getSleepCap()) {
                this.setSleep(false);
                JOptionPane.showMessageDialog(null, this.getAnimalName() + " is going to have too much energy when he wakes up and cannot sleep now.");
            } else if (this.getHealth() + 2 > this.getHealthCap()) {
                this.setSleep(false);
                JOptionPane.showMessageDialog(null, this.getAnimalName() + " is going to be too healthy and cannot sleep now.");
            }
        // Else statement for when values are over or equal to their cap 
        } if (this.getSleep() > this.getSleepCap()) {
            this.setSleep(this.getSleepCap());
            this.setSleep(false);
            JOptionPane.showMessageDialog(null, this.getAnimalName() + " isn't tired enough to go to sleep.");
        } if (this.getHealth() > this.getHealthCap()) {
            this.setHealth(this.getHealthCap());
            this.setSleep(false);
            JOptionPane.showMessageDialog(null, this.getAnimalName() + " is too healthy.");
        }
    }

    // toString method to display animal information
    @Override
    public String toString() {
        return this.getAnimalType() + ": " + super.toString();
    }
    
}