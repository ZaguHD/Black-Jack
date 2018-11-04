/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack.src;

import black.jack.src.enumSrc.Rank;
import black.jack.src.enumSrc.Suit;

/**
 *
 * @author emazi
 */


public class Card {
    
    /*Variable*/
    private Suit suit;
    private Rank rank;
    private String name;
    private int points;
    
    /*Constructor*/

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        name = suit.name() + "-" +rank.name();
        setPoint(rank);
    }
    
    

    
    
    /*Getter & Setter*/
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }    

    
    /*Methods*/     
    //Check if u have two the same Cards
    @Override
    public boolean equals(Object obj){
        Card card= (Card) obj;
       
        return name.equals(card.getName());
    }
    
    private void setPoint(Rank rank){
        switch (rank){
            case TWO: points = 2;
            break;
            case THREE: points = 3;
            break;
            case FOUR: points = 4;
            break;
            case FIVE: points = 5;
            break;
            case SIX: points = 6;
            break;
            case SEVEN: points = 7;
            break;
            case EIGHT: points = 8;
            break;
            case NINE: points = 9;
            break;
            case TEN: points = 10;
            break;
            case JACK: points = 10;
            break;
            case QUEEN: points = 10;
            break;
            case KING: points = 10;
            break;  
            case ACE: points = 11;
            break;
        }
    }
    
}

