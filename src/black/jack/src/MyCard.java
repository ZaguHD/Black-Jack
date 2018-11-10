/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack.src;

import java.util.ArrayList;

/**
 *
 * @author emazi
 */
public class MyCard {
    
    /*Variable*/
    private int money;
    private ArrayList<Card> cards = new ArrayList<>();
    private int points;

    
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
        points += card.getPoints();
        cards.add(card);
        System.out.println("Card taked: "+card.getName() + " Points actually "+points );
        if(points>21){
            return true;
        }else{
            return false;
        }
    }
    public ArrayList<Card> getCards() {
        return cards;
    }
    
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    /*Methods*/






}
