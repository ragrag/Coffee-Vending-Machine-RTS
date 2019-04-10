/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import backend.event.engine.Engine;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.events.WaterHeater_Sensor_READING;
import system.views.WaterHeater_VIEW;

/**
 *
 * @author Mostafa
 */
public class SelectionPanel extends Thread{
    private static SelectionPanel selectionpanel;
    
    Boolean mochabutton;
    Boolean espressobutton;
    Boolean mochaccinobutton;
    Boolean machiattobutton;
    Boolean cappuccinobutton;
    Boolean lattebutton;
    Boolean sugar;
    Boolean small;
    Boolean medium;
    Boolean large;

    public SelectionPanel() {
        this.start();
    }
    
        public static SelectionPanel getInsatance(){
    
        if(selectionpanel != null){
        
            return selectionpanel;
        }
        else{
        
            return selectionpanel = new SelectionPanel();
        }
    }

public void ActivateButtons(Boolean mochabutton,Boolean espressobutton,Boolean mochaccinobutton,
    Boolean machiattobutton,Boolean cappuccinobutton,Boolean lattebutton,Boolean sugar,
    Boolean small,Boolean medium,Boolean large){
  
    this.cappuccinobutton=cappuccinobutton;
    this.espressobutton=espressobutton;
    this.large=large;
    this.lattebutton=lattebutton;
    this.machiattobutton=machiattobutton;
    this.medium=medium;
    this.mochabutton=mochabutton;
    this.mochaccinobutton=mochaccinobutton;
    this.small=small;
    this.sugar=sugar;
    


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
                System.out.println("Mocha"+mochabutton);
                WaterHeater_VIEW.getWaterHeaterView().setTemp("Heat Sensor Off");
                  
            } else {
                
            }
        } //To change body of generated methods, choose Tools | Templates.
    }
    
        
    
}
