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

/**
 *
 * @author 
 */
public class WaterHeater_Sensor extends Thread {
    private static WaterHeater_Sensor waterheatersensor = null;
    
    private WaterHeater_Sensor() {
                Engine.createStatement("select waterTemperature from WaterHeater_Sensor_READING")
                .setSubscriber(new Object() {
                    public void update(double temp) throws InterruptedException {
                        WaterHeater.getInstance().tempretureSignal(temp);
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
            }
            if (CoffeeMachine.getInstance().getPowered()) {
                double temprature = WaterTank.getInstance().getTempreture();
                Engine.sendEvent(new WaterHeater_Sensor_READING(temprature));
            } else {
                MachineStatsGUI.getInstance().setTemp("Heat Sensor Off");
            }
        }
    }
}
