/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


/**
 *
 * @author 
 */
public class WaterTank {

    private double temperature;
    private int quantity;
    private static  WaterTank watertank;
    
    public static WaterTank getInstance(){
        if(watertank!= null)
            return watertank;
        else
            return watertank= new WaterTank();
    }

    private WaterTank() {
        this.temperature = 0;
        this.quantity = 200;
    }

    public double getTempreture() {
        return temperature;
    }

    void raiseTempreture() {
        temperature += 7.8;
    }

    public void idle() {
        temperature = temperature - 5 < 0 ? 0 : temperature - 5;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public boolean releaseWater(int water){
        this.quantity-=water;
        return true;
    }
}
