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
import system.views.MachineStatsGUI;
import system.views.WaterHeater_VIEW;

/**
 *
 * @author mohamed
 */
public class WaterHeater_Sensor extends Thread {
    private static WaterHeater_Sensor waterheatersensor = null;
    
    

    private WaterHeater_Sensor() {
        
        Engine.createStatement("select waterTempreture from WaterHeater_Sensor_READING")
                .setSubscriber(new Object() {
                    public void update(double temp) throws InterruptedException {
                        System.out.println("Water Tempreture: " + String.format("%.2f", temp));
                        WaterHeater.getWaterHeater().tempretureSignal(temp);
                        //WaterHeater_VIEW.getWaterHeaterView().setTemp(String.format("%.2f", temp));
                        MachineStatsGUI.getInstance().setTemp(String.format("%.2f", temp));
                    }
                });
        this.start();
    }

    public static WaterHeater_Sensor getInstance(){
        if(waterheatersensor!=null)
            return waterheatersensor;
        else{
            waterheatersensor = new WaterHeater_Sensor();
            return waterheatersensor;
        }
    }
    @Override
    public void run() {
        while (true) {

            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(WaterHeater_Sensor.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (CoffeeMachine.getInstance().getPowered()) {
                double temprature = WaterTank.getInstance().getTempreture();
                Engine.sendEvent(new WaterHeater_Sensor_READING(temprature));
                
                  
            } else {
                //WaterHeater_VIEW.getWaterHeaterView().setTemp("Heat Sensor Off");
                MachineStatsGUI.getInstance().setTemp("Heat Sensor Off");
            }
        }
    }
}
