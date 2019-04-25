/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.logging.Level;
import java.util.logging.Logger;
import system.views.CoffeeMachineGUI;

/**
 *
 * @author 
 */
public class MoneyHandler extends Thread {
    private int balance;
    private int value;
    private Boolean condition;
    private static MoneyHandler moneyHandler;
    
    private MoneyHandler(){
        resetBalance();
        this.start();
        balance=0;
        condition = null;
        Money_Sensor.getInstance();
    }
    
    public static MoneyHandler getMoneyHandler(){
        if (moneyHandler!=null)
            return moneyHandler;
        else
        {
            moneyHandler = new MoneyHandler();
            return moneyHandler;
        }
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    public void resetBalance(){
        balance=0;
    }
    
    public void authenticateMoney(){
        if(condition != null){
                if(condition)
                    acceptMoney();
                else
                    rejectMoney();
            }
    }
    
    public void acceptMoney(){
        if(authenticateMoneyValue()){
            balance += value;
            value = 0;
            condition = null;
            TransactionProcessor.getInstance().setBalance(balance);
            Screen.getScreen().display("The balance equal "+balance);
        }else{
            Money_Dispenser.getMoneyDispenser().dispenseMoney();
            condition = null;
            Screen.getScreen().display("The balance equal "+balance+"\nRejected Money Value");
            }
    }
    
    public void rejectMoney(){
        Money_Dispenser.getMoneyDispenser().dispenseMoney();
        condition = null;
        Screen.getScreen().display("The balance equal "+balance+"\nBad Money Condition");     
    }
    
    public void returnMoneyToUser(int money){
        try {
            balance = 0;
            Money_Dispenser.getMoneyDispenser().dispenseCoins(money);
            Screen.getScreen().display("Balance : "+balance+"\nMoney Dispensed : "+money);
            Thread.sleep(3000);
            Screen.getScreen().display("Thank you :)");
            Thread.sleep(2000);
            Screen.getScreen().display("Hello! Please select your drink :)");
        } catch (InterruptedException ex) {
        }
        
    }
    
    @Override
    public void run() {
        while(true){
            authenticateMoney();
            try {
                this.sleep(500);
            } catch (InterruptedException ex) {
            }
        }
    }
    
    public boolean authenticateMoneyValue(){
        if(value%5==0 && value<=20 && value>0)
            return true;
        else
            return false;
    }
}
