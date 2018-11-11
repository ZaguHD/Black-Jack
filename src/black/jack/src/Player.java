/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack.src;

import java.util.Arrays;

/**
 *
 * @author emazi
 */
public class Player extends Person{
    
    /*Variable*/
    private int money;
    
    /*Constructor*/

    
    
    /*Getter & Setter*/
    public synchronized int getMoney() {
        return money;
    }

    public synchronized void setMoney(int money) {
        this.money = money;
    }


    /*Methods*/
    
    //Split
    public synchronized void doSplit(){
     
    }
    
    //Double
    public synchronized void doDouble(){
        
    }
    
    //take a Card
    @Override
    public synchronized boolean takeACard(Card[] card){
//        if(isSplitPossible()){
//            
//        }else{
            System.out.println("player:");
            return  getCard()[0].setCard(card[0]);
//        }
//        return false;
    }
    
    //set money to to my Card 
    public synchronized void setMoneyToMyCard(){ 
        
    }
    //set money to to my Card 
    public synchronized void doPass(){ 
        
    }
    
    //Is Split possible?
    public synchronized boolean isSplitPossible(){
        boolean isSplitPossible = false;
        
        if(card[0].getCards().size() == 2) {
            card[0].getCards().get(0);
            
            if(card[0].getCards().get(0).equals(card[0].getCards().get(1))) {
                isSplitPossible = true;
            } 
        }
        else{
          return false;  
        }
        
        return isSplitPossible;
    }
    
    //Is Double possible?
    public synchronized boolean isDoublePossible(){
        boolean isDoublePossible = false;
        if(card[0].getCards().size() == 2){
            isDoublePossible = true;
        }
        else{
            isDoublePossible = false;
        }
        
        return isDoublePossible;
    }
      
}
