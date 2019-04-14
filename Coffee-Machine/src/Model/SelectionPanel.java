/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import backend.event.engine.Engine;
import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.Random;
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
        
         Engine.createStatement("select drink from SelectDrink_EVENT")
                .setSubscriber(new Object() {
                    public void update(Drink d) throws InterruptedException {
                        System.out.println("drink: "+ d.getName());
                        TransactionProcessor.getInstance().setDrink(d);
                    }
                });
         
         Engine.createStatement("select sugerSelection from SelectSuger_EVENT")
                .setSubscriber(new Object() {
                    public void update(int suger) throws InterruptedException {
                        System.out.println("suger: "+ suger);
                        TransactionProcessor.getInstance().setSuger(suger);
                    }
                });
        
         Engine.createStatement("select size from SelectSize_EVENT")
                .setSubscriber(new Object() {
                    public void update(int size) throws InterruptedException {
                        System.out.println("size: "+size);
                        TransactionProcessor.getInstance().setSize(size);
                    }
                });
         
         Engine.createStatement("select startOrder from Start_EVENT")
                .setSubscriber(new Object() {
                    public void update(Boolean startOrder) throws InterruptedException {
                        System.out.println("startOrder: "+startOrder);
                        TransactionProcessor.getInstance().startOrder();
                    }
                });
         Engine.createStatement("select cancel from Cancel_EVENT")
                .setSubscriber(new Object() {
                    public void update(Boolean cancel) throws InterruptedException {
                        System.out.println("cancel: "+cancel);
                        TransactionProcessor.getInstance().cancel();
                    }
                });
         
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
                
                if(this.americanobutton)
                    WaterHeater_VIEW.getWaterHeaterView().getAmericano().setBackground(Color.green);
                else
                    WaterHeater_VIEW.getWaterHeaterView().getAmericano().setBackground(Color.red);
                
                if(this.cappuccinobutton)
                    WaterHeater_VIEW.getWaterHeaterView().getCappuccino().setBackground(Color.green);
                else
                    WaterHeater_VIEW.getWaterHeaterView().getCappuccino().setBackground(Color.red);
                
                if(this.espressobutton)
                    WaterHeater_VIEW.getWaterHeaterView().getEspresso().setBackground(Color.green);
                else
                    WaterHeater_VIEW.getWaterHeaterView().getEspresso().setBackground(Color.red);
                
                if(this.lattebutton)
                    WaterHeater_VIEW.getWaterHeaterView().getLatte().setBackground(Color.green);
                else
                    WaterHeater_VIEW.getWaterHeaterView().getLatte().setBackground(Color.red);
                
                if(this.machiattobutton)
                    WaterHeater_VIEW.getWaterHeaterView().getMacchiato().setBackground(Color.green); 
                else
                    WaterHeater_VIEW.getWaterHeaterView().getMacchiato().setBackground(Color.red); 
                
                if(this.mochabutton)
                    WaterHeater_VIEW.getWaterHeaterView().getMocha().setBackground(Color.green);
                else
                    WaterHeater_VIEW.getWaterHeaterView().getMocha().setBackground(Color.red);
                
                if(this.small)
                    WaterHeater_VIEW.getWaterHeaterView().getSmall().setBackground(Color.green);
                else
                    WaterHeater_VIEW.getWaterHeaterView().getSmall().setBackground(Color.red);
                
                if(this.medium)
                    WaterHeater_VIEW.getWaterHeaterView().getMedium().setBackground(Color.green); 
                else
                    WaterHeater_VIEW.getWaterHeaterView().getMedium().setBackground(Color.red); 
                
                if(this.large)
                    WaterHeater_VIEW.getWaterHeaterView().getLarge().setBackground(Color.green);
                else
                    WaterHeater_VIEW.getWaterHeaterView().getLarge().setBackground(Color.red);
                    
                System.out.println("Mocha" + mochabutton);
                  
                /*
                WaterHeater_VIEW.getWaterHeaterView().getAmericano().setEnabled(this.americanobutton);
                WaterHeater_VIEW.getWaterHeaterView().getCappuccino().setEnabled(this.cappuccinobutton);
                WaterHeater_VIEW.getWaterHeaterView().getEspresso().setEnabled(this.espressobutton);
                WaterHeater_VIEW.getWaterHeaterView().getLatte().setEnabled(this.lattebutton);
                WaterHeater_VIEW.getWaterHeaterView().getMocha().setEnabled(this.mochabutton);
                WaterHeater_VIEW.getWaterHeaterView().getMacchiato().setEnabled(this.machiattobutton);
*/
//                WaterHeater_VIEW.getWaterHeaterView().setDrink(Drink.getDrinks().get(0), mochabutton);
//                WaterHeater_VIEW.getWaterHeaterView().setDrink(Drink.getDrinks().get(1), espressobutton);
//                WaterHeater_VIEW.getWaterHeaterView().setDrink(Drink.getDrinks().get(2), americanobutton);
//                WaterHeater_VIEW.getWaterHeaterView().setDrink(Drink.getDrinks().get(3), machiattobutton);
//                WaterHeater_VIEW.getWaterHeaterView().setDrink(Drink.getDrinks().get(4), lattebutton );
//                WaterHeater_VIEW.getWaterHeaterView().setDrink(Drink.getDrinks().get(5), cappuccinobutton);
          
            } else {
                WaterHeater_VIEW.getWaterHeaterView().getAmericano().setBackground(Color.lightGray);
                WaterHeater_VIEW.getWaterHeaterView().getCappuccino().setBackground(Color.lightGray);
                WaterHeater_VIEW.getWaterHeaterView().getEspresso().setBackground(Color.lightGray);
                WaterHeater_VIEW.getWaterHeaterView().getLatte().setBackground(Color.lightGray);
                WaterHeater_VIEW.getWaterHeaterView().getMacchiato().setBackground(Color.lightGray); 
                WaterHeater_VIEW.getWaterHeaterView().getMocha().setBackground(Color.lightGray);
                WaterHeater_VIEW.getWaterHeaterView().getSmall().setBackground(Color.lightGray);
                WaterHeater_VIEW.getWaterHeaterView().getMedium().setBackground(Color.lightGray);
                WaterHeater_VIEW.getWaterHeaterView().getLarge().setBackground(Color.lightGray);
            }
        } //To change body of generated methods, choose Tools | Templates.
    }

}
