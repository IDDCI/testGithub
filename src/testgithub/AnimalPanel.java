package testgithub;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

/**
 *
 * @author DDC
 */
public class AnimalPanel extends JPanel {
    // variable initialization

    // Menu initializations
    JLabel title, typeQ, nameQ;
    JTextField animalName;
    JComboBox animalType;
    JButton submit;
    String Type = null;
    String Name;

    // Animal frame components initialization
    JButton play, feed, sleep, store, exit, instructions;
    // variable for when the pet is sleeping  
    String sleeping;
    int sleepCounter;

    // Objects to connect to other classes
    Animal animal;
    StorePanel storePanel;

    // Image initialization
    // Dog and Cat
    Image diedDC, happyDC, sleepyDC, wantDC;

    // Toys
    // Food
    public AnimalPanel() {
        
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
                if (!"".equals(Name.trim()) && Name != null) { // if name not empty

                    // Removes old components
                    remove(typeQ);
                    remove(animalType);
                    remove(nameQ);
                    remove(animalName);
                    remove(submit);
                    repaint();

                    // Creates new object depending on the animal type user choses
                    // initializes animal images to use
                    if ("Dog".equals(Type)) {
                        animal = new Dog(Name);
                        happyDC = new ImageIcon("./src/virtualpet/Images/Dog/Happy.png").getImage();
                        sleepyDC = new ImageIcon("./src/virtualpet/Images/Dog/Sleepy.png").getImage();
                        wantDC = new ImageIcon("./src/virtualpet/Images/Dog/want.png").getImage();
                        diedDC = new ImageIcon("./src/virtualpet/Images/Dog/Died.png").getImage();
                    } else if ("Cat".equals(Type)) {
                        animal = new Cat(Name);
                        happyDC = new ImageIcon("./src/virtualpet/Images/Cat/Happy.png").getImage();
                        sleepyDC = new ImageIcon("./src/virtualpet/Images/Cat/Sleepy.png").getImage();
                        wantDC = new ImageIcon("./src/virtualpet/Images/Cat/want.png").getImage();
                        diedDC = new ImageIcon("./src/virtualpet/Images/Cat/Died.png").getImage();
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
                animal.play();

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
                animal.feed();
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
                animal.sleeping();
                sleeping = "sleeping";
                sleepCounter = 0;
            }
        }
        );

        // Button to access store
        this.store = new JButton("Store");
        this.store.setLocation(350, 535);
        this.store.setSize(100, 25);
        //this.store.addActionListener(
        //        new ActionListener() {
        //    public void actionPerformed(ActionEvent e) {        
        //    }
        //}
        //);

        // Exit button
        this.exit = new JButton("Exit");
        this.exit.setLocation(450, 535);
        this.exit.setSize(100, 25);
        add(exit);
        this.exit.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //animal.animalDB.insertAnimal();
                
                //animal.diconnectDB();
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
                        + "Please note:\n- Exiting the game will save your progress. login with your animal type and animal name to load saved data");
            }
        }
        );
    }

    // Draw square screen where animal and stats will be displayed
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(250, 90, 500, 400);
    }

    // Draw animal stats animal
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (Type != null && !"".equals(Name)) {
            // Draws default Animal (either Dog or Cat)
            g.drawImage(happyDC, 300, 150, this);
            // if happiness, health or hunger is low this show want face
            if ((animal.getHappiness() <= 5 && animal.getHappiness() > 0)
                    || animal.getHealth() <= 5 && animal.getHealth() > 0
                    || animal.getHunger() <= 5 && animal.getHunger() > 0) {
                g.drawImage(wantDC, 300, 150, this);
                g.drawString("Your pet has low stats", 400, 440);
                g.drawString("Select an action to bring stats up.", 400, 455);
            }

            // if you put animal to sleep
            if ("sleeping".equals(this.sleeping) && sleepCounter < 1750) {
                g.drawImage(sleepyDC, 300, 150, this);
                sleepCounter++;
            }
            
            if (sleepCounter == 1750){
                sleeping = null;
            }

            //check animal stats are too low
            // sets delete animal to true if one of the stats are low
            // also changes status of user exists so that it stops threads
            animal.sadness();
            animal.unwell();
            animal.starve();
            animal.notSleeping();

            // checks if animal will get deleted for dying
            if (animal.getDeleteAnimal()) {
                g.drawImage(diedDC, 300, 150, this);
                this.sleep.setEnabled(false);
                this.play.setEnabled(false);
                this.feed.setEnabled(false);
                this.store.setEnabled(false);
                g.drawString("Pet has died and data is not saved.", 400, 440);
                g.drawString("Please exit the game and try again.", 400, 455);
            }

            g.drawString(animal.toString(), 260, 410);
            g.drawString("Happiness: " + animal.getHappiness(), 260, 425);
            g.drawString("Health: " + animal.getHealth(), 260, 440);
            g.drawString("Hunger: " + animal.getHunger(), 260, 455);
            g.drawString("Sleep: " + animal.getSleep(), 260, 470);
            
            
        }
        repaint();

    }

    // get storePanel update method to update money
    private void updateStore() {
        this.storePanel.update();
    }

    // get store button to put in main frame to be able to access store
    public JButton getStoreButton() {
        return this.store;
    }

}
