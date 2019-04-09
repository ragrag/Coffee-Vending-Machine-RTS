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
import system.views.WaterHeater_VIEW;

/**
 *
 * @author mohamed
 */
public class WaterHeater extends Thread {

    //private gui;
    private static final WaterHeater waterHeater = new WaterHeater();
    private WaterHeater_Light waterHeaterLight;
    private WaterHeater_Sensor waterHeaterSensor;
    private WaterTank waterContainer;
    private boolean heating;

    private WaterHeater() {

        waterContainer = new WaterTank(0);
        waterHeaterLight = new WaterHeater_Light();
        waterHeaterSensor = new WaterHeater_Sensor(waterContainer);
        WaterHeater_VIEW.getWaterHeaterView().setStatus("Heating");
        heating = true;

       
        this.start();
    }

    public void tempretureSignal(double temp) throws InterruptedException {

        if (temp <= 40) {
            heating = true;
        } else if (temp > 80) {
            heating = false;
        }
    }

    public static WaterHeater getWaterHeater() {
        return waterHeater;
    }

    @Override
    public void run() {
        while (true) {
            if (!CoffeeMachine.getCoffeeMachine().getPowered()) {
                heating = false;
                waterContainer.idle();
                WaterHeater_VIEW.getWaterHeaterView().setStatus("Heater turned off");
            } else {
                if (heating) {
                    waterContainer.raiseTempreture();
                    WaterHeater_VIEW.getWaterHeaterView().setStatus("HEATING");
                } else if (!heating) {
                    waterContainer.idle();
                    WaterHeater_VIEW.getWaterHeaterView().setStatus("COOLING");
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
