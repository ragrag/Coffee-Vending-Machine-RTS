/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import backend.event.engine.Engine;
import system.views.WaterHeater_VIEW;

/**
 *
 * @author glori
 */
public class Money_Sensor {

    public Money_Sensor() {
        Engine.createStatement("select value, condition from InsertMoneyEvent")
                .setSubscriber(new Object() {
                    public void update(int value, boolean condition) throws InterruptedException {
                        System.out.println("Money value: " + value);
                        System.out.println("Money condition: " + condition);
                        MoneyHandler.getMoneyHandler().setValue(value);
                        MoneyHandler.getMoneyHandler().setCondition(condition);
                    }
                });
        
    }
    
    
}
