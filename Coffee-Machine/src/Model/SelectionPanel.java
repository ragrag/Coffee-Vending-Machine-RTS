/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import backend.event.engine.Engine;
import java.awt.Color;
import static java.awt.PageAttributes.ColorType.COLOR;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.events.WaterHeater_Sensor_READING;
import system.views.CoffeeMachineGUI;

/**
 *
 * @author Mostafa
 */
public class SelectionPanel extends Thread {

    private static SelectionPanel selectionpanel;

    Boolean mochabutton = false;
    Boolean espressobutton = false;
    Boolean americanobutton = false;
    Boolean machiattobutton = false;
    Boolean cappuccinobutton = false;
    Boolean lattebutton = false;
    Boolean sugar = false;
    Boolean small = false;
    Boolean medium = false;
    Boolean large = false;
    Thread blinkingThread;
    Thread sizeBlinkingThread;

    public SelectionPanel() {
        blinkingThread = null;
        Engine.createStatement("select drink from SelectDrink_EVENT")
                .setSubscriber(new Object() {
                    public void update(Drink d) throws InterruptedException {
                        System.out.println("drink: " + d.getName());
                        if (blinkingThread != null) {
                            blinkingThread.stop();
                        }
                        drinkBlinking(d);
                        TransactionProcessor.getInstance().setDrink(d);
                        TransactionProcessor.getInstance().calculatePrice();
                        Screen.getScreen().display("The balance equal "+TransactionProcessor.getInstance().getBalance()+"\nOrder Price "+ TransactionProcessor.getInstance().getOrderPrice());
                    }
                });

        Engine.createStatement("select sugerSelection from SelectSuger_EVENT")
                .setSubscriber(new Object() {
                    public void update(int suger) throws InterruptedException {
                        System.out.println("suger: " + suger);
                        TransactionProcessor.getInstance().setSuger(suger);
                    }
                });

        Engine.createStatement("select size from SelectSize_EVENT")
                .setSubscriber(new Object() {
                    public void update(int size) throws InterruptedException {
                        System.out.println("size: " + size);
                        if (sizeBlinkingThread != null) {
                            sizeBlinkingThread.stop();
                        }
                        sizeBlinking(size);
                        TransactionProcessor.getInstance().setSize(size);
                        TransactionProcessor.getInstance().calculatePrice();
                        Screen.getScreen().display("The balance equal "+TransactionProcessor.getInstance().getBalance()+"\nOrder Price: "+ TransactionProcessor.getInstance().getOrderPrice());
                    }
                });

        Engine.createStatement("select startOrder from Start_EVENT")
                .setSubscriber(new Object() {
                    public void update(Boolean startOrder) throws InterruptedException {
                        stopBlinking();
                        System.out.println("startOrder: " + startOrder);
                        TransactionProcessor.getInstance().startOrder();
                    }
                });
        Engine.createStatement("select cancel from Cancel_EVENT")
                .setSubscriber(new Object() {
                    public void update(Boolean cancel) throws InterruptedException {
                        stopBlinking();
                        System.out.println("cancel: " + cancel);
                        TransactionProcessor.getInstance().cancel();
                    }
                });

        this.start();
    }

