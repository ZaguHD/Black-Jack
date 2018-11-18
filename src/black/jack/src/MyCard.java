/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack.src;

import java.util.ArrayList;
import javafx.application.Platform;

/**
 *
 * @author emazi
 */
public class MyCard {
    
    /*Variable*/
    private int money;
    private ArrayList<Card> cards = new ArrayList<>();
    private int points;
    private boolean stand = false;
    
    /*Constructor*/
    
    
    
    /*Getter & Setter*/

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
    public synchronized boolean setCard(Card card){
        points += card.getPoints(); //points added 
        cards.add(card); //Card added
        System.out.println("Card taked: "+card.getName() + " Points actually "+points );
        if(points>21){ //Points more than 21 -> user lost
            return true;
        }else{
            return false;
        }
    }
    public synchronized ArrayList<Card>  getCards() {
        return cards;
    }
    
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    
    /*Methods*/

    public synchronized boolean isStand() {
        return stand;
    }

    public synchronized void setStand(boolean stand) {
        this.stand = stand;
    }






}
