/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.events;

/**
 *
 * @author 
 */
public class SelectSugar_EVENT {
    int sugarSelection;

    public SelectSugar_EVENT(int sugarSelection) {
        this.sugarSelection = sugarSelection;
    }

    public int getSugarSelection() {
        return sugarSelection;
    }
    
}
