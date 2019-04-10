/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Mostafa
 */
public class InventoryHandler {

    private static InventoryHandler inventoryhandler;
    
    private InventoryHandler() {
        IngredientsSensor.getInstance();
        InventoryStorage.getInstance();
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
     public void Recivequantity(int wateramount,int coffeamount,int sugaramount, int chocolateamount,int milkquantity,int smallamount,int mediumamount,int largeamount){
    //System.out.println("AAASSSSSSSSSSSSSSSSSSBBBBBBBBBBBB" );
         ArrayList<Drink> allDrinks = Drink.getDrinks();
         //System.out.println("AAASSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSss" );
         boolean mocha = false,espresso = false,mochaccino = false, machiatto = false ,cappuccino = false,latte = false,sugar=false,small=false,medium=false,large=false;
         for (Drink v : allDrinks)
         {
             
             
             
            switch(v.name){
                    case "mocha":
                        if(checkValidDrink(v ,coffeamount,chocolateamount,milkquantity))
                        mocha = true;
                    case "espresso":
                        if(checkValidDrink(v ,coffeamount,chocolateamount,milkquantity))
                        espresso = true;;
                    case "mochaccino":
                        if(checkValidDrink(v ,coffeamount,chocolateamount,milkquantity))
                        mochaccino = true;
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
         SelectionPanel.getInsatance().ActivateButtons(mocha, espresso, cappuccino, machiatto, mochaccino, latte,sugar,small,medium,large);
     
     }
}
