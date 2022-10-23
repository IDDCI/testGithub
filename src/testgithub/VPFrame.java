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
        
        this.animalPanel.getStoreButton().addActionListener( new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    getContentPane().removeAll();
                    addStorePanel();
                    //calcPanel.setVisible(true);
                    add(storePanel);
                    revalidate();
                    repaint();
                }
            });
        
        this.storePanel.getBackButton().addActionListener( new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                    //calcPanel.setVisible(true);
                    System.out.println("hi there");
                    getContentPane().removeAll();
                    add(animalPanel);
                    revalidate();
                    repaint();
            }
        });
    }
    
    public void addStorePanel() {
        this.storePanel = new StorePanel();
        this.storePanel.setAnimal(animalPanel.animal);
        this.storePanel.setComponents();
    }
    
}

