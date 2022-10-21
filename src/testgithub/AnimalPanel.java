
package testgithub;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author DDC
 */
public class AnimalPanel extends JPanel{
    Animal animal;
    JButton play, feed, sleep, store, exit, instructions;
    JLabel happiness, health, hunger, sleeping;
    JLabel title, typeQ, nameQ;
    JTextField animalTpye, animalName;
    
    public AnimalPanel(){
        setLayout(null);
        super.setBackground(Color.pink); //set background to pink
        
        // Buttons for actions
        this.play = new JButton("Play");
        this.play.setLocation(350, 500);
        this.play.setSize(100, 25);
        add(play); 
        this.play.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        
                    }
                }
        );
        
        
        this.feed = new JButton("Feed");
        this.feed.setLocation(450, 500);
        this.feed.setSize(100, 25);
        add(feed); 
        this.feed.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        
                    }
                }
        );
        
        this.sleep = new JButton("Sleep");
        this.sleep.setLocation(550, 500);
        this.sleep.setSize(100, 25);
        add(sleep); 
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
        add(store); 
        JPanel test = this;
        this.store.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        
                        setVisible(false);
                    }
                }
        );
        
        // Exit button
        this.exit = new JButton("Exit");
        this.exit.setLocation(450, 535);
        this.exit.setSize(100, 25);
        add(exit); 
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
        add(instructions); 
        this.instructions.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        JOptionPane.showMessageDialog(null, "Bababooey\n newGooey");
                    }
                }
        );
        
        // Showing animal stats
        //this.happiness = new JLabel ("Happiness:"+ animal.getHappiness());
        //this.happiness.setLocation(300, 400);
        //this.happiness.setSize(100, 25);
        //add(happiness);
        //this.happiness.setEnabled(false);
        
        //this.health = new JLabel ("Health:"+ animal.getHealth());
        //this.health.setLocation(300, 430);
        //this.health.setSize(100, 25);
        //add(health);
        //this.health.setEnabled(false);
        
        
        // Ask for type of animal
        // title
        
        // text field
        
        // Ask for animal name
        //title
        
        // text field
        
        
    }
}
