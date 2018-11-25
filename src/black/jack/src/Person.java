/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack.src;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;

/**
 *
 * @author emazi
 */
public abstract class Person {
    
    /*Variable*/
    private int id;
    protected MyCard[] card = new MyCard[2];
    private BooleanProperty finish = new SimpleBooleanProperty(false); //If person won't or can't take more cards
    private BooleanProperty turn   = new SimpleBooleanProperty(); 
    
    /*Constructor*/
    public Person(){
        card[0] = new MyCard();
        card[1] = new MyCard();
    }
    
    
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

    public synchronized void setCard(MyCard[] card) {
        this.card = card;
    }

    public BooleanProperty getFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish.set(finish);
    }

    public synchronized BooleanProperty getTurn() {
        return turn;
    }

    public synchronized void setTurn(boolean turn) {
        this.turn.set(turn);
    }

 

    
    /*Methods*/
    public void setPoints(){
        
    }
    
    public boolean takeACard(Card[] card){
        return true;
    }
    public synchronized Image getLastCardImage(int hand){
        for(Card card : getCard()[0].getCards()){
            if(!card.isUsedInGui()){
                card.setUsedInGui(true);
                return card.getImage();
            }
        }
        for(Card card : getCard()[1].getCards()){
            if(!card.isUsedInGui()){
                card.setUsedInGui(true);
                return card.getImage();
            }
        }
        System.out.println("error: Class Person: Method GetLastCardImage: no card found");
        return getCard()[hand].getCards().get(getCard()[hand].getCards().size()-1).getImage();
    }

    

    
}
