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
/**
 *
 * @author mohamed
 */
public class WaterHeater extends Thread {
    //private gui;
    private WaterHeater_Light waterHeaterLight;
    private WaterHeater_Sensor waterHeaterSensor;
    private WaterContainer waterContainer;
    private boolean heating;
    
    
    public WaterHeater() {
        
        waterContainer = new WaterContainer(0);
        waterHeaterLight =  new WaterHeater_Light();
        waterHeaterSensor =  new WaterHeater_Sensor(this,waterContainer);
     
        heating = true;
        
        waterHeaterSensor.start();
    }

      public void tempretureSignal(double temp) throws InterruptedException {
        System.out.println("The temp is now " + temp);
        
        if (temp <= 40 ) {
            heating= true;
        }
        else if(temp>80) 
            heating = false;
      }
    

 
       @Override
    public void run() {
        while(true)
        {
          if (heating) {
                    waterContainer.raiseTempreture();
                  System.out.println("TEMP "+ waterContainer.getTempreture() );

                try {
                    this.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(WaterHeater.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
                  if (!heating) {
                    waterContainer.idle();
                  System.out.println("TEMP "+ waterContainer.getTempreture() );
                 
                try {
                    this.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(WaterHeater.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
        }
    }
      
      
}
