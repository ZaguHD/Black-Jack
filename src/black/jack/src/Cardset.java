/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack.src;

import black.jack.src.enumSrc.Rank;
import black.jack.src.enumSrc.Suit;
import java.util.ArrayList;

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

    /*Methods*/
    public void makeSet() {
        for (int i = 0; i < 3; i++) {
            for(Suit suit : Suit.values()){
                for(Rank rank : Rank.values()){
                    cardset.add(new Card(suit,rank));
                    System.out.println(cardset.get(cardset.size()-1).getName());
                }  
            }
        }
    }
    
    public void getRandom(){    
        
        
        
    }
}
