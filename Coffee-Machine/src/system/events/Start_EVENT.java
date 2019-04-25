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
public class Start_EVENT {
    Boolean startOrder;

    public Start_EVENT(Boolean startOrder) {
        this.startOrder = startOrder;
    }

    public Boolean getStartOrder() {
        return startOrder;
    }
}
