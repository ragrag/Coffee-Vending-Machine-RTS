/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import backend.event.engine.Engine;
import java.util.Random;

/**
 *
 * @author glori
 */
public class Money_Sensor {
    private static  Money_Sensor moneySensor;
    
    public static Money_Sensor getInstance(){
        if(moneySensor!= null)
            return moneySensor;
        else
            moneySensor= new Money_Sensor();
            return moneySensor;
        }

    private Money_Sensor() {
        Engine.createStatement("select value, condition from InsertMoney_EVENT")
                .setSubscriber(new Object() {
                    public void update(int value, boolean condition) throws InterruptedException {
                        Random r=new Random();
                        condition =r.nextBoolean();
                        MoneyHandler.getMoneyHandler().setValue(value);
                        MoneyHandler.getMoneyHandler().setCondition(condition);
                    }
                });
    }
}
