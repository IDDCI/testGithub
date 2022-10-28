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
    private String key;

    public AnimalDB() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }

    public void createAnimalDB() {
        //use the conn, initialize database by creating Animal Table
        try {
            this.statement = this.conn.createStatement();
            this.checkExisting("ANIMAL");
            this.statement.addBatch("CREATE TABLE ANIMAL (NAME VARCHAR(50) NOT NULL, TYPE VARCHAR(20) NOT NULL, LEVEL INT NOT NULL, "
                    + "XP INT, HAPPINESS INT NOT NULL, HEALTH INT NOT NULL, HUNGER INT NOT NULL, SLEEP INT NOT NULL, XPCAP INT, HAPPYCAP INT NOT NULL, HEALTHCAP INT NOT NULL,"
                    + "HUNGERCAP INT NOT NULL, SLEEPCAP INT NOT NULL, MONEY FLOAT, CONSTRAINT animal_pk PRIMARY KEY (NAME, TYPE))");
            this.statement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Error: Unable to create table Animal");
        }
    }

    public void insertAnimal() {
        try {
            this.statement = this.conn.createStatement();
            this.statement.addBatch("INSERT INTO ANIMAL ('" + animal.getAnimalName() + "', '" + animal.getAnimalType() + "', " + animal.lvl.getLevel() + ", "
                    + animal.lvl.getXp() + ", " + animal.getHappiness() + ", " + animal.getHealth() + ", " + animal.getHunger() + ", " + animal.getSleep() + ", "
                    + animal.lvl.getLevelXpCap() + ", " + animal.getHappinessCap() + ", " + animal.getHealthCap() + ", " + animal.getHungerCap() + "," + animal.getSleepCap() + "," 
                    + animal.store.money.getAmount()+")");

            this.statement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Error: Unable to insert into table Animal");
        }
    }

    public void createInventoryBD() {
        //use the conn, initialize database by creating FOOD Table
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

    public void insertInventory(String ID, String item) {
        try {
            this.statement = this.conn.createStatement();
            this.statement.addBatch("INSERT INTO INVENTORY (" + ID + ", " + item + ")");

            this.statement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Error: Unable to insert in Inventory table");
        }
    }

    public void createAnimalInvenDB() {
        try {
            this.statement = this.conn.createStatement();
            this.checkExisting("ANIMAL_INVENTORY");
            this.statement.addBatch("CREATE TABLE ANIMAL_INVENTORY (INVENTORYID INT, NAME VARCHAR(50), "
                    + "TYPE VARCHAR(50), CONSTRAINT inven_fk FOREIGN KEY (INVENTORYID) REFERENCES INVENTORY(INVENTORYID), "
                    + "CONSTRAINT name_fk FOREIGN KEY (NAME) REFERENCES ANIMAL(NAME), "
                    + "CONSTRAINT type_fk FOREIGN KEY (TYPE) REFERENCES ANIMAL(TYPE))");
            this.statement.executeBatch();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Error: Unable to create table Animal Inventory");
        }
    }

    public void insertAnimalInvenDB(int inventoryID) {
        try {
            this.statement = this.conn.createStatement();
            this.statement.addBatch("INSERT INTO ANIMAL_INVENTORY (" + inventoryID + ", '" + animal.getAnimalName() + "', '"+animal.getAnimalType()+"')");

            this.statement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Error: Unable to insert in table Animal Inventory");
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
                //System.out.println("Table '"+tableTitle+"' exists.");
                if (tableTitle.equalsIgnoreCase(name)) {
                    statement.executeUpdate("Drop table " + name);
                    //System.out.println("The table '"+name+"' has been deleted.");
                    break;
                }
            }
            result.close();
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    public void disconnect() {
        this.dbManager.closeConnections();
    }
}
