/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testgithub;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author DDC
 */
public class AnimalDB {
    //instance variables
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    private Animal animal;
    private final String key;

    
    //constructor
    public AnimalDB(String name, String type, Animal animal) {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
        key = name + "_" + type; //primary key for the pet

        //connect animal object
        this.animal = animal;
    }

    // insert Animal into database
    public void insertAnimal() {

        try {
            this.statement = this.conn.createStatement();
            //this.statement.addBatch("INSERT INTO ANIMAL VALUES (D_DOG, D, DOG, 1, 1,1,1,1,1,1,1,1,1,1,1)");
            this.statement.addBatch("INSERT INTO ANIMAL VALUES ('" + key + "', '" + animal.getAnimalName() + "', '" + animal.getAnimalType() + "', " + animal.lvl.getLevel() + ", "
                    + animal.lvl.getXp() + ", " + animal.getHappiness() + ", " + animal.getHealth() + ", " + animal.getHunger() + ", " + animal.getSleep() + ", "
                    + animal.lvl.getLevelXpCap() + ", " + animal.getHappinessCap() + ", " + animal.getHealthCap() + ", " + animal.getHungerCap() + "," + animal.getSleepCap() + ","
                    + animal.store.money.getAmount() + ")");
            this.statement.executeBatch();
            System.out.println("Animal inserted :D");
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Error: Unable to insert into table Animal");
        }
    }

    // If a new item is wanted to be added into the database
    public void insertAnimalInvenDB(String item) {
        try {
            //execute sql statement
            this.statement = this.conn.createStatement();
            ResultSet rs = null;
            
            //select statement if statement matches the given item
            String sqlQuery = "SELECT ITEMID, ITEMTYPE, ITEM FROM STORE WHERE ITEM='" + item + "'";
            rs = statement.executeQuery(sqlQuery);
            //if next exists from sql query
            if (rs.next())
            {
                //insert item values into database 
                this.statement.addBatch("INSERT INTO ANIMAL_INVENTORY VALUES (" + rs.getInt("ITEMID") + ", '" + this.key + "')");
                this.statement.executeBatch();
            }
            
            //catch exception
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Error: Unable to insert in Inventory table");
        }
    }

    // Delete animal if it's dead
    public void deleteAnimal() {
        try {
            this.statement = this.conn.createStatement();
            this.statement.addBatch("DELETE FROM ANIMAL_INVENTORY WHERE ANIMALID='" + key + "'");
            this.statement.addBatch("DELETE FROM ANIMAL WHERE ANIMALID='" + key + "'");
            this.statement.executeBatch();
            System.out.println("Animal Deleted");
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Error: Unable to delete animal");
        }
    }
    
