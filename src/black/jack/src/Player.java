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
    private boolean splitted = false;
    
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

    public synchronized boolean isSplitted() {
        return splitted;
    }

    public synchronized void setSplitted(boolean splitted) {
        this.splitted = splitted;
    }
    
    
    
    public void setWinMoney(int money) {
        synchronized(this){
            Platform.runLater(()->{
                this.money.setValue(money);
            });            
        }
    }

    /*Methods*/
    
    //Split
    public synchronized boolean doSplit(IntegerProperty moneyInGame){
        if(isSplitPossible() && moneyInGame.getValue()<=money.getValue()){
           doDouble(moneyInGame);
           getCard()[1].setCard(getCard()[0].getCards().get(1)); //split cards into two hands
           getCard()[0].getCards().remove(1);
           getCard()[0].setStand(true);
           System.out.println("Splitted: now u can put into first hand");
           return true;
        }
        return false;
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
        System.out.println("player:");
        if(splitted){
            if(getCard()[0].isStand()){
                getCard()[0].setCard(card[0]);
                return false;
            }else{
                return getCard()[1].setCard(card[0]);
            }
        }else{
            return  getCard()[0].setCard(card[0]);
        }
    }
    
    //set money to to my Card 
    public synchronized void setMoneyToMyCard(){ 
        
    }
    //set money to to my Card 
    public synchronized void doPass(){ 
        
    }
    
    //Is Split possible?
    public synchronized boolean isSplitPossible(){
        if(card[0].getCards().size() == 2 && (card[1].getCards().isEmpty())
          && card[0].getCards().get(0).getPoints() == card[0].getCards().get(1).getPoints()) {
            splitted = true;
            return true;
        }        
        return false;
    }
    
    //Is Double possible?
    public synchronized boolean isDoublePossible(){
        return (card[0].getCards().size() == 2 && (card[1].getCards().isEmpty()));
    }
}
