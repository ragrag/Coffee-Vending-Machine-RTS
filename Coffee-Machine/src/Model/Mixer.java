/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ragrag
 */
public class Mixer {
    private static Mixer Mixer=null;
    
    
    public static Mixer getInstance(){
        if(Mixer != null){
            return Mixer;
        }
        else{
            return Mixer = new Mixer();
        }
    }
    
    public void mix(){
    
    }
    
    public void pour(){
    
    }
    
    public void dispatchIngredients(int sugar,int size,Drink drink){
        
    }
    
}
    
    
    
    

