/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import backend.event.engine.Engine;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.views.CoffeeMachineGUI;

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
        Screen.getScreen();
        WaterHeater.getInstance();
        MoneyHandler.getMoneyHandler();
        InventoryHandler.getInsatance();
        TransactionProcessor.getInstance();
        Mixer.getInstance();
        CoffeeMachineGUI.getCoffeeMachineGUI().setPower(powered);
        
        
        
        
        Engine.createStatement("select powered from Power_EVENT")
                .setSubscriber(new Object() {
                    public void update(boolean power) throws InterruptedException {
                        System.out.println("Machine Power state changed ");
                        switchPower();
                        handleSystem();
                        CoffeeMachineGUI.getCoffeeMachineGUI().setPower(powered);
                        
                    }
                });
    }

    public void handleSystem(){
        if(!powered){
            Screen.getScreen().display("Thank you\nGoodbye :)");
            Thread goodbye = new Thread(){
            public void run()
            {
                try {
                Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
                Screen.getScreen().display(" ");
                disableBottuns();  
            }};
           goodbye.start();
        }
        
        else{
            
            enableBottuns();
            Screen.getScreen().display("Hello! Please select your drink :)");
        }
    }
    
    
    public void disableBottuns(){
        CoffeeMachineGUI.getCoffeeMachineGUI().getAmericano().setEnabled(false);
        CoffeeMachineGUI.getCoffeeMachineGUI().getCappuccino().setEnabled(false);
        CoffeeMachineGUI.getCoffeeMachineGUI().getEspresso().setEnabled(false);
        CoffeeMachineGUI.getCoffeeMachineGUI().getMocha().setEnabled(false);
        CoffeeMachineGUI.getCoffeeMachineGUI().getLatte().setEnabled(false);
        CoffeeMachineGUI.getCoffeeMachineGUI().getMacchiato().setEnabled(false);
        CoffeeMachineGUI.getCoffeeMachineGUI().getSugar().setEnabled(false);
        CoffeeMachineGUI.getCoffeeMachineGUI().getSmall().setEnabled(false);
        CoffeeMachineGUI.getCoffeeMachineGUI().getMedium().setEnabled(false);
        CoffeeMachineGUI.getCoffeeMachineGUI().getLarge().setEnabled(false);
        CoffeeMachineGUI.getCoffeeMachineGUI().getStart().setEnabled(false);
        CoffeeMachineGUI.getCoffeeMachineGUI().getCancel().setEnabled(false);
        CoffeeMachineGUI.getCoffeeMachineGUI().getInsertMoney().setEnabled(false);
        SelectionPanel.getInstance().stopBlinking();
    }
    
    public void enableBottuns(){
        CoffeeMachineGUI.getCoffeeMachineGUI().getAmericano().setEnabled(true);
        CoffeeMachineGUI.getCoffeeMachineGUI().getCappuccino().setEnabled(true);
        CoffeeMachineGUI.getCoffeeMachineGUI().getEspresso().setEnabled(true);
        CoffeeMachineGUI.getCoffeeMachineGUI().getMocha().setEnabled(true);
        CoffeeMachineGUI.getCoffeeMachineGUI().getLatte().setEnabled(true);
        CoffeeMachineGUI.getCoffeeMachineGUI().getMacchiato().setEnabled(true);
        CoffeeMachineGUI.getCoffeeMachineGUI().getSugar().setEnabled(true);
        CoffeeMachineGUI.getCoffeeMachineGUI().getSmall().setEnabled(true);
        CoffeeMachineGUI.getCoffeeMachineGUI().getMedium().setEnabled(true);
        CoffeeMachineGUI.getCoffeeMachineGUI().getLarge().setEnabled(true);
        CoffeeMachineGUI.getCoffeeMachineGUI().getStart().setEnabled(true);
        CoffeeMachineGUI.getCoffeeMachineGUI().getCancel().setEnabled(true);
        CoffeeMachineGUI.getCoffeeMachineGUI().getInsertMoney().setEnabled(true);
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
