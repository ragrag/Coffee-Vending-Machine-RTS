/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import backend.event.engine.Engine;
import static java.lang.Thread.sleep;
import system.events.Inventory_Sensor_READING;
import system.views.MachineStatsGUI;

/**
 *
 * @author 
 */
public class IngredientsSensor extends Thread{

    private static IngredientsSensor ingredientsSensor;
    
    private IngredientsSensor() {
                Engine.createStatement("select sugar, milk, coffee, water, chocolate, smallCups, mediumCups, largeCups from Inventory_Sensor_READING")
                .setSubscriber(new Object() {
                    public void update(int sugar, int milk, int coffee, int water, int chocolate, int small,int medium,int large) throws InterruptedException {
                        
                        System.out.println("Sugar amount: " + sugar);
                        System.out.println("Milk amount: " + milk);
                        System.out.println("Coffee amount: " + coffee);
                        System.out.println("Chocolate amount: " + chocolate);
                        System.out.println("Water amount: " + water);
                        System.out.println("Small cups amount: " + small);
                        System.out.println("Medium cups amount: " + medium);
                        System.out.println("Large cups amount: " + large);
                        
                        InventoryHandler.getInsatance().Recivequantity(water,coffee,sugar,chocolate,milk,small,medium,large);
                        
                        MachineStatsGUI.getInstance().getSugerSlider().setValue(sugar);
                        MachineStatsGUI.getInstance().getWaterSlider().setValue(water);
                        MachineStatsGUI.getInstance().getLargeSlider().setValue(large);
                        MachineStatsGUI.getInstance().getMediumSlider().setValue(medium);
                        MachineStatsGUI.getInstance().getSmallSlider().setValue(small);
                        MachineStatsGUI.getInstance().getMilkSlider().setValue(milk);
                        MachineStatsGUI.getInstance().getChoclateSlider().setValue(chocolate);
                        MachineStatsGUI.getInstance().getCoffeeSlider().setValue(coffee);
                    
                    }
                });
        this.start();
    }

    public static IngredientsSensor getInstance(){
        if(ingredientsSensor!=null)
            return ingredientsSensor;
        else{
            ingredientsSensor=new IngredientsSensor();
            return ingredientsSensor;
        }
    }
    @Override
    public void run() {
          while (true) {

            try {
                sleep(1000);
            } catch (InterruptedException ex) {
            }
            if (CoffeeMachine.getInstance().getPowered()) {
                int sugarQuantity = InventoryStorage.getInstance().getSugar();
                int milkQuantity = InventoryStorage.getInstance().getMilk();
                int choccolateQuantity = InventoryStorage.getInstance().getChocolate();
                int coffeeQuantity = InventoryStorage.getInstance().getCoffee();
                int waterQuantity = InventoryStorage.getInstance().getWater();
                int smallCupsQuantity = InventoryStorage.getInstance().getSmall();
                int mediumCupsQuantity = InventoryStorage.getInstance().getMedium();
                int largeCupsQuantity = InventoryStorage.getInstance().getLarge();
                
                Engine.sendEvent(new Inventory_Sensor_READING(sugarQuantity, milkQuantity, coffeeQuantity, waterQuantity, choccolateQuantity,smallCupsQuantity,mediumCupsQuantity,largeCupsQuantity));
            } 
        } 
    }
    
    
    
}
