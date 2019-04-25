/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import system.views.CoffeeMachineGUI;

/**
 *
 * @author 
 */
public class Screen {
    
    private static Screen screen=null;
    private Screen(){
        display("Hello! :)");
    }
    
    public static Screen getScreen(){
        if(screen!=null)
            return screen;
        else
        {
            screen=new Screen();
            return screen;
        }
    }
    
    public void display(String msg)
    {
        CoffeeMachineGUI.getCoffeeMachineGUI().getScreen().setText(msg);
    }
    
}
