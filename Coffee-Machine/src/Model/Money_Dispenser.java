/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import backend.event.engine.Engine;
import system.events.InsertMoney_EVENT;
import system.views.CoffeeMachineGUI;
import java.io.*;
import sun.audio.*;

/**
 *
 * @author 
 */
public class Money_Dispenser {

    private static Money_Dispenser moneyDispenser = null;
    private InputStream input;
    private AudioStream audio;
    
    private Money_Dispenser() {
    }

    public static Money_Dispenser getMoneyDispenser() {
        if (moneyDispenser != null) {
            return moneyDispenser;
        } else {
            moneyDispenser = new Money_Dispenser();
            return moneyDispenser;
        }
    }

    public void swallow() {
        if ("".equals(CoffeeMachineGUI.getCoffeeMachineGUI().getMoneyEntered().getText())) {
            System.out.println("Please Enter Money First");
            Screen.getScreen().display("Please Enter Money First");
        } else {
            Engine.sendEvent(new InsertMoney_EVENT(Integer.parseInt(CoffeeMachineGUI.getCoffeeMachineGUI().getMoneyEntered().getText()), true));
            CoffeeMachineGUI.getCoffeeMachineGUI().getMoneyEntered().setText("");
            
            try {
            //open the sound file as a Java input stream
            input = new FileInputStream("D:\\projects\\Coffee-Vending-Machine-RTS\\Coffee-Machine\\src\\Sounds\\Money_Swallow.wav");
            
            //create an audiostream from the inputstream
            audio = new AudioStream(input);
            
            //play the audio clip of swallowing money
            AudioPlayer.player.start(audio);
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot find the sound effect");
            } catch (IOException ex) {
            System.out.println("Cannot find the sound effect");
            }   
        }
    }
    
    public void dispenseMoney() {
        try {
            Thread.sleep(2000);
            //open the sound file as a Java input stream
            input = new FileInputStream("D:\\projects\\Coffee-Vending-Machine-RTS\\Coffee-Machine\\src\\Sounds\\Beeping.wav");
            
            //create an audiostream from the inputstream
            audio = new AudioStream(input);
            
            //play the audio clip of swallowing money
            AudioPlayer.player.start(audio);
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot find the sound effect");
            } catch (IOException ex) {
            System.out.println("Cannot find the sound effect");
            } catch (InterruptedException ex) { 
            } 
    }
    
    public void dispenseCoins(int change) {
        if(change!=0)
        {try {
            Thread.sleep(2000);
            //open the sound file as a Java input stream
            input = new FileInputStream("D:\\projects\\Coffee-Vending-Machine-RTS\\Coffee-Machine\\src\\Sounds\\Coin_Dispensing.wav");
            
            //create an audiostream from the inputstream
            audio = new AudioStream(input);
            
            //play the audio clip of swallowing money
            AudioPlayer.player.start(audio);
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot find the sound effect");
            } catch (IOException ex) {
            System.out.println("Cannot find the sound effect");
            } catch (InterruptedException ex) { 
            } 
        }
    }
}
