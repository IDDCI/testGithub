/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testgithub;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author DDC
 */
public class Health implements Runnable{
    //Instance variable(s)
    Animal animal;
    private long time;

    // Constructor
    public Health(Animal animalParameter) {
        this.animal = animalParameter;
    }

    @Override
    // Run method for when Health thread starts
    public void run() {
        try {
            if (animal.lvl.getLevel() < 3) { // decides time depending on animal level
                time = 60;
            } else {
                time = 50;
            }

            while (animal.getHealth() != 0) {
                TimeUnit.SECONDS.sleep(time); // delay depending on level
                animal.setHealth(animal.getHealth() - 1); // decrease pet's health
                
                // When user exits break while loop to stop it 
                if (animal.isUserExits()){
                    break;
                }
            }
        } catch (InterruptedException e){
            System.out.println("Error Thread interrupted.");
        }
        
        
    }
}