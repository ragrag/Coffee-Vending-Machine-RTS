/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author ragrag
 */
public class Mixer {

    private static Mixer Mixer = null;
    private InputStream moneySwallow;
    private AudioStream moneySwallowSound;
    
    public static Mixer getInstance() {
        if (Mixer != null) {
            return Mixer;
        } else {
            return Mixer = new Mixer();
        }
    }

    public void mix() {
        System.out.println("Mixing your drink");
        Screen.getScreen().display("Wait for your drink");
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            //Logger.getLogger(Mixer.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.pour();
        
    }

    public void pour() {
        System.out.println("POURING DRINK!!!!!");
        Screen.getScreen().display("Wait for your drink");
        for (int i = 0; i<=5;i++)
        {
        try {
            sleep((i+1)*1000);
            
            //open the sound file as a Java input stream
            moneySwallow = new FileInputStream("D:\\projects\\Coffee-Vending-Machine-RTS\\Coffee-Machine\\src\\Sounds\\Pouring.wav");
            
            //create an audiostream from the inputstream
            moneySwallowSound = new AudioStream(moneySwallow);
            
            //play the audio clip of swallowing money
            AudioPlayer.player.start(moneySwallowSound);
            //clip.open();
            //clip.start();
        }catch (FileNotFoundException ex) {
            System.out.println("Cannot find the sound effect");
            } catch (IOException ex) {
            System.out.println("Cannot find the sound effect");
            } 
         catch (InterruptedException ex) {
            Logger.getLogger(Mixer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("LEVEL "+i+1);
        }
    }

    public void dispatchIngredients(int sugar, int size, Drink drink) {
         if (InventoryHandler.getInsatance().releaseIngredients(drink.getCoffee(), drink.getChocolate(), drink.getMilk(), sugar, size)) {
                    mix();
                    System.out.println("Ingredients Released");
                } else {
                    System.out.println("ERROR WE TOOK YOUR MONEY");
                }
    }

}
