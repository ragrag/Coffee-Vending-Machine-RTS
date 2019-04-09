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
public class WaterTank {

    private double tempreture;

    public WaterTank(double tempreture) {
        this.tempreture = tempreture;

    }

    public double getTempreture() {
        return tempreture;
    }

    void raiseTempreture() {

        tempreture += 7.8;
    }

    public void idle() {
        tempreture = tempreture - 5 < 0 ? 0 : tempreture - 5;
    }

}
