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
public class SelectionPanel extends Thread {

    private static SelectionPanel selectionpanel;

    Boolean mochabutton = false;
    Boolean espressobutton = false;
    Boolean americanobutton = false;
    Boolean machiattobutton = false;
    Boolean cappuccinobutton = false;
    Boolean lattebutton = false;
    Boolean sugar = false;
    Boolean small = false;
    Boolean medium = false;
    Boolean large = false;

    public SelectionPanel() {
        this.start();
    }

    public static SelectionPanel getInsatance() {

        if (selectionpanel != null) {

            return selectionpanel;
        } else {

            return selectionpanel = new SelectionPanel();
        }
    }

    public void ActivateButtons(Boolean mochabutton, Boolean espressobutton, Boolean americanobutton,
            Boolean machiattobutton, Boolean cappuccinobutton, Boolean lattebutton, Boolean sugar,
            Boolean small, Boolean medium, Boolean large) {

        this.cappuccinobutton = cappuccinobutton;
        this.espressobutton = espressobutton;
        this.lattebutton = lattebutton;
        this.machiattobutton = machiattobutton;
        this.mochabutton = mochabutton;
        this.americanobutton = americanobutton;
        
        this.medium = medium;
        this.large = large;
        this.small = small;
        this.sugar = sugar;

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
                System.out.println("Mocha" + mochabutton);
                WaterHeater_VIEW.getWaterHeaterView().setDrink(Drink.getDrinks().get(0), mochabutton);
                WaterHeater_VIEW.getWaterHeaterView().setDrink(Drink.getDrinks().get(1), espressobutton);
                WaterHeater_VIEW.getWaterHeaterView().setDrink(Drink.getDrinks().get(2), americanobutton);
                WaterHeater_VIEW.getWaterHeaterView().setDrink(Drink.getDrinks().get(3), machiattobutton);
                WaterHeater_VIEW.getWaterHeaterView().setDrink(Drink.getDrinks().get(4), lattebutton );
                WaterHeater_VIEW.getWaterHeaterView().setDrink(Drink.getDrinks().get(5), cappuccinobutton);
          
            } else {

            }
        } //To change body of generated methods, choose Tools | Templates.
    }

}
