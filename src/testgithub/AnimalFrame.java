/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testgithub;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author jennylim
 */
public class AnimalFrame extends JFrame {
    AnimalPanel animalPanel;
    
    public AnimalFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.setResizable(false);
        
        //create new instance of animalPanel and add to frame
        this.animalPanel = new AnimalPanel();
        
        
        this.add(this.animalPanel); //add animal panel to the frame first
        this.animalPanel.setLayout(null);
        this.setVisible(true);
        
        this.animalPanel.setVisible(true);
    }
    
    
}
