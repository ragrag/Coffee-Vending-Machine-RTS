/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import backend.event.engine.Engine;
import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.views.CoffeeMachineGUI;

/**
 *
 * @author 
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
                        if (blinkingThread != null) {
                            blinkingThread.stop();
                        }
                        drinkBlinking(d);
                        TransactionProcessor.getInstance().setDrink(d);
                        TransactionProcessor.getInstance().calculatePrice();
                        Screen.getScreen().display("The balance equal "+TransactionProcessor.getInstance().getBalance()+"\nOrder Price "+ TransactionProcessor.getInstance().getOrderPrice());
                    }
                });

        Engine.createStatement("select sugarSelection from SelectSugar_EVENT")
                .setSubscriber(new Object() {
                    public void update(int sugar) throws InterruptedException {
                        TransactionProcessor.getInstance().setSugar(sugar);
                    }
                });

        Engine.createStatement("select size from SelectSize_EVENT")
                .setSubscriber(new Object() {
                    public void update(int size) throws InterruptedException {
                        if (sizeBlinkingThread != null) {
                            sizeBlinkingThread.stop();
                        }
                        sizeBlinking(size);
                        TransactionProcessor.getInstance().setSize(size);
                        TransactionProcessor.getInstance().calculatePrice();
                        Screen.getScreen().display("The balance equal "+TransactionProcessor.getInstance().getBalance()+"\nOrder Price "+ TransactionProcessor.getInstance().getOrderPrice());
                    }
                });

        Engine.createStatement("select startOrder from Start_EVENT")
                .setSubscriber(new Object() {
                    public void update(Boolean startOrder) throws InterruptedException {
                        stopBlinking();
                        TransactionProcessor.getInstance().startOrder();
                    }
                });
        Engine.createStatement("select cancel from Cancel_EVENT")
                .setSubscriber(new Object() {
                    public void update(Boolean cancel) throws InterruptedException {
                        stopBlinking();
                        TransactionProcessor.getInstance().cancel();
                    }
                });

        this.start();
    }

    public void drinkBlinking(Drink d) {
        blinkingThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SelectionPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (null != d.getName()) switch (d.getName()) {
                        case "espresso":
                            CoffeeMachineGUI.getCoffeeMachineGUI().getEspresso().setBackground(Color.CYAN);
                            break;
                        case "mocha":
                            CoffeeMachineGUI.getCoffeeMachineGUI().getMocha().setBackground(Color.CYAN);
                            break;
                        case "latte":
                            CoffeeMachineGUI.getCoffeeMachineGUI().getLatte().setBackground(Color.CYAN);
                            break;
                        case "machiatto":
                            CoffeeMachineGUI.getCoffeeMachineGUI().getMacchiato().setBackground(Color.CYAN);
                            break;
                        case "americano":
                            CoffeeMachineGUI.getCoffeeMachineGUI().getAmericano().setBackground(Color.CYAN);
                            break;
                        case "cappuccino":
                            CoffeeMachineGUI.getCoffeeMachineGUI().getCappuccino().setBackground(Color.CYAN);
                            break;
                        default:
                            break;
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
