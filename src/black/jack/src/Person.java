/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack.src;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author emazi
 */
public abstract class Person {
    
    /*Variable*/
    private int id;
    private MyCard card[] = new MyCard[2];
    private BooleanProperty finish = new SimpleBooleanProperty(); //If person won't or can't take more cards
    private BooleanProperty turn   = new SimpleBooleanProperty(); 
    
    /*Constructor*/

    
    
    /*Getter & Setter*/
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MyCard[] getCard() {
        return card;
    }

    public void setCard(MyCard[] card) {
        this.card = card;
    }

    public BooleanProperty getFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish.set(finish);
    }

    public BooleanProperty getTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn.set(turn);
    }

 

    
    /*Methods*/
    public void setPoints(){
        
    }
    
    public void takeACard(){
        
    }

    

    
}
