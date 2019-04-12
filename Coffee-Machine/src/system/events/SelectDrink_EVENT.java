/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.events;

import Model.Drink;

/**
 *
 * @author ragrag
 */
public class SelectDrink_EVENT {
    Drink drink;

    public SelectDrink_EVENT(Drink drink) {
        this.drink = drink;
    }

    public Drink getDrink() {
        return drink;
    }
    
    
}
