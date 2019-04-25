/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.events;

/**
 *
 * @author 
 */
public class WaterHeater_Sensor_READING {

    double waterTemperature;

    public WaterHeater_Sensor_READING(double waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public double getWaterTemperature() {
        return waterTemperature;
    }

}
