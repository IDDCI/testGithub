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

    public AnimalDB(String name, String type) {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
        key = name+"_"+type; //primary key for the pet
    }

    // insert Animal into database
    public void insertAnimal() {
        try {
            this.statement = this.conn.createStatement();
            this.statement.addBatch("INSERT INTO ANIMAL ('" + key + "', '" + animal.getAnimalName() + "', '" + animal.getAnimalType() + "', " + animal.lvl.getLevel() + ", "
                    + animal.lvl.getXp() + ", " + animal.getHappiness() + ", " + animal.getHealth() + ", " + animal.getHunger() + ", " + animal.getSleep() + ", "
                    + animal.lvl.getLevelXpCap() + ", " + animal.getHappinessCap() + ", " + animal.getHealthCap() + ", " + animal.getHungerCap() + "," + animal.getSleepCap() + "," 
                    + animal.store.money.getAmount()+")");
            this.statement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Error: Unable to insert into table Animal");
        }
    }

    // If a new item is wanted to be added into the database
    public void insertInventory(int ID, String type, String item, float price) {
        try {
            this.statement = this.conn.createStatement();
            this.statement.addBatch("INSERT INTO INVENTORY (" +ID+ ", " +type+ ", "+item+", "+price+")");
            this.statement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Error: Unable to insert in Inventory table");
        }
    }

    // Stores items that were bought by animal
    public void insertAnimalInvenDB(int inventoryID) {
        try {
            this.statement = this.conn.createStatement();
            this.statement.addBatch("INSERT INTO ANIMAL_INVENTORY (" + inventoryID + ", '" + animal.getAnimalName() + "', '"+animal.getAnimalType()+"')");
            this.statement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Error: Unable to insert in table Animal Inventory");
        }
    }
    
    // Delete animal if it's dead
    public void deleteAnimal() {
        try {
            this.statement = this.conn.createStatement();
            this.statement.addBatch("DELETE FROM ANIMAL WHERE ANIMALID='"+key+"';");
            this.statement.addBatch("DELETE FROM ANIMAL_INVENTORY WHERE ANIMALID='"+key+"';");
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
            System.out.println("Error: Unable to create table Animal");
        }
    }
    
    public void createInventoryBD() {
        try {
            this.statement = this.conn.createStatement();
            this.checkExisting("INVENTORY");
            this.statement.addBatch("CREATE TABLE INVENTORY (INVENTORYID INT NOT NULL PRIMARY KEY, TYPE VARCHAR(50),  ITEM VARCHAR(50), PRICE FLOAT)");
            this.statement.addBatch("INSERT INTO INVENTORY VALUES(1, 'foods', 'basic', 10.5), (2, 'foods', 'deluxe', 14), (3, 'foods', 'premium', 20)"
                    + ", (4, 'toys', 'ball', 7.5), (5, 'toys', 'yarn', 8), (6, 'toys', 'chew toy', 11), (7, 'toys', 'mice toy', 10.5), (8, 'toys', 'frisbee', 20),"
                    + "(9, 'toys', 'laser pointer', 25), (10, 'beds', 'double', 11.5), (11, 'beds', 'queen', 23), (12, 'beds', 'king', 30)");
            this.statement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Error: Unable to create table Inventory");
        }
    }
    
    public void createAnimalInvenDB() {
        try {
            this.statement = this.conn.createStatement();
            this.checkExisting("ANIMAL_INVENTORY");
            this.statement.addBatch("CREATE TABLE ANIMAL_INVENTORY (INVENTORYID INT, ANIMALID VARCHAR(100))");
            this.statement.addBatch("ALTER TABLE ANIMAL_INVENTORY ADD CONSTRAINT inven_fk FOREIGN KEY (INVENTORYID) REFERENCES INVENTORY(INVENTORYID)");
            this.statement.addBatch("ALTER TABLE ANIMAL_INVENTORY ADD CONSTRAINT name_type_fk FOREIGN KEY (ANIMALID) REFERENCES ANIMAL(ANIMALID)");
            this.statement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Error: Unable to create table Animal Inventory");
        }
    }
}