    // Check table for saved data
    public boolean checkAnimal(String name, String type) {
        ResultSet rs = null;
        boolean existing = false;
        try {
            this.statement = this.conn.createStatement();
            String sqlQuery = "SELECT NAME, TYPE FROM ANIMAL WHERE ANIMALID='" + key + "'";
            rs = statement.executeQuery(sqlQuery);
            while (rs.next()) {
                if ((rs.getString("NAME").equals(name)) && (rs.getString("TYPE").equals(type))) {
                    existing = true;
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Error with checking for animal");
        }
        
        return existing;
    }

    // Check table for saved data
    public void retrieveAnimal(String name, String type) {
        ResultSet rs = null;
        try {
            this.statement = this.conn.createStatement();
            String sqlQuery = "SELECT NAME, TYPE, LEVEL, XP, HAPPINESS, HEALTH, HUNGER, SLEEP, XPCAP, "
                    + "HAPPYCAP, HEALTHCAP, HUNGERCAP, SLEEPCAP, MONEY FROM ANIMAL WHERE ANIMALID='" + key + "'";
            rs = statement.executeQuery(sqlQuery);
            while (rs.next()) {
                animal.setName(rs.getString("NAME"));
                animal.setAnimalType(rs.getString("TYPE"));
                animal.lvl.setLevel(rs.getInt("LEVEL"));
                animal.lvl.setXp(rs.getInt("XP"));
                animal.setHappiness(rs.getInt("HAPPINESS"));
                animal.setHealth(rs.getInt("HEALTH"));
                animal.setHunger(rs.getInt("HUNGER"));
                animal.setSleep(rs.getInt("SLEEP"));
                animal.lvl.setLevelXpCap(rs.getInt("XPCAP"));
                animal.setHappinessCap(rs.getInt("HAPPYCAP"));
                animal.setHealthCap(rs.getInt("HEALTHCAP"));
                animal.setHungerCap(rs.getInt("HUNGERCAP"));
                animal.setSleepCap(rs.getInt("SLEEPCAP"));
                animal.store.money.setAmount(rs.getDouble("MONEY"));
                
                if ((rs.getString("NAME").equals(name)) && (rs.getString("TYPE").equals(type))) {
                    System.out.println("Animal save file has been found");
                    JOptionPane.showMessageDialog(null, "Animal save file has been found");
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Error with retrieving data from Animal table");
        }
    }

    // Check table for inventory data
    public void retrieveAnimalInvenDB() {
        ResultSet rs = null;
        try {
            this.statement = this.conn.createStatement();
            String sqlQuery = "SELECT ANIMAL_INVENTORY.ANIMALID, ANIMAL_INVENTORY.ITEMID, STORE.ITEM, STORE.ITEMTYPE FROM ANIMAL_INVENTORY, STORE WHERE ANIMAL_INVENTORY.ITEMID = STORE.ITEMID";
            rs = statement.executeQuery(sqlQuery);
            while (rs.next()) {
                //if animalid matches the stored key, proceed to adding inventory items
                if (rs.getString("ANIMALID").equals(key)) {
                    System.out.println(rs.getInt("ITEMID"));
                    //add items to animal's inventory
                    //add to bed
                    if (rs.getString("ITEMTYPE").equals("beds")) {

                        animal.store.inventory.addBed(rs.getString("ITEM"));
                    }
                    //add to food
                    if (rs.getString("ITEMTYPE").equals("foods")) {
                        animal.store.inventory.addFood(rs.getString("ITEM"));
                    }
                    //add to toy
                    if (rs.getString("ITEMTYPE").equals("toys")) {
                        animal.store.inventory.addToy(rs.getString("ITEM"));
                    }

                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("No items to add to inventory");
        } 
    }

    //retrieve store items
    public void retrieveStoreDB() {
        try {
            this.statement = this.conn.createStatement();
            ResultSet rs = null;

            String sqlQuery = "SELECT ITEM, ITEMTYPE, PRICE FROM STORE";
            rs = statement.executeQuery(sqlQuery);
            while (rs.next()) {
                //add items in hashmaps of the store
                //add to bed
                if (rs.getString("ITEMTYPE").toUpperCase().equals("BEDS")) {
                    animal.store.bed.put(rs.getString("ITEM"), rs.getDouble("PRICE"));

                }
                //add to food
                if (rs.getString("ITEMTYPE").toUpperCase().equals("FOODS")) {
                    animal.store.food.put(rs.getString("ITEM"), rs.getDouble("PRICE"));
                }
                //add to toy
                if (rs.getString("ITEMTYPE").toUpperCase().equals("TOYS")) {
                    animal.store.toy.put(rs.getString("ITEM"), rs.getDouble("PRICE"));
                }

            }
        } //catch error
        catch (SQLException e) {
            System.out.println("Cannot retrieve items from store");
        }

    }

    //disconnect database
    public void disconnect() {
        this.dbManager.closeConnections();
    }

    //creating animal database
    public void createAnimalDB() {
        try {
            this.statement = this.conn.createStatement();
            this.statement.addBatch("CREATE TABLE ANIMAL (ANIMALID VARCHAR(100) NOT NULL PRIMARY KEY, NAME VARCHAR(50) NOT NULL, TYPE VARCHAR(20) NOT NULL, LEVEL INT, "
                    + "XP INT, HAPPINESS INT NOT NULL, HEALTH INT NOT NULL, HUNGER INT NOT NULL, SLEEP INT NOT NULL, XPCAP INT, HAPPYCAP INT NOT NULL, HEALTHCAP INT NOT NULL,"
                    + "HUNGERCAP INT NOT NULL, SLEEPCAP INT NOT NULL, MONEY FLOAT)");
            this.statement.executeBatch();
        } catch (SQLException e) {
            //System.out.println("Error: Unable to create table Animal");
        }
    }

    public void createStoreDB() {
        try {
            this.statement = this.conn.createStatement();
            this.statement.addBatch("CREATE TABLE STORE (ITEMID INT NOT NULL PRIMARY KEY, ITEMTYPE VARCHAR(50),  ITEM VARCHAR(50), PRICE FLOAT)");
            this.statement.addBatch("INSERT INTO STORE VALUES(1, 'foods', 'basic', 10.5), (2, 'foods', 'deluxe', 14.0), (3, 'foods', 'premium', 20.0)"
                    + ", (4, 'toys', 'ball', 7.5), (5, 'toys', 'yarn', 8.0), (6, 'toys', 'chew toy', 11.0), (7, 'toys', 'mice toy', 10.5), (8, 'toys', 'frisbee', 20.0),"
                    + "(9, 'toys', 'laser pointer', 25.0), (10, 'beds', 'double', 11.5), (11, 'beds', 'queen', 23.0), (12, 'beds', 'king', 30.0)");
            this.statement.executeBatch();

        } catch (SQLException e) {
            //System.out.println("Error: Unable to create table Store");
        }
    }

    public void createAnimalInvenDB() {
        try {
            this.statement = this.conn.createStatement();
            this.statement.addBatch("CREATE TABLE ANIMAL_INVENTORY (ITEMID INT, ANIMALID VARCHAR(100))");
            this.statement.addBatch("ALTER TABLE ANIMAL_INVENTORY ADD CONSTRAINT inven_fk FOREIGN KEY (ITEMID) REFERENCES STORE(ITEMID)");
            this.statement.addBatch("ALTER TABLE ANIMAL_INVENTORY ADD CONSTRAINT name_type_fk FOREIGN KEY (ANIMALID) REFERENCES ANIMAL(ANIMALID)");
            this.statement.executeBatch();
        } catch (SQLException e) {
            //System.out.println("Error: Unable to create table Animal Inventory");
        }
    }
}
