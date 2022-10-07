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
public class Happiness implements Runnable {
    //Instance variable(s)
    Animal animal;
    private long time;

    // Constructor
    public Happiness(Animal animalParameter) {
        this.animal = animalParameter;
    }

    @Override
    // Run method for when Happiness thread starts
    public void run() {
        try {
            if (animal.lvl.getLevel() < 3) { // decides time depending on animal level
                time = 45;
            } else {
                time = 30;
            }

            while (animal.getHappiness() != 0) {
                TimeUnit.SECONDS.sleep(time); // delay depending on level
                animal.setHappiness(animal.getHappiness() - 1); // decrease pet's happiness
                
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