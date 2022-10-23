/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testgithub;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    JLabel storeTitle, displayCoins, filterLabel;
    JComboBox filterBox;
    JScrollPane itemPane;
    String[] itemListArray;
    JList foodList, bedList, toyList, allItemsList;
    
    Animal animal;
    
    AnimalPanel animalPanel;
    
    //constructor
    public StorePanel(Animal animal, AnimalPanel animalPanel) {
        //setting values
        this.animal = animal;
        this.animalPanel = animalPanel; //provide link to animal panel
        
        //to be able to move components around
        this.setLayout(null);
        super.setBackground(Color.pink);
        
        
       
        //adding components to frame
        //add Store title
        this.storeTitle = new JLabel();
        this.storeTitle.setText("Store");
        this.storeTitle.setLocation(430, 0);
        this.storeTitle.setSize(170,100);
        this.storeTitle.setFont(new Font("Serif", Font.PLAIN, 50));
        this.storeTitle.setVisible(true);
        this.add(storeTitle);
                
        //display coins
        this.displayCoins = new JLabel();
            //get money amount from money class
        this.displayCoins.setText("Coins: $"+this.animal.store.money.getAmount()); 
        this.displayCoins.setLocation(100, 60);
        this.displayCoins.setSize(220,100);
        this.displayCoins.setFont(new Font("Serif", Font.PLAIN, 30));
        this.displayCoins.setVisible(true);
        this.add(displayCoins);
        
        //adding items to item pane
        this.foodList = new JList(this.animal.store.getFoods());
        this.bedList = new JList(this.animal.store.getBeds());
        this.toyList = new JList(this.animal.store.getToys());
        this.allItemsList = new JList(this.animal.store.getAllItems());
        
        this.itemPane = new JScrollPane(this.allItemsList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.itemPane.setLocation(480, 100);
        this.itemPane.setSize(420,420);
        this.itemPane.setVisible(true);
        this.add(itemPane);
        
        //Exit button
        this.backButton = new JButton("Back");
        this.backButton.setLocation(808, 560);
        this.backButton.setSize(100, 25);
        this.add(backButton); 
        this.backButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        //bring animalPanel back to front
                       animalPanel.setVisible(true);
                       
                    }
                }
        );
        
        //Buy button
        this.buyButton = new JButton("Buy");
        this.buyButton.setLocation(700, 560);
        this.buyButton.setSize(100, 25);
        this.add(buyButton); 
        this.buyButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                       
                    }
                }
        );
    }
    
    public void update() {
        System.out.println(this.animal.store.money.getAmount());
        this.displayCoins.setText("Coins: $"+this.animal.store.money.getAmount()); 
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
