
package testgithub;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;

public class VirtualPetGUI extends JFrame{
    //instance variables
    AnimalPanel animalPanel;
    StorePanel storePanel;
    Animal animal;
    
    public VirtualPetGUI() {
        //set up frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        
        this.setResizable(false);
        
        
        //create new instance of animalPanel and add to frame
        this.animalPanel = new AnimalPanel(this.storePanel);
        
        this.add(this.animalPanel); //add animal panel to the frame first
        this.animalPanel.setLayout(null);
        
        this.getContentPane().setBackground(Color.PINK);
        this.setVisible(true);
        
        this.animalPanel.setVisible(true);
        
        
    }
}
