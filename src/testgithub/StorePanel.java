/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testgithub;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
/**
 *
 * @author jennylim
 */
public class StorePanel extends JPanel implements ActionListener {
    //instance variables
    JButton buyButton, backButton, searchButton;
    JLabel displayCoins, filterLabel;
    JComboBox filterBox;
    JScrollPane itemPane;
    String[] itemListArray;
    JList itemList;
    
    Animal animal;
    
    JPanel animalPanel;
    
    //constructor
    public StorePanel(Animal animal) {
        //setting values
        this.animal = animal;
        this.animalPanel = animalPanel;
        
        //setting values to itemListArray from store class
        ArrayList getItemArray = animal.store.getStoreItems();
        System.out.println(getItemArray.size());
        this.itemListArray = new String[getItemArray.size()];

        for(int i=0;i<getItemArray.size();i++)
        {
            this.itemListArray[i] = (String)getItemArray.get(i);
        }
        
        //to be able to move components around
        this.setLayout(null);
        super.setBackground(Color.pink);
        super.setVisible(true);
        this.itemList = new JList(itemListArray);
        
        
        //adding items to item pane
        this.itemPane = new JScrollPane(this.itemList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
       
        //adding components to frame
        // Exit button
        this.backButton = new JButton("Exit");
        this.backButton.setLocation(450, 535);
        this.backButton.setSize(100, 25);
        this.add(backButton); 
        this.backButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                       
                    }
                }
        );
        
        
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
