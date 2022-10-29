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

/**
 *
 * @author DDC
 */
public class AnimalDB {

    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    private Animal animal;
    private final String key;

    public AnimalDB(String name, String type, Animal animal) {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
        key = name+"_"+type; //primary key for the pet
        
        //connect animal object
        this.animal = animal;
    }

    // insert Animal into database
    public void insertAnimal() {
        
        try {
            this.statement = this.conn.createStatement();
            this.statement.addBatch("INSERT INTO ANIMAL (" + key + ", " + animal.getAnimalName() + ", " + animal.getAnimalType() + ", " + animal.lvl.getLevel() + ", "
                    + animal.lvl.getXp() + ", " + animal.getHappiness() + ", " + animal.getHealth() + ", " + animal.getHunger() + ", " + animal.getSleep() + ", "
                    + animal.lvl.getLevelXpCap() + ", " + animal.getHappinessCap() + ", " + animal.getHealthCap() + ", " + animal.getHungerCap() + "," + animal.getSleepCap() + "," 
                    + animal.store.money.getAmount()+")");
            this.statement.executeBatch();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Error: Unable to insert into table Animal");
        }
    }

    // If a new item is wanted to be added into the database
    public void insertAnimalInvenDB(String item) {
        try {
            this.statement = this.conn.createStatement();
            ResultSet rs = null;
            String sqlQuery = "SELECT ITEMID, TYPE, ITEM, PRICE FROM STORE WHERE ANIMALID='"+key+"'";
            rs = statement.executeQuery(sqlQuery);
            
            while(rs.next()) {
                if (rs.getString("ITEM").equals(item)) {
                    this.statement.addBatch("INSERT INTO ANIMAL_INVENTORY ("+rs.getFloat("ITEMID")+", "+this.key+")");
                    this.statement.executeBatch();
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Error: Unable to insert in Inventory table");
        }
    }
    
    // Delete animal if it's dead
    public void deleteAnimal() {
        try {
            this.statement = this.conn.createStatement();
            this.statement.addBatch("DELETE FROM ANIMAL WHERE ANIMALID='"+key+"'");
            this.statement.addBatch("DELETE FROM ANIMAL_INVENTORY WHERE ANIMALID='"+key+"'");
            this.statement.executeBatch();
            System.out.println("Animal Deleted");
        } catch (SQLException e) {
            System.out.println("Error: Unable to delete animal");
        }
    }
    
    // Check table for saved data
    public void retrieveAnimal(){
        ResultSet rs = null;
        try {
            this.statement = this.conn.createStatement();
            String sqlQuery = "SELECT NAME, TYPE, LEVEL, XP, HAPPINESS, HEALTH, HUNGER, SLEEP, XPCAP, "
                    + "HAPPYCAP, HEALTHCAP, HUNGERCAP, SLEEPCAP, MONEY FROM ANIMAL WHERE ANIMALID='"+key+"'";
            rs = statement.executeQuery(sqlQuery);
            while(rs.next()){
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
            }
        } catch (SQLException e) {
            System.out.println("No animal of that ID found. New pet being created");
        }
    }

    // Check table for inventory data
    public void retrieveAnimalInvenDB(){
        ResultSet rs = null;
        try {
            this.statement = this.conn.createStatement();
            String sqlQuery = "SELECT ITEM, TYPE, PRICE FROM STORE S, ANIMAL_INVENTORY A_I, ANIMAL A"
                    + " WHERE A_I.ITEMID = S.ITEMID AND A_I.ANIMALID = A.ANIMALID" ;
            rs = statement.executeQuery(sqlQuery);
            while(rs.next()){
                //add items to animal's inventory
                //add to bed
                if (rs.getString("TYPE").equals("BED")) {
                    animal.store.inventory.bed.add(rs.getString("ITEM"));
                }
                //add to food
                if (rs.getString("TYPE").equals("FOOD")) {
                    animal.store.inventory.food.add(rs.getString("ITEM"));
                }
                //add to toy
                if (rs.getString("TYPE").equals("TOY")) {
                    animal.store.inventory.toy.add(rs.getString("ITEM"));
                }
                
            }
        } catch (SQLException e) {
            System.out.println("No items to add to inventory");
        }
    }
    
    //retrieve store items
    public void retrieveStoreDB() {
        try {
            this.statement = this.conn.createStatement();
            ResultSet rs = null;

            String sqlQuery = "SELECT ITEM, TYPE, PRICE FROM STORE";
            rs = statement.executeQuery(sqlQuery);
            while(rs.next()){
                //add items in hashmaps of the store
                //add to bed
                if (rs.getString("TYPE").toUpperCase().equals("BEDS")) {
                    animal.store.bed.put(rs.getString("ITEM"), rs.getDouble("PRICE"));
                    
                }
                //add to food
                if (rs.getString("TYPE").toUpperCase().equals("FOODS")) {
                    animal.store.food.put(rs.getString("ITEM"), rs.getDouble("PRICE"));
                }
                //add to toy
                if (rs.getString("TYPE").toUpperCase().equals("TOYS")) {
                    animal.store.toy.put(rs.getString("ITEM"), rs.getDouble("PRICE"));
                }

            }
        }
        //catch error
        catch(SQLException e)
        {
            System.out.println("Cannot retrieve items from store");
        }
        
    }
    
    public void checkExisting(String name) {
        try {
            DatabaseMetaData MetaData = this.conn.getMetaData();
            String[] types = {"TABLE"};
            statement = this.conn.createStatement();
            ResultSet result = MetaData.getTables(null, null, null, types);
            while (result.next()) {
                String tableTitle = result.getString("TABLE_NAME");
                System.out.println("Table '"+tableTitle+"' exists.");
                if (tableTitle.equalsIgnoreCase(name)) {
                    statement.executeUpdate("DROP TABLE " + name);
                    System.out.println("The table '"+name+"' has been deleted.");
                    break;
                }
            }
            result.close();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Error");
        }
    }

    public void disconnect() {
        this.dbManager.closeConnections();
    }
    
    public void createAnimalDB() {
        try {
            this.statement = this.conn.createStatement();
            this.checkExisting("ANIMAL");
            this.statement.addBatch("CREATE TABLE ANIMAL (ANIMALID VARCHAR(100) NOT NULL PRIMARY KEY, NAME VARCHAR(50) NOT NULL, TYPE VARCHAR(20) NOT NULL, LEVEL INT NOT NULL, "
                    + "XP INT, HAPPINESS INT NOT NULL, HEALTH INT NOT NULL, HUNGER INT NOT NULL, SLEEP INT NOT NULL, XPCAP INT, HAPPYCAP INT NOT NULL, HEALTHCAP INT NOT NULL,"
                    + "HUNGERCAP INT NOT NULL, SLEEPCAP INT NOT NULL, MONEY FLOAT)");
            this.statement.executeBatch();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Error: Unable to create table Animal");
        }
    }
    
    public void createStoreDB() {
        try {
            this.statement = this.conn.createStatement();
            this.checkExisting("STORE");
            this.statement.addBatch("CREATE TABLE STORE (ITEMID INT NOT NULL PRIMARY KEY, TYPE VARCHAR(50),  ITEM VARCHAR(50), PRICE FLOAT)");
            this.statement.addBatch("INSERT INTO STORE VALUES(1, 'foods', 'basic', 10.5), (2, 'foods', 'deluxe', 14.0), (3, 'foods', 'premium', 20.0)"
                    + ", (4, 'toys', 'ball', 7.5), (5, 'toys', 'yarn', 8.0), (6, 'toys', 'chew toy', 11.0), (7, 'toys', 'mice toy', 10.5), (8, 'toys', 'frisbee', 20.0),"
                    + "(9, 'toys', 'laser pointer', 25.0), (10, 'beds', 'double', 11.5), (11, 'beds', 'queen', 23.0), (12, 'beds', 'king', 30.0)");
            this.statement.executeBatch();
            
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Error: Unable to create table Store");
        }
    }
    
    public void createAnimalInvenDB() {
        try {
            this.statement = this.conn.createStatement();
            this.checkExisting("ANIMAL_INVENTORY");
            this.statement.addBatch("CREATE TABLE ANIMAL_INVENTORY (ITEMID INT, ANIMALID VARCHAR(100))");
            this.statement.addBatch("ALTER TABLE ANIMAL_INVENTORY ADD CONSTRAINT inven_fk FOREIGN KEY (ITEMID) REFERENCES STORE(ITEMID)");
            this.statement.addBatch("ALTER TABLE ANIMAL_INVENTORY ADD CONSTRAINT name_type_fk FOREIGN KEY (ANIMALID) REFERENCES ANIMAL(ANIMALID)");
            this.statement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Error: Unable to create table Animal Inventory");
        }
    }
}
