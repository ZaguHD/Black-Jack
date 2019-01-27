/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack.src;

import java.util.Arrays;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

/**
 *
 * @author emazi
 */
public class Player extends Person{
    
    /*Variable*/
    private IntegerProperty money = new SimpleIntegerProperty(1000);
    private boolean splitted = false; //for game
    private BooleanProperty split = new SimpleBooleanProperty(false); //for gui
    private BooleanProperty noSplit = new SimpleBooleanProperty(true); //for gui
    
    private BooleanProperty splitable = new SimpleBooleanProperty(false); //disable and enable Splut button 
    private BooleanProperty passable = new SimpleBooleanProperty(false); //disable and enable Splut button 
    private BooleanProperty doubleable = new SimpleBooleanProperty(false); //disable and enable Splut button 
    private BooleanProperty hittable = new SimpleBooleanProperty(false); //disable and enable Splut button 
    
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

    public synchronized BooleanProperty getSplit() {
        return split;
    }

    public synchronized void setSplit(boolean split) {
        this.split.set(split);
    }

    public synchronized BooleanProperty getNoSplit() {
        return noSplit;
    }

    public synchronized void setNoSplit(boolean split) {
        this.noSplit.set(split);
    }

    public BooleanProperty getSplitable() {
        return splitable;
    }

    public synchronized void setSplitable(boolean splitable) {
        this.splitable.setValue(splitable);
    }

    public synchronized BooleanProperty getPassable() {
        return passable;
    }

    public synchronized void setPassable(BooleanProperty passable) {
        this.passable = passable;
    }

    public synchronized BooleanProperty getDoubleable() {
        return doubleable;
    }

    public synchronized void setDoubleable(BooleanProperty doubleable) {
        this.doubleable = doubleable;
    }

    public synchronized BooleanProperty getHittable() {
        return hittable;
    }

    public synchronized void setHittable(BooleanProperty hittable) {
        this.hittable = hittable;
    }
    

    @Override
    public synchronized void setTurn(boolean turn){
        setTurnUnder(turn);
        if(turn == false){
            splitable.set(turn);
            doubleable.set(turn);
            hittable.set(turn);
            passable.set(turn);            
        }else{
            hittable.set(turn);
            passable.set(turn);   
        }
    }
    
    public synchronized void checkSplitableAndDoubleable(IntegerProperty moneyInGame){
        if(moneyInGame.getValue()<=money.getValue()){
            if(card[0].getCards().size() == 2 && (card[1].getCards().isEmpty())
             && card[0].getCards().get(0).getPoints() == card[0].getCards().get(1).getPoints()){
                splitable.set(true);
            }else{
                splitable.set(false);
            }
            if(card[0].getCards().size() == 2 && (card[1].getCards().isEmpty())){
                doubleable.set(true);
            }else{
                doubleable.set(false);
            }
        }else{
           doubleable.set(false);
           splitable.set(false);
        }
        
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
           setSplit(true);
            setNoSplit(false);
           doDouble(moneyInGame);
           getCard()[1].setCard(getCard()[0].getCards().get(1)); //split cards into two hands
           getCard()[0].setPoints(getCard()[0].getPoints()/2);
           Platform.runLater(()->{
                getCard()[0].setGuiPoints(getCard()[0].getPoints());
           });
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
                if(getCard()[0].setCard(card[0])){
                    getCard()[0].setStand(false);
                    getCard()[1].setStand(true);
                }
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
