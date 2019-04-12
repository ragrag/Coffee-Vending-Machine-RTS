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
public class Drink {
   
    private static ArrayList<Drink> allDrinks = new ArrayList<Drink>();
    int price;
    String name;
    int coffee;
    int milk;
    int chocolate;
    private Drink(String name, int coffee,int milk , int chocolate,int price){
        this.name = name;
        this.coffee = coffee;
        this.milk = milk;
        this.chocolate=  chocolate;
        this.price=price;
        
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
    
    public int getCoffee() {
        return coffee;
    }

    public int getMilk() {
        return milk;
    }

    public int getChocolate() {
        return chocolate;
    }
    public String getName(){
        return name;
    }
    
    private static  void initializeDrinks(){
        Drink espresso = new Drink("espresso",2,4,5,5);
        Drink mocha = new Drink("mocha",2,1,1,10);
        Drink latte = new Drink("latte",2,1,1,15);
        Drink machiatto = new Drink("machiatto",2,1,1,25);
        Drink americano = new Drink("americano",2,1,1,10);
        Drink cappuccino = new Drink("cappuccino",2,1,1,15);
        
        allDrinks.add(mocha);
        allDrinks.add(espresso);
        allDrinks.add(americano);
        allDrinks.add(machiatto);
        allDrinks.add(latte);
        allDrinks.add(cappuccino);
    }
    public static  ArrayList<Drink> getDrinks()
    {
        System.err.println("SSSSSSSSSSS");
        if (allDrinks.size() > 0)
            return allDrinks;
        else {
          
            initializeDrinks();
            
            return allDrinks;
            
        }
    }
   
            
}
