/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.events;

/**
 *
 * @author glori
 */
public class InsertMoney_EVENT {
    private int value;
    private boolean condition;

    public InsertMoney_EVENT(int value, boolean condition) {
        this.value = value;
        this.condition = condition;
    }

    public int getValue() {
        return value;
    }

    public boolean getCondition() {
        return condition;
    }
    
    
}
