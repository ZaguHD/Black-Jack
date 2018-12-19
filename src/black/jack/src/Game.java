/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack.src;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


/**
 *
 * @author emazi
 */
public class Game extends Thread{
    
    private Player player = new Player();
    private Croupier croupier = new Croupier();
    private Cardset cardset;
    private boolean endGame;
    private String move ="";
    private int [] winner = new int[2]; // 0 = lost 1=player 2= draw
    private IntegerProperty moneyInHand1 = new SimpleIntegerProperty(0); 
    private IntegerProperty moneyInHand2 = new SimpleIntegerProperty(0); 
    //private int numberOfPlayer = player.size();
 
    
    /*Constructor*/

    public Game() {

    }
    
    

    /*Getter & Setter*/
    
   public synchronized Cardset getCardset() {
        return cardset;
    }
    
    public synchronized void setCardset(Cardset cardset) {    
        this.cardset = cardset;
    }

    public synchronized boolean isEndGame() {
        return endGame;
    }

    public synchronized void setEndGame(boolean endGame) {
        this.endGame = endGame;

    }

    public synchronized String getMove() {
        return move;
    }

    public synchronized void setMove(String move) {
        this.move = move;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Croupier getCroupier() {
        return croupier;
    }

    public void setCroupier(Croupier croupier) {
        this.croupier = croupier;
    }

    public synchronized IntegerProperty getMoneyInHand1() {
        return moneyInHand1;
    }
    public synchronized IntegerProperty getMoneyInHand2() {
        return moneyInHand1;
    }

    public synchronized int[] getWinner() {
        return winner;
    }

    public synchronized void setWinner(int[] winner) {
        this.winner = winner;
    }
    

    public synchronized void setMoneyInHand1(int moneyInGame) {
        this.moneyInHand1.set(this.moneyInHand1.getValue()+moneyInGame);
        //System.out.println("Money in Game Hand 1: "+this.moneyInHand1.getValue());
    }
    public synchronized void setMoneyInHand2(int moneyInGame) {
        this.moneyInHand2.set(this.moneyInHand2.getValue()+moneyInGame);
        //System.out.println("Money in Game Hand 2:"+this.moneyInHand2.getValue());
    }   
    
    //User become on start 2 Cards and Croupier 1 
    private synchronized void prepareGame(){
       player.setTurn(true);
       for(int index = 0; index < 2; index ++){
            player.takeACard(cardset.getRandom(1));             
       }       
       if(player.getCard()[0].getPoints() ==21){
           endGame = true;
       }else{
          croupier.takeACard(cardset.getRandom(1));   
       }
    }
    
  
    @Override
    public void run(){
        prepareGame();      
        while(!endGame){ // so long as User can use Buttons(Double,Split etc.)
            endGame = makeaMove(); //User input (Split,Pass etc.)
            try {
                Thread.sleep(100); //Thread's sleeping
            } catch (InterruptedException ex) {
                System.out.println("Error: Thread Sleeping");
            }
     
        }
         try {
        Thread.sleep(250); //Thread's sleeping
            } catch (InterruptedException ex) {
                System.out.println("Error: Thread Sleeping");
        }
        player.setTurn(false); //User can't klick Buttons(Split etc.)
        player.setFinish(true);
            while(((getPlayer().getCard()[0].getPoints() <= 21 
                ||(getPlayer().getCard()[1].getPoints() <= 21 
                && getPlayer().getCard()[1].getPoints() !=0)))
                && !(getPlayer().getCard()[0].getPoints() == 21 && getPlayer().getCard()[0].getCards().size() ==2 )){

                if(croupier.takeACard(cardset.getRandom(1))){
                   break;                    
                }
                System.out.println("Croupier is taking a card");              
             try {
                Thread.sleep(750); //Thread's sleeping
            } catch (InterruptedException ex) {
                System.out.println("Error: Thread Sleeping");
            }
            }
        findWinner();
        System.out.println("Player points: "+player.getCard()[0].getPoints()+ " Croupier points: "+croupier.getCard()[0].getPoints());       
    }
    //User klicked a Button 
    private synchronized boolean makeaMove(){
        if(getMove().equals("btnPass")){ // User klicked Pass
            setMove("");                 // String to ""
            System.out.println("Pass");  // Sout
            if(getPlayer().isSplitted() && !getPlayer().getCard()[1].isStand()){
               getPlayer().getCard()[0].setStand(false);
               getPlayer().getCard()[1].setStand(true);
               return false;
            }
            return true;                 // return true -> The Croupier will now ... 
        }else if(!getMove().equals("")){
            switch(getMove()){
                case "btnSplit":
                    if(player.doSplit(getMoneyInHand1())){
                        setMoneyInHand2(getMoneyInHand1().getValue());   
                        System.out.println("SPLIT");
                    }
                    break;
                case "btnDouble":
                    if(player.doDouble(getMoneyInHand1())){
                        setMoneyInHand1(getMoneyInHand1().getValue());
                        System.out.println("Double");
                        return getPlayer().takeACard(cardset.getRandom(1));
                    }
                    break;
                case "btnTakeACard":
                    setMove("");
                    return getPlayer().takeACard(cardset.getRandom(1));
                default: System.out.println("Error: Wrong Button");
                    break;
            }
            setMove("");
            return false;
        }
        return false;
    } 
    private synchronized void findWinner(){
        int money = 0;
        for(int hand = 0; hand<2; hand++){
            if((!(player.getCard()[hand].getPoints()>21)) && (player.getCard()[hand].getPoints() > croupier.getCard()[0].getPoints() 
            || croupier.getCard()[0].getPoints() >21) && player.getCard()[hand].getPoints() != 0){
                System.out.println("player won hand: "+hand);
                     getWinner()[hand] = 1;
                    //User bekommt Geld
                    if(hand ==0){
                        money = money + (getMoneyInHand1().getValue()*2);
                    }else{
                        money = (money + getMoneyInHand2().getValue()*2);
                    }
            }else if(player.getCard()[hand].getPoints()==croupier.getCard()[0].getPoints()
                   &&!(croupier.getCard()[0].getCards().size()==2 && croupier.getCard()[0].getPoints()==21)){
                System.out.println("draw");
                getWinner()[hand] = 2;
                money = money + getMoneyInHand1().getValue();
            }else{
                System.out.println("player lost");
               getWinner()[hand] = 3;
                
            }                
        }
        player.setWinMoney(player.getMoney().getValue()+(money));
    }
}
