/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack.src;

import java.util.Arrays;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author emazi
 */
public class Player extends Person{
    
    /*Variable*/
    private IntegerProperty money = new SimpleIntegerProperty(1000);
    
    /*Constructor*/
//    public Player(){
//        money.set(1000);
//    }
//    
    
    /*Getter & Setter*/
    public synchronized IntegerProperty getMoney() {
        return money;
    }

    public synchronized void setMoney(int money) {
        this.money.setValue(money);
    }
    
    public synchronized void setWinMoney(int money) {
        Platform.runLater(()->{
            this.money.setValue(money);
        });
    }

    /*Methods*/
    
    //Split
    public synchronized void doSplit(){
     
    }
    
    //Double
    public synchronized boolean doDouble(IntegerProperty moneyInGame){
        if(isDoublePossible() && moneyInGame.getValue()<=money.getValue()){
           int newValue = moneyInGame.getValue();
           //Gui Changed 
           Platform.runLater(()->{
                money.setValue(money.getValue()-newValue);
           });
           return true;
        }
        return false;
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
        return card[0].getCards().size() == 2;
    }
}
