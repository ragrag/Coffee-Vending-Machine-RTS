/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.event.engine;

import Model.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import system.views.MachineStatsGUI;
import system.views.CoffeeMachineGUI;
import system.views.PouringGUI;

/**
 *
 * @author 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Logger.getRootLogger().setLevel(Level.OFF);

        // Register events
        Engine.registerEvents();
        CoffeeMachineGUI.getCoffeeMachineGUI();
        MachineStatsGUI.getInstance();
        PouringGUI.getInstance();
        CoffeeMachine.getInstance();
    }

}
