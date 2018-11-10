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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    Game game;
    
    @FXML
    ImageView background;
    
    @FXML 
    Button btnSplit,btnDouble,btnSetMoney,btnTakeACard,btnPass;
    
    @FXML
    ArrayList<Button> btnArray = new ArrayList<>();



    
    /*Methods*/
    public void startAGame() {
        game = new Game();
        game.start();
        btnArray.add(btnSplit);
        btnArray.add(btnDouble);
        btnArray.add(btnSetMoney);
        btnArray.add(btnTakeACard);
        btnArray.add(btnPass);
    }
 
    
    @FXML 
    private void takeACard(ActionEvent event){
        game.setMove(((Button)event.getTarget()).getId());
    }
    
    @FXML
    private void setMoney(ActionEvent event){
        game.setMove(((Button)event.getTarget()).getId());
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
            btn.visibleProperty().bindBidirectional(game.getPlayer().get(0).getTurn());
          });
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
