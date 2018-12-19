/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack.src;

import black.jack.src.enumSrc.Rank;
import black.jack.src.enumSrc.Suit;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author emazi
 */
public class Cardset {
    
    /*Variable*/
    private ArrayList<Card> cardset = new ArrayList<>();
    
    /*Constructor*/

    public Cardset() {
        makeSet();
    }
    
 

    /*Getter & Setter*/
    public ArrayList<Card> getCardset(){
        return cardset;
        
    }
    
    public void setCardset(ArrayList<Card> cardset) {    
        this.cardset = cardset;
    }
    
    //For Test Split
    public synchronized Card[] getCard(){
        Card[] cards = new Card[2];
        cards[0]=cardset.get(1);
        return cards;
    } 

    
    //For Test Balc Jack
    public synchronized Card[] getAce(){
        Card[] cards = new Card[2];
        cards[0]=cardset.get(0);
        return cards;
    }    
    public synchronized Card[] getTen(){
        Card[] cards = new Card[2];
        cards[0]=cardset.get(11);
        return cards;
    }     

    /*Methods*/
    public void makeSet() {
        for (int i = 0; i < 3; i++) {
            for(Suit suit : Suit.values()){
                for(Rank rank : Rank.values()){
                    cardset.add(new Card(suit,rank));
                  //  System.out.println(cardset.get(cardset.size()-1).getName());
                }  
            }
        }
    }
    
    public synchronized Card[] getRandom(int numberOfCards){
        int random = (int) (Math.random() * cardset.size());
        Card[] cards = new Card[2];
        if(numberOfCards == 1){
            cards[0]=cardset.get(random);
            cardset.remove(random);
            return cards;
        }else{
            cards[0]=cardset.get(random);
            cardset.remove(random);
            cards[1]=cardset.get(random);
            random = (int) (Math.random() * cardset.size());
            cardset.remove(random);
            return cards;
        }
    }
}
