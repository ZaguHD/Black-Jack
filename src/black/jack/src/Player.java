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
public class Player extends Person{
    
    /*Variable*/
    private int money;
    
    /*Constructor*/

    
    
    /*Getter & Setter*/
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


    /*Methods*/
    
    //Split
    public void doSplit(){
        
    }
    
    //Double
    public void doDouble(){
        
    }
    
    //take a Card
    @Override
    public void takeACard(){
        
    }
    
    //set money to to my Card 
    public void setMoneyToMyCard(){ 
        
    }
    
    //Split possible
    public boolean isSplitPossible(){
        boolean isSplitPossible = false;
        if(card[0].getCards().size() == 2) {
            
            card[0].getCards().get(0);
            
            if(card[0].getCards().get(0).equals(card[0].getCards().get(1))) {
                return true;
            }
           
        
        return isSplitPossible; 
        }
        else{
          return false;  
        }
    }
    
    //Double possible
    public boolean isDoublePossible(){
        boolean isDoublePossible = false;
        
        return isDoublePossible;
    }
      
}
