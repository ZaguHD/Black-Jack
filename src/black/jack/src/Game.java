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
    private IntegerProperty moneyInGame = new SimpleIntegerProperty(0); 
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

    public synchronized IntegerProperty getMoneyInGame() {
        return moneyInGame;
    }

    public synchronized void setMoneyInGame(int moneyInGame) {
        this.moneyInGame.set(this.moneyInGame.getValue()+moneyInGame);
        System.out.println("Money in Game: "+this.moneyInGame.getValue());
    }
    
    //User become on start 2 Cards and Croupier 1 
    private synchronized void prepareGame(){
       player.setTurn(true);
       for(int index = 0; index < 2; index ++){
            croupier.takeACard(cardset.getRandom(1));
            player.takeACard(cardset.getRandom(1));
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
        player.setTurn(false); //User can't klick Buttons(Split etc.)
        if(player.getCard()[0].getPoints()>21){
            System.out.println("player lost");
        }else{
            while(!croupier.takeACard(cardset.getRandom(1))){
                System.out.println("Croupier is taking a card");
            }
            if(player.getCard()[0].getPoints() > croupier.getCard()[0].getPoints() || croupier.getCard()[0].getPoints() >21){
                System.out.println("player won");
                    //User bekommt Geld
                    player.setWinMoney(player.getMoney().getValue()+(getMoneyInGame().getValue()*2)); 
            }else{
                System.out.println("player lost");
            }
        }
        System.out.println("Player points: "+player.getCard()[0].getPoints()+ " Croupier points: "+croupier.getCard()[0].getPoints());       
    }
    //User klicked a Button 
    private synchronized boolean makeaMove(){
        if(getMove().equals("btnPass")){ // User klicked Pass
            setMove("");                 // String to ""
            System.out.println("Pass");  // Sout
            return true;                 // return true -> The Croupier will now ... 
        }else if(!getMove().equals("")){
            switch(getMove()){
                case "btnSplit":
                    player.doSplit();
                    System.out.println("SPLIT");
                    break;
                case "btnDouble":
                    if(player.doDouble(getMoneyInGame())){
                        setMoneyInGame(getMoneyInGame().getValue());}
                    System.out.println("Double");
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
}
