/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.logging.Level;
import java.util.logging.Logger;
import system.views.WaterHeater_VIEW;

/**
 *
 * @author ragrag
 */
public class TransactionProcessor extends Thread{
    
    private static TransactionProcessor transactionInstance = null;
    Drink drink;
    int size;
    int suger;
    Boolean state=null;
    int balance;
    int orderPrice;
    int change;

    public static TransactionProcessor getInstance(){
        
        if(transactionInstance != null)
            return transactionInstance;
        else {    
            return transactionInstance = new TransactionProcessor();
        }
        
    }
    public TransactionProcessor() {
        this.start();
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSuger(int suger) {
        this.suger = suger;
    }

    public void startOrder(){
        this.state=false;
    
    }
    
    @Override
    public void run() {
        //super.run(); //To change body of generated methods, choose Tools | Templates.

        
        
        
        
    }
    
    
}
