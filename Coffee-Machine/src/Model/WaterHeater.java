/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.logging.Level;
import java.util.logging.Logger;
import system.views.MachineStatsGUI;

/**
 *
 * @author 
 */
public class WaterHeater extends Thread {
    private static WaterHeater waterHeater = new WaterHeater();
    private boolean heating;

    private WaterHeater() {
        MachineStatsGUI.getInstance().setStatus("Heating");
        heating = true;
        WaterTank.getInstance();
        WaterHeater_Sensor.getInstance();
       
        this.start();
    }

    public void tempretureSignal(double temp) throws InterruptedException {
        if (temp <= 40) {
            heating = true;
        } else if (temp > 80) {
            heating = false;
        }
    }

    public static WaterHeater getInstance() {
        return waterHeater;
    }

    @Override
    public void run() {
        while (true) {
            if (!CoffeeMachine.getInstance().getPowered()) {
                heating = false;
                WaterTank.getInstance().idle();
                MachineStatsGUI.getInstance().setStatus("Heater turned off");
            } else {
                if (heating) {
                    WaterTank.getInstance().raiseTempreture();
                    MachineStatsGUI.getInstance().setStatus("HEATING");
                } else if (!heating) {
                    WaterTank.getInstance().idle();
                    MachineStatsGUI.getInstance().setStatus("COOLING");
                }
            }
            try {
                this.sleep(1400);
            } catch (InterruptedException ex) {
            }
        }
    }

    
      
}
