/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.events;

/**
 *
 * @author Mostafa
 */
public class Inventory_Sensor_READING {
    
    int sugar;
    int milk;
    int coffee;
    int water;
    int chocolate;
    int smallCups,mediumCups,largeCups;

    public Inventory_Sensor_READING(int sugar, int milk, int coffee, int water, int chocolate,int smallCups,int mediumCups,int largeCups) {
        this.sugar = sugar;
        this.milk = milk;
        this.coffee = coffee;
        this.water = water;
        this.chocolate = chocolate;
        this.smallCups = smallCups;
        this.mediumCups = mediumCups;
        this.largeCups = largeCups;
    }

   

    public int getSugar() {
        return sugar;
    }

    public int getMilk() {
        return milk;
    }

    public int getCoffee() {
        return coffee;
    }

    public int getWater() {
        return water;
    }

    public int getChocolate() {
        return chocolate;
    }

    public int getSmallCups() {
        return smallCups;
    }

    public int getMediumCups() {
        return mediumCups;
    }

    public int getLargeCups() {
        return largeCups;
    }
    
    
    
}
