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
    protected JButton buyButton, backButton, filterButton;
    private JLabel storeTitle, displayCoins, filterLabel, inventoryTitle;
    private JComboBox filterBox;
    private JScrollPane itemPane, inventoryPane;
    private String[] foodList, bedList, toyList, allItemsList, inventoryListString;
    private JList displayedList, inventoryList;
    private Animal animal;
    
    
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
    
    //set animal to access classes in animal
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    
    //set components of panel
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
        this.displayedList = new JList(allItemsList);
            //adding jlist to scrollpane
        this.itemPane = new JScrollPane(this.displayedList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.itemPane.setLocation(480, 100);
        this.itemPane.setSize(420,420);
        this.itemPane.setVisible(true);
        this.add(itemPane);
        
        //add inventory title
        this.inventoryTitle = new JLabel();
        this.inventoryTitle.setText("Pet's Inventory:");
        this.inventoryTitle.setLocation(100, 300);
        this.inventoryTitle.setSize(170,100);
        this.inventoryTitle.setFont(new Font("Serif", Font.PLAIN, 20));
        this.inventoryTitle.setVisible(true);
        this.add(inventoryTitle);
        
        //adding to inventory list
        this.inventoryListString = animal.store.inventory.getAllItemsString();
        this.inventoryList = new JList(inventoryListString);
            //adding inventory list to scrollpane
        this.inventoryPane = new JScrollPane(this.inventoryList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.inventoryPane.setLocation(100, 370);
        this.inventoryPane.setSize(350,150);
        this.inventoryPane.setVisible(true);
        this.add(inventoryPane);
        
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
                        //get selected filter option
                        filterOption = filterBox.getSelectedItem().toString();
                        
                        //set pane to display all items
                        if (filterOption.equals("All")) {
                            displayedList.setListData(allItemsList);
                        }
                        
                        //set pane to display only foods
                        if (filterOption.equals("Foods")) {
                            displayedList.setListData(foodList);
                        }
                        
                        //set pane to display only toys
                        if (filterOption.equals("Toys")) {
                            displayedList.setListData(toyList);
                        }
                        
                        //set pane to display only beds
                        if (filterOption.equals("Beds")) {
                            displayedList.setListData(bedList);
                        }
                           
                        //update store view
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
    
    //return the back button
    public JButton getBackButton() {
        return this.backButton;
    }
    
    
    
    //update user view
    public void update() {
        //update coins and inventorylist view
        this.displayCoins.setText("Coins: $"+this.animal.store.money.getAmount()); 
        inventoryList.setListData(animal.store.inventory.getAllItemsString());
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
