/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack.src;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author emazi
 */
public class GameCr implements Initializable {

       
    /*Variable*/   
    private BlackJack manager;
    Game game = new Game();
    Cardset cardset;
    private int oldValue;
    private int newValue;
    
    @FXML
    ImageView background;
    
    @FXML 
    Button btnSplit,btnDouble,btnTakeACard,btnPass;
    
    @FXML
    Button btn500,btn100,btn25,btn5,btn1;
    
    @FXML
    Label labelBankBalance;
    
    @FXML
    ArrayList<Button> btnArray = new ArrayList<>();
    
    
    /*Methods*/
    public void startAGame() {
        int money = game.getPlayer().getMoney().getValue();
        game = new Game();
        if(btnArray.isEmpty()){
            btnArray.add(btnSplit);
            btnArray.add(btnDouble);
            btnArray.add(btnTakeACard);
            btnArray.add(btnPass);
            cardset = new Cardset();
        }
        game.setDaemon(true);
        game.setCardset(cardset);
        game.start();
        setListener();
        game.getPlayer().setMoney(money);
    }
 
    
    @FXML 
    private void takeACard(ActionEvent event){
        game.setMove(((Button)event.getTarget()).getId());
    }
    
    @FXML
    private void setMoney(ActionEvent event){
        if(!game.isAlive())
            startAGame();
            String money = ((Button)event.getTarget()).getId().substring(3);
            oldValue = game.getPlayer().getMoney().getValue();
            newValue = oldValue - Integer.parseInt(money);
            if(newValue >= 0){
                game.getPlayer().setMoney(newValue);
                game.setMoneyInGame(Integer.parseInt(money));
            }else{
                System.out.println("not enough money!");
            }
    }
    
    @FXML
    private void doDouble(ActionEvent event){
        game.setMove(((Button)event.getTarget()).getId());
    }
    
    @FXML
    private void doPass(ActionEvent event){
        game.setMove(((Button)event.getTarget()).getId());
    }
   
    @FXML
    private void doSplit(ActionEvent event){
        game.setMove(((Button)event.getTarget()).getId());
    }
    
    
    //Listener Methods to disable and able Buttons
    public void setListener(){
          btnArray.forEach((btn) -> {
            btn.visibleProperty().bindBidirectional(game.getPlayer().getTurn());
          });
          labelBankBalance.textProperty().bind(game.getPlayer().getMoney().asString());
    }

    
    public void setBackground(){
        Image image = new Image("/black/jack/gui/blackjacktable.png");
        background.setImage(image);
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    void setMainApp(BlackJack manager){
        this.manager = manager;
    }
    
    public GameCr getMe(){
        return this;
    }
    
}
