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
public class VPFrame extends JFrame{
    StorePanel storePanel;
    AnimalPanel animalPanel;
    
    public VPFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.setResizable(false);
        
        this.animalPanel = new AnimalPanel();
        this.storePanel = new StorePanel();
        
        this.add(this.animalPanel); //add animal panel to the frame first
        this.animalPanel.setLayout(null);
        this.setVisible(true);
        this.animalPanel.setVisible(true);
        
        //store and back buttons connect the storepanel and animalpanel therefore 
        //there will be a listener to determine when each is called to switch between
        //the two
        this.animalPanel.getStoreButton().addActionListener( new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    //set animalpanel visibility to false and add the storepanel
                    animalPanel.setVisible(false);
                    addStorePanel();
                    add(storePanel);
                    //set storepanel visibility to true
                    storePanel.setVisible(true);
                    
                    //listen to the store's back button 
                    storePanel.getBackButton().addActionListener( new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e) 
                        {
                            //remove the store panel when the back button is pressed
                            remove(storePanel);
                            //set visibility of animal panel to true
                            animalPanel.setVisible(true);
                        }
                    });
                    revalidate();
                    repaint();
                }
            });
        
    }
    
    //add store panel to frame
    public void addStorePanel() {
        this.storePanel = new StorePanel();
        this.storePanel.setAnimal(animalPanel.animal);
        this.storePanel.setComponents();
    }
    
}

