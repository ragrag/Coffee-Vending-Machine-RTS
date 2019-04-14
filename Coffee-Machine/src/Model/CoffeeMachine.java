/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import backend.event.engine.Engine;
import system.views.WaterHeater_VIEW;

/**
 *
 * @author mohamed
 */
public class CoffeeMachine {

    private static CoffeeMachine coffeeMachine = null;;
    private boolean powered;

    private CoffeeMachine() {
        
        this.powered = true;
        SelectionPanel.getInstance();
        WaterHeater.getInstance();
        MoneyHandler.getMoneyHandler();
        InventoryHandler.getInsatance();
        TransactionProcessor.getInstance();
        Mixer.getInstance();
        WaterHeater_VIEW.getWaterHeaterView().setPower(powered);
        
        
        
        
        Engine.createStatement("select powered from Power_EVENT")
                .setSubscriber(new Object() {
                    public void update(boolean power) throws InterruptedException {
                        System.out.println("Machine Power state changed ");
                        switchPower();
                        handleSystem();
                        WaterHeater_VIEW.getWaterHeaterView().setPower(powered);

                    }
                });
    }

    public void handleSystem(){
        if(!powered)
            disableBottuns();
        else
            enableBottuns();
    }
    
    
    public void disableBottuns(){
        WaterHeater_VIEW.getWaterHeaterView().getAmericano().setEnabled(false);
        WaterHeater_VIEW.getWaterHeaterView().getCappuccino().setEnabled(false);
        WaterHeater_VIEW.getWaterHeaterView().getEspresso().setEnabled(false);
        WaterHeater_VIEW.getWaterHeaterView().getMocha().setEnabled(false);
        WaterHeater_VIEW.getWaterHeaterView().getLatte().setEnabled(false);
        WaterHeater_VIEW.getWaterHeaterView().getMacchiato().setEnabled(false);
        WaterHeater_VIEW.getWaterHeaterView().getSugar().setEnabled(false);
        WaterHeater_VIEW.getWaterHeaterView().getSmall().setEnabled(false);
        WaterHeater_VIEW.getWaterHeaterView().getMedium().setEnabled(false);
        WaterHeater_VIEW.getWaterHeaterView().getLarge().setEnabled(false);
        WaterHeater_VIEW.getWaterHeaterView().getStart().setEnabled(false);
        WaterHeater_VIEW.getWaterHeaterView().getCancel().setEnabled(false);
        WaterHeater_VIEW.getWaterHeaterView().getInsertMoney().setEnabled(false);
        SelectionPanel.getInstance().stopBlinking();
    }
    
    public void enableBottuns(){
        WaterHeater_VIEW.getWaterHeaterView().getAmericano().setEnabled(true);
        WaterHeater_VIEW.getWaterHeaterView().getCappuccino().setEnabled(true);
        WaterHeater_VIEW.getWaterHeaterView().getEspresso().setEnabled(true);
        WaterHeater_VIEW.getWaterHeaterView().getMocha().setEnabled(true);
        WaterHeater_VIEW.getWaterHeaterView().getLatte().setEnabled(true);
        WaterHeater_VIEW.getWaterHeaterView().getMacchiato().setEnabled(true);
        WaterHeater_VIEW.getWaterHeaterView().getSugar().setEnabled(true);
        WaterHeater_VIEW.getWaterHeaterView().getSmall().setEnabled(true);
        WaterHeater_VIEW.getWaterHeaterView().getMedium().setEnabled(true);
        WaterHeater_VIEW.getWaterHeaterView().getLarge().setEnabled(true);
        WaterHeater_VIEW.getWaterHeaterView().getStart().setEnabled(true);
        WaterHeater_VIEW.getWaterHeaterView().getCancel().setEnabled(true);
        WaterHeater_VIEW.getWaterHeaterView().getInsertMoney().setEnabled(true);
    }
    public boolean getPowered() {
        return powered;
    }

    public void switchPower() {
        powered = !powered;
    }

    public static CoffeeMachine getInstance() {
        if(coffeeMachine != null)
            return coffeeMachine;
        else
            return coffeeMachine = new  CoffeeMachine();
    }

}
