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
public class Dog extends Animal {
    //Initialize variable(s)
    String animalType;

    // Constructor
    public Dog(String name) {
        super(name);
        super.setAnimalType("Dog");
    }

    // Method for when the user want to feed pet
    @Override
    public void feed() {
        // Makes sure that the stats are less than the cap
        if (this.getHunger() < this.getHungerCap() && this.getHappiness() < this.getHappinessCap()) {
            if (!(this.getHunger() + 2 > this.getHungerCap())) {
                // Add onto stats
                this.setHappiness(this.getHappiness() + 2);
                this.setHunger(this.getHunger() + 1);
                // Adds xp to animal
                lvl.updateXp();
            // If values are going below 0 or above the cap after the method runs
            } else if (this.getHunger() + 2 > this.getHungerCap()) {
                JOptionPane.showMessageDialog(null, this.getAnimalName() + " is going to be too full to eat.");
            }
        // If statements for when values are over or equal to their cap
        } if (this.getHunger() >= this.getHungerCap()) {
            this.setHunger(this.getHungerCap());
            JOptionPane.showMessageDialog(null, this.getAnimalName() + " is too full to eat.");
        } if (this.getHappiness() >= this.getHappinessCap()) {
            this.setHappiness(this.getHappinessCap());
            JOptionPane.showMessageDialog(null, this.getAnimalName() + " has reached max happiness.");
        }
    }

    // Method for when the user wants to play with the pet
    @Override
    public void play() {
        // Makes sure that the stats are less than the cap
        if (this.getHappiness() < this.getHappinessCap() && this.getHunger() != 1 && this.getSleep() != 1) {
            if (!(this.getHappiness() + 2 > this.getHappinessCap()) && !(this.getHunger() - 2 < 0) && !(this.getSleep() - 2 < 0)) {
                // Add onto stats
                this.setHappiness(this.getHappiness() + 2); 
                this.setHunger(this.getHunger() - 2);
                this.setSleep(this.getSleep() - 2);
                // Adds money to user
                this.store.money.addAmount(5);
                // Adds xp to animal
                lvl.updateXp();
            // If values are going below 0 or above the cap after the method runs
            } else if (this.getHappiness() + 2 > this.getHappinessCap()) {
                JOptionPane.showMessageDialog(null, this.getAnimalName() + " will go over max happiness and that's not allowed.");
            } else if (this.getHunger() - 2 < 0) {
                JOptionPane.showMessageDialog(null, this.getAnimalName() + " is going to be too hungry to play.");
            } else if (this.getSleep() - 2 < 0) {
                JOptionPane.showMessageDialog(null, this.getAnimalName() + " is going to be too sleepy to play.");
            }    
        // If statement for when values are over or equal to their cap
        } if (this.getHunger() == 1) {
            JOptionPane.showMessageDialog(null, this.getAnimalName() + " is too hungry to play.");
        } if (this.getSleep() == 1) {
            JOptionPane.showMessageDialog(null, this.getAnimalName() + " is too sleepy to play.");
        } if (this.getHappiness() >= this.getHappinessCap()) {
            this.setHappiness(this.getHappinessCap());
            JOptionPane.showMessageDialog(null, this.getAnimalName() + " has reached max happiness.");
        }

    }

    // Method for when the user wants to make the pet go to sleep
    @Override
    public void sleeping() {
        // Make sure values are less then the cap
        if (this.getSleep() < this.getSleepCap() && this.getHealth() < this.getHealthCap()) {
            // Adds onto values
            this.setSleep(this.getSleep() + 1); 
            this.setHealth(this.getHealth() + 1); 
        // if statements if the value is equal to or over it's current cap
        } if (this.getSleep() >= this.getSleepCap()) {
            this.setSleep(this.getSleepCap());
            JOptionPane.showMessageDialog(null, this.getAnimalName() + " isn't tired enough to go to sleep.");
        } if (this.getHealth() >= this.getHealthCap()) {
            this.setHealth(this.getHealthCap());
            JOptionPane.showMessageDialog(null, this.getAnimalName() + " is too healthy.");
        }
    }

    // toString Method to display information 
    @Override
    public String toString() {
        return this.getAnimalType() + ": " + super.toString();
    }

    
}