    public void drinkBlinking(Drink d) {
        blinkingThread = new Thread() {
            public void run() {
                while (true) {
                    try {
                        sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SelectionPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if ("espresso".equals(d.getName())) {
                        CoffeeMachineGUI.getCoffeeMachineGUI().getEspresso().setBackground(Color.CYAN);
                    } else if ("mocha".equals(d.getName())) {
                        CoffeeMachineGUI.getCoffeeMachineGUI().getMocha().setBackground(Color.CYAN);
                    } else if ("latte".equals(d.getName())) {
                        CoffeeMachineGUI.getCoffeeMachineGUI().getLatte().setBackground(Color.CYAN);
                    } else if ("machiatto".equals(d.getName())) {
                        CoffeeMachineGUI.getCoffeeMachineGUI().getMacchiato().setBackground(Color.CYAN);
                    } else if ("americano".equals(d.getName())) {
                        CoffeeMachineGUI.getCoffeeMachineGUI().getAmericano().setBackground(Color.CYAN);
                    } else if ("cappuccino".equals(d.getName())) {
                        CoffeeMachineGUI.getCoffeeMachineGUI().getCappuccino().setBackground(Color.CYAN);
                    }
                }
            }
        };
        blinkingThread.start();
    }

    public void stopBlinking() {

        if (sizeBlinkingThread != null) {
            sizeBlinkingThread.stop();
        }
        if (blinkingThread != null) {
            blinkingThread.stop();
        }
    }

    public void sizeBlinking(int size) {
        sizeBlinkingThread = new Thread() {
            public void run() {
                while (true) {
                    try {
                        sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SelectionPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    switch (size) {
                        case 1:
                            CoffeeMachineGUI.getCoffeeMachineGUI().getSmall().setBackground(Color.CYAN);
                            break;
                        case 2:
                            CoffeeMachineGUI.getCoffeeMachineGUI().getMedium().setBackground(Color.CYAN);
                            break;
                        case 3:
                            CoffeeMachineGUI.getCoffeeMachineGUI().getLarge().setBackground(Color.CYAN);
                            break;
                        default:
                            break;
                    }
                }
            }
        };
        sizeBlinkingThread.start();
    }

    public static SelectionPanel getInstance() {

        if (selectionpanel != null) {

            return selectionpanel;
        } else {

            return selectionpanel = new SelectionPanel();
        }
    }

    public void ActivateButtons(Boolean mochabutton, Boolean espressobutton, Boolean americanobutton,
            Boolean machiattobutton, Boolean cappuccinobutton, Boolean lattebutton, Boolean sugar,
            Boolean small, Boolean medium, Boolean large) {

        this.cappuccinobutton = cappuccinobutton;
        this.espressobutton = espressobutton;
        this.lattebutton = lattebutton;
        this.machiattobutton = machiattobutton;
        this.mochabutton = mochabutton;
        this.americanobutton = americanobutton;

        this.medium = medium;
        this.large = large;
        this.small = small;
        this.sugar = sugar;

    }

    @Override
    public void run() {
        while (true) {

            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(WaterHeater_Sensor.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (CoffeeMachine.getInstance().getPowered()) {

                if (this.americanobutton) {
                    CoffeeMachineGUI.getCoffeeMachineGUI().getAmericano().setBackground(Color.green);
                } else {
                    CoffeeMachineGUI.getCoffeeMachineGUI().getAmericano().setBackground(Color.red);
                }

                if (this.cappuccinobutton) {
                    CoffeeMachineGUI.getCoffeeMachineGUI().getCappuccino().setBackground(Color.green);
                } else {
                    CoffeeMachineGUI.getCoffeeMachineGUI().getCappuccino().setBackground(Color.red);
                }

                if (this.espressobutton) {
                    CoffeeMachineGUI.getCoffeeMachineGUI().getEspresso().setBackground(Color.green);
                } else {
                    CoffeeMachineGUI.getCoffeeMachineGUI().getEspresso().setBackground(Color.red);
                }

                if (this.lattebutton) {
                    CoffeeMachineGUI.getCoffeeMachineGUI().getLatte().setBackground(Color.green);
                } else {
                    CoffeeMachineGUI.getCoffeeMachineGUI().getLatte().setBackground(Color.red);
                }

                if (this.machiattobutton) {
                    CoffeeMachineGUI.getCoffeeMachineGUI().getMacchiato().setBackground(Color.green);
                } else {
                    CoffeeMachineGUI.getCoffeeMachineGUI().getMacchiato().setBackground(Color.red);
                }

                if (this.mochabutton) {
                    CoffeeMachineGUI.getCoffeeMachineGUI().getMocha().setBackground(Color.green);
                } else {
                    CoffeeMachineGUI.getCoffeeMachineGUI().getMocha().setBackground(Color.red);
                }

                if (this.small) {
                    CoffeeMachineGUI.getCoffeeMachineGUI().getSmall().setBackground(Color.green);
                } else {
                    CoffeeMachineGUI.getCoffeeMachineGUI().getSmall().setBackground(Color.red);
                }

                if (this.medium) {
                    CoffeeMachineGUI.getCoffeeMachineGUI().getMedium().setBackground(Color.green);
                } else {
                    CoffeeMachineGUI.getCoffeeMachineGUI().getMedium().setBackground(Color.red);
                }

                if (this.large) {
                    CoffeeMachineGUI.getCoffeeMachineGUI().getLarge().setBackground(Color.green);
                } else {
                    CoffeeMachineGUI.getCoffeeMachineGUI().getLarge().setBackground(Color.red);
                }

                System.out.println("Mocha" + mochabutton);

            } else {
                CoffeeMachineGUI.getCoffeeMachineGUI().getAmericano().setBackground(Color.lightGray);
                CoffeeMachineGUI.getCoffeeMachineGUI().getCappuccino().setBackground(Color.lightGray);
                CoffeeMachineGUI.getCoffeeMachineGUI().getEspresso().setBackground(Color.lightGray);
                CoffeeMachineGUI.getCoffeeMachineGUI().getLatte().setBackground(Color.lightGray);
                CoffeeMachineGUI.getCoffeeMachineGUI().getMacchiato().setBackground(Color.lightGray);
                CoffeeMachineGUI.getCoffeeMachineGUI().getMocha().setBackground(Color.lightGray);
                CoffeeMachineGUI.getCoffeeMachineGUI().getSmall().setBackground(Color.lightGray);
                CoffeeMachineGUI.getCoffeeMachineGUI().getMedium().setBackground(Color.lightGray);
                CoffeeMachineGUI.getCoffeeMachineGUI().getLarge().setBackground(Color.lightGray);
            }
        } 
    }

}
