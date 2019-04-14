/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import backend.event.engine.*;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.events.*;
import system.views.MachineStatsGUI;
import system.views.WaterHeater_VIEW;

/**
 *
 * @author mohamed
 */
public class WaterHeater extends Thread {

    //private gui;
    private static WaterHeater waterHeater = new WaterHeater();
    private WaterHeater_Light waterHeaterLight;
 
    private boolean heating;

    private WaterHeater() {

        waterHeaterLight = new WaterHeater_Light();
        //WaterHeater_VIEW.getWaterHeaterView().setStatus("Heating");
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
                //WaterHeater_VIEW.getWaterHeaterView().setStatus("Heater turned off");
                MachineStatsGUI.getInstance().setStatus("Heater turned off");
            } else {
                if (heating) {
                    WaterTank.getInstance().raiseTempreture();
                    //WaterHeater_VIEW.getWaterHeaterView().setStatus("HEATING");
                    MachineStatsGUI.getInstance().setStatus("HEATING");
                } else if (!heating) {
                    WaterTank.getInstance().idle();
                    //WaterHeater_VIEW.getWaterHeaterView().setStatus("COOLING");
                    MachineStatsGUI.getInstance().setStatus("COOLING");
                }
            }
            try {
                this.sleep(1400);
            } catch (InterruptedException ex) {
                Logger.getLogger(WaterHeater.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
      
}
