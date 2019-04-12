/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ragrag
 */
public class Mixer {

    private static Mixer Mixer = null;

    public static Mixer getInstance() {
        if (Mixer != null) {
            return Mixer;
        } else {
            return Mixer = new Mixer();
        }
    }

    public void mix() {
        System.out.println("Mixing your drink");
        try {
            sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Mixer.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.pour();
        
    }

    public void pour() {
        System.out.println("POURING DRINK!!!!!");
       
        for (int i = 0; i<=5;i++)
        {
        try {
            sleep((i+1)*1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Mixer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("LEVEL "+i+1);
        }
    }

    public void dispatchIngredients(int sugar, int size, Drink drink) {
System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

         if (InventoryHandler.getInsatance().releaseIngredients(drink.getCoffee(), drink.getChocolate(), drink.getMilk(), sugar, size)) {
                    mix();
                    System.out.println("Ingredients Released");
                } else {
                    System.out.println("ERROR WE TOOK YOUR MONEY");
                }
    }

}
