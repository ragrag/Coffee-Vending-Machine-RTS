/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author 
 */
public class InventoryHandler {

    private static InventoryHandler inventoryhandler;
    
    private InventoryHandler() {
        InventoryStorage.getInstance();
        IngredientsSensor.getInstance();
    }
    public static InventoryHandler getInsatance(){
        if(inventoryhandler != null){
            return inventoryhandler;
        }
        else{
            return inventoryhandler = new InventoryHandler();
        }
    }
    
    private boolean checkValidDrink(Drink drink ,int coffeamount, int choccolateamount,int milkquantity )
    {
        if ( coffeamount >= drink.getCoffee() && choccolateamount >= drink.getChocolate() &&  milkquantity >= drink.getMilk()  )
            return true;
        return false;
    }
    
    public int getWaterQuantity(){
        return WaterTank.getInstance().getQuantity();
    }
    
    public void setWaterQuantity(int water){
        WaterTank.getInstance().setQuantity(water);
    }
    
    public boolean releaseIngredients(int coffee,int chocolate,int milk,int sugar, int size){
        return (WaterTank.getInstance().releaseWater(200 + ((size-1)*100))&& InventoryStorage.getInstance().releaseIngredients(sugar, milk, coffee, chocolate, size));
    }
    
    public void Recivequantity(int wateramount,int coffeamount,int sugaramount, int chocolateamount,int milkquantity,int smallamount,int mediumamount,int largeamount){
        ArrayList<Drink> allDrinks = Drink.getDrinks();
        boolean mocha = false,espresso = false,americano = false, machiatto = false ,cappuccino = false,latte = false,sugar=false,small=false,medium=false,large=false;
        for (Drink v : allDrinks)
        {
            switch(v.name){
                    case "mocha":
                        if(checkValidDrink(v ,coffeamount,chocolateamount,milkquantity))
                        mocha = true;
                    case "espresso":
                        if(checkValidDrink(v ,coffeamount,chocolateamount,milkquantity))
                        espresso = true;;
                    case "americano":
                        if(checkValidDrink(v ,coffeamount,chocolateamount,milkquantity))
                        americano = true;
                    case "machiatto":
                        if(checkValidDrink(v ,coffeamount,chocolateamount,milkquantity))
                        machiatto = true;
                    case "latte":
                        if(checkValidDrink(v ,coffeamount,chocolateamount,milkquantity))
                        latte = true;
                    case "cappuccino":
                        if(checkValidDrink(v ,coffeamount,chocolateamount,milkquantity))
                        cappuccino = true;
            }
        }
        if(sugaramount>=5)
            sugar=true;
        if(smallamount>=1)
            small=true;
        if(mediumamount>=1)
            medium=true;
        if(largeamount>=1)
            large=true;
         
        SelectionPanel.getInstance().ActivateButtons(mocha, espresso, cappuccino, machiatto, americano, latte,sugar,small,medium,large);
    }
}
