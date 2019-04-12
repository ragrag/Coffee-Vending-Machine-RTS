/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.event.engine;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import system.events.*;

/**
 *
 * @author mohamed
 */
public class Engine {

    private static EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();

    public static void registerEvents() {
        //engine.getEPAdministrator().getConfiguration().addEventType(PowerEvent.class);
        engine.getEPAdministrator().getConfiguration().addEventType(WaterHeater_Sensor_READING.class);
        engine.getEPAdministrator().getConfiguration().addEventType(Power_EVENT.class);
        engine.getEPAdministrator().getConfiguration().addEventType(InsertMoney_EVENT.class);
        engine.getEPAdministrator().getConfiguration().addEventType(Inventory_Sensor_READING.class);
        engine.getEPAdministrator().getConfiguration().addEventType(SelectDrink_EVENT.class);
        engine.getEPAdministrator().getConfiguration().addEventType(SelectSize_EVENT.class);
        engine.getEPAdministrator().getConfiguration().addEventType(SelectSuger_EVENT.class);
        engine.getEPAdministrator().getConfiguration().addEventType(Start_EVENT.class);
        engine.getEPAdministrator().getConfiguration().addEventType(Cancel_EVENT.class);
        System.out.println("Events Successfully Registered.");
    }

    public static EPStatement createStatement(String s) {
        EPStatement result = engine.getEPAdministrator().createEPL(s);
        System.out.println("EPL Statement Successfully Created.");
        return result;
    }

    public static void sendEvent(Object o) {
        engine.getEPRuntime().sendEvent(o);
    }

}
