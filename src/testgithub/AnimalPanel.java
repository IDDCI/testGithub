
package testgithub;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author DDC
 */
public class AnimalPanel extends JPanel{

    
    JLabel title, typeQ, nameQ;
    JTextField animalName;
    JComboBox animalType;
    JButton submit;
    String Type, Name;
    
    JButton play, feed, sleep, store, exit, instructions;
    JLabel happiness, health, hunger, sleeping;
    
    
    Animal animal;
    Dog dog;
    Cat cat;
    StorePanel storePanel;
    
    public AnimalPanel(StorePanel storePanel){
        this.storePanel = storePanel; //provide link to store panel
        
        setLayout(null);
        super.setBackground(Color.pink); //set background to pink
        
        
        // Ask for type of animal
        // title
        this.typeQ = new JLabel("Animal Type:");
        this.typeQ.setLocation(350, 200);
        this.typeQ.setSize(100, 25);
        add(typeQ);
        
        // Drop box
        this.animalType = new JComboBox();
        this.animalType.addItem("Dog");
        this.animalType.addItem("Cat");
        this.animalType.setSize(100, 25);
        this.animalType.setLocation(350, 225);
        add(animalType);
        
        
        // Ask for animal name
        //title
        this.nameQ = new JLabel("Animal Name:");
        this.nameQ.setLocation(350, 300);
        this.nameQ.setSize(100, 25);
        add(nameQ);
        
        // text field
        this.animalName = new JTextField();
        this.animalName.setLocation(350, 325);
        this.animalName.setSize(200, 25);
        add(animalName);
        
        // Button to submit animal type and name
        this.submit = new JButton("Submit");
        this.submit.setLocation(350, 400);
        this.submit.setSize(100, 25);
        add(submit);
        this.submit.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        Type = (String) animalType.getSelectedItem();
                        Name = animalName.getText();
                        if (!"".equals(Name)){
                            remove(typeQ);
                            remove(animalType);
                            remove(nameQ);
                            remove(animalName);
                            remove(submit);
                            
                            if ("Dog".equals(Type)){
                                dog = new Dog(Name); 
                            }
                            
                            else if ("Cat".equals(Type)){
                                cat = new Cat(Name);
                            }
                            
                            add(play);
                            add(feed); 
                            add(sleep); 
                            add(store); 
                            add(exit); 
                            add(instructions);
                        }
                        else {
                           JOptionPane.showMessageDialog(null, "Name field is empty please enter\na name for the animal");
                        }
                    }
                }
        );
         
        // Buttons for actions
        this.play = new JButton("Play");
        this.play.setLocation(350, 500);
        this.play.setSize(100, 25);
        this.play.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        
                    }
                }
        );
        
        
        this.feed = new JButton("Feed");
        this.feed.setLocation(450, 500);
        this.feed.setSize(100, 25);
        this.feed.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        
                    }
                }
        );
        
        this.sleep = new JButton("Sleep");
        this.sleep.setLocation(550, 500);
        this.sleep.setSize(100, 25);
        
        this.sleep.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        
                    }
                }
        );
        
        // Button to access store
        this.store = new JButton("Store");
        this.store.setLocation(350, 535);
        this.store.setSize(100, 25);
        
        JPanel test = this;
        this.store.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        //set animal panel to false, displaying store panel
                        setVisible(false);
                        
                    }
                }
        );
        
        // Exit button
        this.exit = new JButton("Exit");
        this.exit.setLocation(450, 535);
        this.exit.setSize(100, 25);
        
        this.exit.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        
                    }
                }
        );
        
        // Instruction Button
        this.instructions = new JButton("Instructions");
        this.instructions.setLocation(550, 535);
        this.instructions.setSize(100, 25);
         
        this.instructions.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        JOptionPane.showMessageDialog(null, "Bababooey\n newGooey");
                    }
                }
        );
        
        
        
        
    }
}
