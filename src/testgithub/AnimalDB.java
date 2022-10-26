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
    
    public AnimalDB(){
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }
    
    public void createAnimalDB(){
        //use the conn, initialize database by creating Animal Table
        try{
            this.statement = this.conn.createStatement();
            this.checkExisting("ANIMAL");
            this.statement.addBatch("CREATE TABLE ANIMAL (ANIMALID VARCHAR(50), NAME VARCHAR(50), TYPE VARCHAR(20), LEVEL INT, "
                    + "XP INT, HAPPINESS INT, HEALTH INT, HUNGER INT, SLEEP INT, XPCAP INT, HAPPYCAP INT, HEALTHCAP INT,"
                    + "HUNGERCAP INT, SLEEPCAP INT)");
            
            this.statement.executeBatch();
        } catch (SQLException e){
            System.out.println("Error: Unable to create table Animal");
        }
    }
    
    public void insertAnimal(){
        try{
            this.statement = this.conn.createStatement();
            key = animal.getAnimalName()+"_"+animal.getAnimalType();
            this.statement.addBatch("INSERT INTO ANIMAL ("+key+", "+animal.getAnimalName()+", "+ animal.getAnimalType() +", "+animal.lvl.getLevel()+", "
                    +animal.lvl.getXp()+", "+animal.getHappiness()+", "+animal.getHealth()+", "+animal.getHunger()+", "+animal.getSleep()+", "
                    +animal.lvl.getLevelXpCap()+", "+animal.getHappinessCap()+", "+animal.getHealthCap()+", "+animal.getHungerCap()+","+animal.getSleepCap()+")");
            
            this.statement.executeBatch();
        } catch (SQLException e){
            System.out.println("Error: Unable to insert into table Animal");
        }
    }
    
    public void createInventoryBD(){
        //use the conn, initialize database by creating FOOD Table
        try{
            this.statement = this.conn.createStatement();
            this.checkExisting("INVENTORTY");
            this.statement.addBatch("CREATE TABLE INVENTORTY (INVENTORYID INT, TYPE VARCHAR(50),  ITEM VARCHAR(50))");
            this.statement.addBatch("INSERT INTO INVENTORTY VALUES(1, foods, basic), (2, foods, deluxe), (3, foods, premium)"
                    + ", (4, toys, ball), (5, toys, yarn), (6, toys, chew toy), (7, toys, mice toy), (8, toys, frisbee),"
                    + "(9, toys, laser pointer), (10, beds, double), (11, beds, queen), (12, beds, king)");
            this.statement.executeBatch();
        } catch (SQLException e){
            System.out.println("Error: Unable to create table Food");
        }
    }
    
    public void insertInventory(String ID, String item){
        try{
            this.statement = this.conn.createStatement();
            this.statement.addBatch("INSERT INTO INVENTORTY ("+ID+", "+ item+")");
            
            this.statement.executeBatch();
        } catch (SQLException e){
            System.out.println("Error: Unable to create table Food");
        }
    }
    
    public void createAnimalInvenDB(){
        try{
            this.statement = this.conn.createStatement();
            this.checkExisting("ANIMAL_INVENTORY");
            this.statement.addBatch("CREATE TABLE ANIMAL_INVENTORTY (INVENTORYID INT, ANIMALID VARCHAR(50))");
            
            this.statement.executeBatch();
        } catch (SQLException e){
            System.out.println("Error: Unable to create table Food");
        }
    }
    
    public void insertAnimalInvenDB(int inventoryID){
        try{
            this.statement = this.conn.createStatement();
            this.statement.addBatch("INSERT INTO ANIMAL_INVENTORTY ("+inventoryID+", "+key+")");
            
            this.statement.executeBatch();
        } catch (SQLException e){
            System.out.println("Error: Unable to create table Food");
        }
    }
    
    
    public void checkExisting(String name){
        try{
            DatabaseMetaData MetaData = this.conn.getMetaData();
            String[] types = {"TABLE"};
            statement = this.conn.createStatement();
            ResultSet result = MetaData.getTables(null, null, null, types);
            while (result.next()) {
                String tableTitle = result.getString("TABLE_NAME");
                System.out.println("Table '"+tableTitle+"' exists.");
                if (tableTitle.equalsIgnoreCase(name)){
                    statement.executeUpdate("Drop table "+name);
                    System.out.println("The table '"+name+"' has been deleted.");
                    break;
                }
            }
            result.close();
        } catch (SQLException e){
            System.out.println("Error");
        }
    }
    
    public void disconnect(){
        this.dbManager.closeConnections();
    }
}
