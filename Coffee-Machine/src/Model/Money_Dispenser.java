/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import backend.event.engine.Engine;
import system.events.InsertMoneyEvent;
import system.views.WaterHeater_VIEW;

/**
 *
 * @author glori
 */


public class Money_Dispenser {
    private static Money_Dispenser moneyDispenser;
    
    private Money_Dispenser(){
    }
    
    public static Money_Dispenser getMoneyDispenser(){
        if (moneyDispenser!=null)
            return moneyDispenser;
        else
        {
            moneyDispenser = new Money_Dispenser();
            return moneyDispenser;
        }
    }
    public void swallow(){
        Engine.sendEvent(new InsertMoneyEvent(Integer.parseInt(WaterHeater_VIEW.getWaterHeaterView().getMoneyEntered().getText()),true));
    }
    
    public void dispenseMoney(){
    }
    
    
}
