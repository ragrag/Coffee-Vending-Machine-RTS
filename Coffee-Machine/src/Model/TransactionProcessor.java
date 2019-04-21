/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.logging.Level;
import java.util.logging.Logger;
import system.views.CoffeeMachineGUI;

/**
 *
 * @author ragrag
 */
public class TransactionProcessor extends Thread {

    private static TransactionProcessor transactionInstance = null;
    Drink drink;
    int size;
    int suger;
    Boolean state = null;
    Boolean pressCancel = false;
    int balance;
    int orderPrice;
    int change;

    public static TransactionProcessor getInstance() {

        if (transactionInstance != null) {
            return transactionInstance;
        } else {
            return transactionInstance = new TransactionProcessor();
        }

    }

    public TransactionProcessor() {
        resetTransaction();
        this.start();
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSuger(int suger) {
        this.suger = suger;
    }

    public void startOrder() {
        if (drink == null) {
            Screen.getScreen().display("Please select drink first");
        } else {
            calculatePrice();
            change = balance - orderPrice;
            this.state = false;
        }

    }
    
    public void calculatePrice(){
        orderPrice = drink.price + ((size - 1) * 5);
    }
    
    public int getOrderPrice(){
        return orderPrice;
    }
    
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void resetTransaction() {
        suger = 0;
        size = 1;
        state = null;
        balance = 0;
        orderPrice = 0;
        pressCancel = false;
        change = 0;
        drink = null;
    }

    public void cancel() {
        if (state == null) {
            System.out.println("Cancel");
            MoneyHandler.getMoneyHandler().returnMoneyToUser(balance);
            resetTransaction();
        } else {
            System.out.println("Cannot Cancel");
        }

    }

    @Override
    public void run() {
        while (true) {
            if (CoffeeMachine.getInstance().getPowered()) {
                if (state != null) {
                    if (state == false) {
                        if (change < 0) {
                            Screen.getScreen().display("The balance equal "+balance+"\nOrder price "+orderPrice+"\nInsufficent balance");
                            state = null;
                        } else {
                            state = true;
                        }
                    } else if (state == true) {
                        CoffeeMachine.getInstance().disableBottuns();

                        Thread t = new Thread() {
                            public void run() {
                                Mixer.getInstance().dispatchIngredients(suger, size, drink);
                            }
                        };
                        t.start();

                        try {
                            t.join();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(TransactionProcessor.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        //start code
                        CoffeeMachine.getInstance().enableBottuns();
                        MoneyHandler.getMoneyHandler().returnMoneyToUser(change);
                        resetTransaction();
                    }
                }
            } else {

            }

            try {
                this.sleep(1000);
            } catch (InterruptedException ex) {
                //Logger.getLogger(WaterHeater.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
