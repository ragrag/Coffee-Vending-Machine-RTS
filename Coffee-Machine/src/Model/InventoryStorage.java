/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Mostafa
 */
public class InventoryStorage {
    private int sugar;
    private int milk;
    private int coffee;
    private int water;
    private int chocolate;
    private int small,medium,large;
    private static InventoryStorage inventorystorage;

    private InventoryStorage() {
        this.sugar = 20;
        this.milk = 20;
        this.coffee = 20;
        this.water = WaterTank.getInstance().getQuantity();
        this.chocolate = 20;
        this.small = 30;
        this.medium = 30;
        this.large = 30;
    }
public static InventoryStorage getInstance(){

    if(inventorystorage != null){
        return inventorystorage;
    }
    else {
        inventorystorage = new InventoryStorage();
        return inventorystorage;
    
    }
}

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getCoffee() {
        return coffee;
    }

    public void setCoffee(int coffee) {
        this.coffee = coffee;
    }

    public int getWater() {
        return water=  WaterTank.getInstance().getQuantity();
    }

    public void setWater(int water) {
        WaterTank.getInstance().setQuantity(water);
        getWater();
    }

    public int getChocolate() {
        return chocolate;
    }

    public void setChocolate(int chocolate) {
        this.chocolate = chocolate;
    }

    public static InventoryStorage getInventorystorage() {
        return inventorystorage;
    }

    public static void setInventorystorage(InventoryStorage inventorystorage) {
        InventoryStorage.inventorystorage = inventorystorage;
    }

    public int getSmall() {
        return small;
    }

    public void setSmall(int small) {
        this.small = small;
    }

    public int getMedium() {
        return medium;
    }

    public void setMedium(int medium) {
        this.medium = medium;
    }

    public int getLarge() {
        return large;
    }

    public void setLarge(int large) {
        this.large = large;
    }
 
    
}
