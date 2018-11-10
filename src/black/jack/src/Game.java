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
    
    private ArrayList<Player> player = new ArrayList<>();
    private Croupier croupier = new Croupier();
    private Cardset cardset;
    private boolean endGame;
    private String move ="";
    private int indexOfTurn = 0;
    private int numberOfPlayer = player.size();
 
    
    /*Constructor*/

    public Game() {
        player.add(new Player());
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

    public synchronized ArrayList<Player> getPlayer() {
        return player;
    }

    public void setPlayer(ArrayList<Player> player) {
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
       indexOfTurn = 0;
       numberOfPlayer = player.size();
       player.get(indexOfTurn).setTurn(true);
    }
    
  
    @Override
    public void run(){
        prepareGame();
        
        while(!endGame){
            if(!makeaMove()){
            }else if(indexOfTurn == numberOfPlayer){
                croupier.takeACard(cardset.getRandom(1));
                indexOfTurn = 0;
                System.out.println("Croupier Turn");
                player.get(indexOfTurn).setTurn(true);
            }else{
                indexOfTurn++;
                player.get(indexOfTurn).setTurn(true);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Error: Thread Sleeping");
            }
     
        }
        System.out.println("fertig: User has more than 21 points");
    }
    
    private synchronized boolean makeaMove(){
        if(getMove().equals("btnPass")){
            player.get(indexOfTurn).setTurn(false);
            setMove("");
            System.out.println("Pass");
            indexOfTurn++;
            return true;
        }else if(!getMove().equals("")){
            switch(getMove()){
                case "btnSplit":
                    player.get(0).doDouble();
                    System.out.println("SPLIT");
                    break;
                case "btnDouble":
                    player.get(0).doDouble();
                    System.out.println("Double");
                    break;
                case "btnSetMoney":
                    player.get(0).setMoney(200);
                    System.out.println("Set Money");
                    break;
                case "btnTakeACard":
                    System.out.println("hello");
                    endGame = getPlayer().get(0).takeACard(cardset.getRandom(1));
                    break;
                default: System.out.println("Error: Wrong Button");
                    break;
            }
            setMove("");
            return false;
        }
        return false;
    }
    
}
