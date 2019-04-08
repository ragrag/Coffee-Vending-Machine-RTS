/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.events;

/**
 *
 * @author mohamed
 */
public class WaterHeater_Sensor_READING {
    double waterTempreture;

    
    
    public WaterHeater_Sensor_READING(double waterTempreture) {
        this.waterTempreture = waterTempreture;
    }

   
    public double getWaterTempreture() {
        return waterTempreture;
    }
    
    
    
}
