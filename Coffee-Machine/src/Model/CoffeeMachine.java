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

    private static final CoffeeMachine waterHeaterView = new CoffeeMachine();
    private boolean powered;

    private CoffeeMachine() {
        this.powered = true;
        WaterHeater_VIEW.getWaterHeaterView().setPower(powered);
        Engine.createStatement("select powered from PowerEvent")
                .setSubscriber(new Object() {
                    public void update(boolean power) throws InterruptedException {
                        System.out.println("Machine Power state changed ");
                        switchPower();
                        WaterHeater_VIEW.getWaterHeaterView().setPower(powered);

                    }
                });
    }

    public boolean getPowered() {
        return powered;
    }

    public void switchPower() {
        powered = !powered;
    }

    public static CoffeeMachine getCoffeeMachine() {
        return waterHeaterView;
    }

}
