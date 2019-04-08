/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author mohamed
 */
public class WaterContainer {
    private double tempreture;

    
    public WaterContainer(double tempreture)
    {
        this.tempreture = tempreture;
    }
    
    public double getTempreture() {
        return tempreture;
    }
    
    
       
    void raiseTempreture()
    {
      
            tempreture +=7.8;
    }

    public void idle() {
        
           tempreture -=5;
           
           
        
    }  
    
    
}
