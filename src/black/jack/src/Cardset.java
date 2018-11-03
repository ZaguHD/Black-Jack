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
    }
    
    public void getRandom(){    
    }
}
