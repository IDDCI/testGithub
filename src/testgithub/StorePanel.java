/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testgithub;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
/**
 *
 * @author jennylim
 */
public class StorePanel extends JPanel implements ActionListener {
    //instance variables
    JButton buyButton, backButton, filterButton;
    JLabel storeTitle, displayCoins, filterLabel;
    JComboBox filterBox;
    JScrollPane itemPane, inventoryPane;
    String[] foodList, bedList, toyList, allItemsList, inventoryListString;
    JList displayedList, inventoryList;
    Animal animal;
    
    
    //constructor
    public StorePanel() {
        
        //to be able to move components around
        this.setLayout(null);
        super.setBackground(Color.pink);
        
        //Exit button
        this.backButton = new JButton("Back");
        this.backButton.setLocation(808, 560);
        this.backButton.setSize(100, 25);
        this.add(backButton); 
        
       
        
    }
    
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    
    public void setComponents() {
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
        this.displayCoins.setLocation(100, 70);
        this.displayCoins.setSize(220,100);
        this.displayCoins.setFont(new Font("Serif", Font.PLAIN, 30));
        this.displayCoins.setVisible(true);
        this.add(displayCoins);
        
        //adding items to item pane
            //getting category list as strings
        this.allItemsList = animal.store.getAllItemsString();
        this.foodList = animal.store.getFoods();
        this.bedList = animal.store.getBeds();
        this.toyList = animal.store.getToys();
        
            //adding to item list
        this.displayedList = new JList(this.animal.store.getAllItemsString());
            //adding jlist to scrollpane
        this.itemPane = new JScrollPane(this.displayedList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.itemPane.setLocation(480, 100);
        this.itemPane.setSize(420,420);
        this.itemPane.setVisible(true);
        this.add(itemPane);
        
        //adding to inventory list
        this.displayedList = new JList(this.animal.store.inventory.getAllItemsString());
            //adding inventory list to scrollpane
        this.itemPane = new JScrollPane(this.displayedList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.itemPane.setLocation(480, 100);
        this.itemPane.setSize(420,420);
        this.itemPane.setVisible(true);
        this.add(itemPane);
        
        //add filter label
        this.filterLabel = new JLabel();
        this.filterLabel.setText("Filter By:"); 
        this.filterLabel.setLocation(105, 140);
        this.filterLabel.setSize(220,100);
        this.filterLabel.setVisible(true);
        this.add(filterLabel);
        
        //add filter droplist
        this.filterBox = new JComboBox <String>();
        this.filterBox.addItem("All");
        this.filterBox.addItem("Foods");
        this.filterBox.addItem("Toys");
        this.filterBox.addItem("Beds");
        this.filterBox.setLocation(100, 205);
        this.filterBox.setSize(170,35);
        this.add(this.filterBox);
        
        //add filter button
        this.filterButton = new JButton("Filter");
        this.filterButton.setLocation(100, 245);
        this.filterButton.setSize(100, 25);
        this.add(filterButton); 
        this.filterButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String filterOption;
                        filterOption = filterBox.getSelectedItem().toString();
                        
                        if (filterOption.equals("All")) {
                            displayedList.setListData(allItemsList);
                            itemPane.setViewportView(displayedList);
                            
                        }
                        if (filterOption.equals("Foods")) {
                            displayedList.setListData(foodList);
                            itemPane.setViewportView(displayedList);
                            
                        }
                        if (filterOption.equals("Toys")) {
                            displayedList.setListData(toyList);
                        }
                        if (filterOption.equals("Beds")) {
                            displayedList.setListData(bedList);
                        }
                            
                        update();
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
                    try{
                        //current displayedlist value contains item and price, split line to get just the item
                        String[] splitLine = ((String)displayedList.getSelectedValue()).split("\t", 2);
                        //check if purchase was successful
                        if (animal.store.buyItem(splitLine[0]))
                        {
                            JOptionPane.showMessageDialog(null, "Purchase Successful!\n"
                            +displayedList.getSelectedValue()+ " added to inventory");
                        }
                        //otherwise display message saying they do not have enough money
                        else
                             JOptionPane.showMessageDialog(null, "You don't have enough to make this purchase!");
                    }
                        //if no items are selected
                    catch(NullPointerException exception) {
                        JOptionPane.showMessageDialog(null, "You haven't selected an item!");
                    }
                    //update coins on screen
                    update();
                }
            }
        );
    }
    
    public JButton getBackButton() {
        return this.backButton;
    }
    
    
    
    //update user view
    public void update() {
        this.displayCoins.setText("Coins: $"+this.animal.store.money.getAmount()); 
        
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
