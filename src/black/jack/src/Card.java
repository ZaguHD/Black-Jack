/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack.src;

/**
 *
 * @author emazi
 */
public class Card {
    
    /*Variable*/
    private String name;
    private int points;
    
    /*Constructor*/
    

    
    
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


}
