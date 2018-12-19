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
        if(getCard()[0].getPoints() <= 16){
            System.out.println("croupier:");
            if(getCard()[0].setCard(card[0])){
               return true;
            }else{
                if(getCard()[0].getPoints() > 16){
                    return true;
                }else{
                    return false; 
                }
            }
        }else{
             System.out.println("Croupier has more than 16: End of Game");
             return true;
        }
       
    }
 
}
