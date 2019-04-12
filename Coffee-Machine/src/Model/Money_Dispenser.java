/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import backend.event.engine.Engine;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.events.InsertMoney_EVENT;
import system.views.WaterHeater_VIEW;

/**
 *
 * @author glori
 */
public class Money_Dispenser {

    private static Money_Dispenser moneyDispenser = null;
    
    private Money_Dispenser() {
    }

    public static Money_Dispenser getMoneyDispenser() {
        if (moneyDispenser != null) {
            return moneyDispenser;
        } else {
            moneyDispenser = new Money_Dispenser();
            return moneyDispenser;
        }
    }

    public void swallow() {
        if ("".equals(WaterHeater_VIEW.getWaterHeaterView().getMoneyEntered().getText())) {
            System.out.println("Please Enter Money First");
            WaterHeater_VIEW.getWaterHeaterView().getScreen().setText("Please Enter Money First");
        } else {
            Engine.sendEvent(new InsertMoney_EVENT(Integer.parseInt(WaterHeater_VIEW.getWaterHeaterView().getMoneyEntered().getText()), true));
            WaterHeater_VIEW.getWaterHeaterView().getMoneyEntered().setText("");
        }
    }
    
    public void dispenseMoney() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Money_Dispenser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
