/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testgithub;

/**
 *
 * @author jennylim
 */
public class Money {
    //instance variables
    private double currentAmount;
    
    //default constructor
    public Money() {
        this.currentAmount = 15;
    }
    
    //method to add to current amount
    public void addAmount(double amountToAdd) {
        this.currentAmount += amountToAdd;
    }
    
    //method to take out frmo current aomunt
    public void withdrawAmount(double amountToWithdraw) {
        this.currentAmount -= amountToWithdraw;
    }
    
    //get and set methods
    public void setAmount(double amount) {
        this.currentAmount = amount;
    }
    public double getAmount() {
        return this.currentAmount;
    }
}