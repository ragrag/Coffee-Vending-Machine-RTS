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
import javax.swing.ImageIcon;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import system.views.PouringGUI;

/**
 *
 * @author 
 */
public class Mixer {

    private static Mixer Mixer = null;
    private InputStream input;
    private AudioStream audio;

    public Mixer() {
    }
    
    
    public static Mixer getInstance() {
        if (Mixer != null) {
            return Mixer;
        } else {
            return Mixer = new Mixer();
        }
    }

    public void mix() {
        Screen.getScreen().display("Wait for your drink");
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
        }
        this.pour();
        
    }

    public void pour() {
        Screen.getScreen().display("Wait for your drink");
        Thread pour = new Thread(){
        public void run(){
        for (int i = 0; i<=2;i++)
        {
            try {
                sleep((i+1)*1000);
                //open the sound file as a Java input stream
                input = new FileInputStream("D:\\projects\\Coffee-Vending-Machine-RTS\\Coffee-Machine\\src\\Sounds\\Pouring.wav");
                //create an audiostream from the inputstream
                audio = new AudioStream(input);
                //play the audio clip of swallowing money
                AudioPlayer.player.start(audio);
            }catch (FileNotFoundException ex) {
                System.out.println("Cannot find the sound effect");
            }catch (IOException ex) {
                System.out.println("Cannot find the sound effect");
            }catch (InterruptedException ex) {
            }
        }
        
        }};
        Thread slider = new Thread(){
        @Override
        public void run(){
            for(int i=0;i<=100;i+=5){
                PouringGUI.getInstance().getDrink().setValue(i);
                try {
                    sleep(380);
                } catch (InterruptedException ex) {
                }
            }
        }};
        pour.start();
        slider.start();
        try {
            pour.join();
            slider.join();
        } catch (InterruptedException ex) {
        }
        try{
                //open the sound file as a Java input stream
                input = new FileInputStream("D:\\projects\\Coffee-Vending-Machine-RTS\\Coffee-Machine\\src\\Sounds\\Beeping.wav");
                //create an audiostream from the inputstream
                audio = new AudioStream(input);
                //play the audio clip of swallowing money
                AudioPlayer.player.start(audio);
            }catch (FileNotFoundException ex) {
                System.out.println("Cannot find the sound effect");
            }catch (IOException ex) {
                System.out.println("Cannot find the sound effect");
            }
    }

    public void dispatchIngredients(int sugar, int size, Drink drink) {
        if(InventoryHandler.getInsatance().releaseIngredients(drink.getCoffee(), drink.getChocolate(), drink.getMilk(), sugar, size)) {
            switch (size) {
                case 2:
                    PouringGUI.getInstance().getCupSize().setIcon(new ImageIcon("D:\\projects\\Coffee-Vending-Machine-RTS\\Coffee-Machine\\src\\system\\views\\medium.png"));
                    break;
                case 3:
                    PouringGUI.getInstance().getCupSize().setIcon(new ImageIcon("D:\\projects\\Coffee-Vending-Machine-RTS\\Coffee-Machine\\src\\system\\views\\large.png"));
                    break;
                default:
                    PouringGUI.getInstance().getCupSize().setIcon(new ImageIcon("D:\\projects\\Coffee-Vending-Machine-RTS\\Coffee-Machine\\src\\system\\views\\small.png"));
                    break;
            }
            mix();
            System.out.println("Ingredients Released");
        } else {
            System.out.println("ERROR WE TOOK YOUR MONEY");
        }
    }

}
