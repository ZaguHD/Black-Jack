/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack.src;

import java.util.ArrayList;
import java.util.Arrays;


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
    private int indexOfTurn = 0;
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

    
    private synchronized void prepareGame(){
       cardset = new Cardset();
       player.setTurn(true);
       for(int index = 0; index < 2; index ++){
            croupier.takeACard(cardset.getRandom(1));
            player.takeACard(cardset.getRandom(1));
       }
    }
    
  
    @Override
    public void run(){
        prepareGame();      
        while(!endGame){
            endGame = makeaMove();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Error: Thread Sleeping");
            }
     
        }
        if(player.getCard()[0].getPoints()>21){
            System.out.println("player lost");
        }else{
            while(!croupier.takeACard(cardset.getRandom(1))){
                System.out.println("Croupier is taking a card");
            }
            if(player.getCard()[0].getPoints() > croupier.getCard()[0].getPoints() || croupier.getCard()[0].getPoints() >21){
                System.out.println("player won");
            }else{
                System.out.println("player lost");
            }
        }
        System.out.println("Player points: "+player.getCard()[0].getPoints()+ " Croupier points: "+croupier.getCard()[0].getPoints());
    }
    
    private synchronized boolean makeaMove(){
        if(getMove().equals("btnPass")){
            player.setTurn(false);
            setMove("");
            System.out.println("Pass");
            return true;
        }else if(!getMove().equals("")){
            switch(getMove()){
                case "btnSplit":
                    player.doDouble();
                    System.out.println("SPLIT");
                    break;
                case "btnDouble":
                    player.doDouble();
                    System.out.println("Double");
                    break;
                case "btnSetMoney":
                    player.setMoney(200);
                    System.out.println("Set Money");
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
