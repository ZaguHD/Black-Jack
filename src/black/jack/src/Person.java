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
public abstract class Person {
    
    /*Variable*/
    private int id;
    private MyCard card[] = new MyCard[2];
    private boolean finish; //If person won't or can't take more cards
    private boolean turn; 
    
    /*Constructor*/

    
    
    /*Getter & Setter*/
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MyCard[] getCard() {
        return card;
    }

    public void setCard(MyCard[] card) {
        this.card = card;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }  

    
    /*Methods*/
    public void setPoints(){
        
    }
    
    public void takeACard(){
        
    }

    

    
}
