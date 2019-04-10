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
 * @author glori
 */
public class MoneyHandler extends Thread {
    private int balance;
    private int value;
    private static Boolean condition;
    
    private static MoneyHandler moneyHandler;
    private MoneyHandler(){
        this.start();
        Money_Sensor.getInstance();
    }
    public static MoneyHandler getMoneyHandler(){
        condition = null;
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

    public void autheniecateMoney(){

    if(condition != null){
                if(!condition)
                {
                    rejectMoney();
                 }
                else
                {
                    acceptMoney();
                }
            }

}
    public void acceptMoney(){
        if(authenticateMoneyValue()){
                        balance += value;
                        value = 0;
                        condition = null;
                        System.out.println("The balance equal "+balance);
                        WaterHeater_VIEW.getWaterHeaterView().getScreen().setText("The balance equal "+balance);
                    }
                    else{
                        Money_Dispenser.getMoneyDispenser().dispenseMoney();
                        condition = null;
                        WaterHeater_VIEW.getWaterHeaterView().getScreen().setText("The balance equal "+balance+"\nRejected Money Value");
                    }
    }
    public void rejectMoney(){
    
           Money_Dispenser.getMoneyDispenser().dispenseMoney();
                    condition = null;
                    WaterHeater_VIEW.getWaterHeaterView().getScreen().setText("The balance equal "+balance+"\nBad Money Condition");
                
    }
    
    @Override
    public void run() {
        while(true){
            autheniecateMoney();
            try {
                this.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(WaterHeater.class.getName()).log(Level.SEVERE, null, ex);
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
