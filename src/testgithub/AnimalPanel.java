package testgithub;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author DDC
 */
public class AnimalPanel extends JPanel {

    JLabel title, typeQ, nameQ;
    JTextField animalName;
    JComboBox animalType;
    JButton submit;
    String Type = null;
    String Name;

    JButton play, feed, sleep, store, exit, instructions;
    JLabel happiness, health, hunger, sleeping;

    Animal animal;
    Dog dog;
    Cat cat;
    StorePanel storePanel;

    public AnimalPanel(StorePanel storePanel) {
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
        this.animalName.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        add(animalName);

        // Button to submit animal type and name
        this.submit = new JButton("Submit");
        this.submit.setLocation(350, 400);
        this.submit.setSize(100, 25);
        add(submit);
        this.submit.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Stores animal type and name
                Type = (String) animalType.getSelectedItem();
                Name = animalName.getText();
                
                // Checks if name entered by user is empty or not
                if (!"".equals(Name.trim()) && Name != null) { // if not empty
                    
                    // Removes old components
                    remove(typeQ);
                    remove(animalType);
                    remove(nameQ);
                    remove(animalName);
                    remove(submit);
                    repaint();

                    // Creates new object depending on the animal type user chose
                    if ("Dog".equals(Type)) {
                        dog = new Dog(Name);

                    } else if ("Cat".equals(Type)) {
                        cat = new Cat(Name);
                    }

                    // Adds new components
                    add(play);
                    add(feed);
                    add(sleep);
                    add(store);
                    add(instructions);

                } else {
                    // If name is empty nothing will be removed or added, just a pop up message to inform the user
                    JOptionPane.showMessageDialog(null, "Name field is empty please enter\na name for the animal");
                }
            }
        }
        );

        // Buttons for actions
        
        // Play
        this.play = new JButton("Play");
        this.play.setLocation(350, 500);
        this.play.setSize(100, 25);
        this.play.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ("Dog".equals(Type)){
                    dog.play();
                } else if ("Cat".equals(Type)){
                    cat.play();
                }
                
            }
        }
        );

        // Feed
        this.feed = new JButton("Feed");
        this.feed.setLocation(450, 500);
        this.feed.setSize(100, 25);
        this.feed.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ("Dog".equals(Type)){
                    dog.feed();
                } else if ("Cat".equals(Type)){
                    cat.feed();
                }
            }
        }
        );

        // Sleep
        this.sleep = new JButton("Sleep");
        this.sleep.setLocation(550, 500);
        this.sleep.setSize(100, 25);
        this.sleep.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ("Dog".equals(Type)){
                    dog.sleeping();
                } else if ("Cat".equals(Type)){
                    cat.sleeping();
                }
            }
        }
        );

        // Button to access store
        this.store = new JButton("Store");
        this.store.setLocation(350, 535);
        this.store.setSize(100, 25);

        JPanel test = this;
        this.store.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //set animal panel to false, displaying store panel
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
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
        );

        // Instruction Button
        this.instructions = new JButton("Instructions");
        this.instructions.setLocation(550, 535);
        this.instructions.setSize(100, 25);

        this.instructions.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Ways to take care of pet :).\n- Playing increases happiness, but decreases hunger and sleep\n"
                                    + "- Feeding increases happiness and hunger\n"
                                    + "- Sleeping increases health and sleep\n"
                                    + "- Play or feed your pet to earn XP, the maximum level your pet can go is level 5\n"
                                    + "- If the health, hunger or happiness fall below 0, then the pet has become too unhealthy and has died. "
                                    + "\n\tTake proper care of your pet to prevent this!\n"
                                    + "- If your pet has full happiness or hunger, you cannot feed them\n"
                                    + "- If your pet is too happy or is too sleepy, they cannot play\n"
                                    + "- If your pet has full sleep or health, they cannot sleep\n"
                                    + "- Pet stats decrease with time so make sure to keep your pet at full health!\n"
                                    + "- Access the store to buy new items for your pet! Leveling up will provide more store items\n"
                                    + "- Items bought from the store will be randomly displayed during feeding or playing\n"
                                    + "- Money is obtained by playing with the pet, you will earn $5 each time your pet plays\n"
                                    + "- Look after your VirtualPet and it will flourish, have fun!\n\n"
                                    + "Please note:\n- Stats do not update automatically, you will need to press 'see current stats' <5>\n"
                                    + "\tor perform an action to see your updated stats \n"
                                    + "- Exiting the game will save your progress. login with your animal type and animal name to load saved data");
            }
        }
        );
    }

    // Draw screen
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(300, 90, 400, 400);
    }
    
    // Draw animal stats (Later on draw animal)
    @Override
    public void paint(Graphics g){
        super.paint(g);
        if (Type != null && !"".equals(Name)) {
            if ("Dog".equals(this.Type)) {
                g.drawString("Happiness: "+dog.getHappiness(), 310, 425);
                g.drawString("Health: "+dog.getHealth(), 310, 440);
                g.drawString("Hunger: "+dog.getHunger(), 310, 455);
                g.drawString("Sleep: "+dog.getSleep(), 310, 470);
            } 
            if ("Cat".equals(this.Type)) {
                g.drawString("Happiness: "+cat.getHappiness(), 310, 425);
                g.drawString("Health: "+cat.getHealth(), 310, 440);
                g.drawString("Hunger: "+cat.getHunger(), 310, 455);
                g.drawString("Sleep: "+cat.getSleep(), 310, 470);
            }
            
        }
        repaint();
    }

}
