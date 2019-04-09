/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.events;

/**
 *
 * @author mohamed
 */
public class PowerEvent {

    boolean powered;

    public PowerEvent(boolean powered) {
        this.powered = powered;
    }

    public boolean getPowered() {
        return powered;
    }

}
