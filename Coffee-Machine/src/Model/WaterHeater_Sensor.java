/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import backend.event.engine.Engine;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.events.WaterHeater_Sensor_READING;

/**
 *
 * @author mohamed
 */
public class WaterHeater_Sensor extends Thread{
    
    
  
    WaterHeater waterHeater;
    private WaterContainer waterContainer;
    
    
    public WaterHeater_Sensor(WaterHeater waterHeater, WaterContainer waterContainer) {
                this.waterHeater= waterHeater;
                this.waterContainer = waterContainer;
        Engine.createStatement("select waterTempreture from WaterHeater_Sensor_READING")
                .setSubscriber(new Object() {
                    public void update(double temp) throws InterruptedException {
                        waterHeater.tempretureSignal(temp);
                    }
                });
    }
    
    

    @Override
    public void run() {
        while (true) {
           
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(WaterHeater_Sensor.class.getName()).log(Level.SEVERE, null, ex);
            }
          
            Engine.sendEvent(new WaterHeater_Sensor_READING(waterContainer.getTempreture()));
        }
    }
}
