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
public class Croupier extends Person{
       
    /*Variable*/

    
    /*Constructor*/

    
    
    /*Getter & Setter*/
    



    
    /*Methods*/ 
    @Override
    public boolean takeACard(Card[] card){
        if(getCard()[0].getPoints() <= 17){
            return getCard()[0].setCard(card[0]);
        }else{
             System.out.println("Croupier has more than 17: End of Game");
             return true;
        }
       
    }
 
}
