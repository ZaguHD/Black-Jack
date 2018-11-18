/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack.src;

import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

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
    private int cardIndex = 0;
    private int x = 309;
    private int y = 390;

    
    @FXML
    ImageView background;
    
    @FXML 
    Button btnSplit,btnDouble,btnTakeACard,btnPass, okBtn;
    
    @FXML
    Button btn500,btn100,btn25,btn5,btn1;
    
    @FXML
    Label labelBankBalance, resultLabel;
    
    @FXML
    ArrayList<Button> btnArray = new ArrayList<>();
    
    @FXML 
    AnchorPane playPane;
    
    @FXML
    Pane resultPane;

    
    
    private ArrayList<Pane> paneList = new ArrayList();
    
//    @FXML
//    Pane cardNode;
//    @FXML
//    ImageView frontCard, backCard;
    
    
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
    private void waitForAnserw(){
      Thread waitThread = new Thread(){
          @Override
           public void run(){
            boolean splitted = false;
            int oldSize = 0;
            while(game.isAlive()){
                if(game.getPlayer().getCard()[0].getCards().size() == 2 && oldSize !=2 && !game.getPlayer().isSplitted()){
                    Platform.runLater(()->{
                        takeCard(2);   
                    });   
                    oldSize=2;
                }else if(game.getPlayer().isSplitted() && splitted == false){
                    Platform.runLater(()->{
                        makeSplit();   
                    });                
                    splitted = true;
                }else if(game.getPlayer().getCard()[0].getCards().size() == oldSize+1){
                    Platform.runLater(()->{
                        takeCard(1);   
                    });  
                    oldSize++;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameCr.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Platform.runLater(()->{
                 resultPane.setDisable(false);
                 resultPane.setOpacity(40);
                switch (game.getWinner()) {
                    case 0:
                        resultLabel.setText("Player lost");
                        break;
                    case 1:
                        resultLabel.setText("Player won");
                        break;
                    default:
                        resultLabel.setText("draw");
                        break;
                }
                  });
    

         }
      };
      waitThread.start();
     
    }
    
    @FXML
    private void playAgain(ActionEvent event){
        paneList.forEach((pane) -> {
            playPane.getChildren().remove(pane);                 

        });
       paneList = new ArrayList<>();
        cardIndex = 0;
        x = 309;
        y = 390;
        resultPane.setDisable(true);
        resultPane.setOpacity(0);
    }
    
    @FXML 
    private void takeACard(ActionEvent event){
        game.setMove(((Button)event.getTarget()).getId());
        //takeCard(1);

    }
    
    @FXML
    private void setMoney(ActionEvent event){
        if(!game.isAlive()){ //When game isn't started 
            startAGame(); //start a game
            waitForAnserw();
            String money = ((Button)event.getTarget()).getId().substring(3); //Numbers from Button id (Example: btn500 -> 500)
            oldValue = game.getPlayer().getMoney().getValue(); 
            newValue = oldValue - Integer.parseInt(money);
            if(newValue >= 0){ //If user has more than or equals 0 
                game.getPlayer().setMoney(newValue); 
                game.setMoneyInHand1(Integer.parseInt(money));
                //takeCard(2);
            }else{
                System.out.println("not enough money!");
            }
        }
    }
    private void takeCard(int count){
            paneList.add(new Pane());
            ImageView frontCard = new ImageView();
            ImageView backCard = new ImageView();              

            playPane.getChildren().add(paneList.get(cardIndex));
            paneList.get(cardIndex).setLayoutX(307);
            paneList.get(cardIndex).setLayoutY(55);
            paneList.get(cardIndex).getChildren().addAll(frontCard,backCard);

            backCard.setImage(game.getPlayer().getCard()[0].getCards().get(cardIndex).getImage());
            Image image = new Image(getClass().getResource("/black/jack/gui/card_Pictures/cardBack.png").toString());

            frontCard.setImage(image);
            TranslateTransition move = new TranslateTransition(Duration.millis(500), paneList.get(cardIndex));
            move.setToX(x);
            move.setToY(y);
            x += 12;
            y -= 10;
           
            ScaleTransition stHideFront = new ScaleTransition(Duration.millis(250), frontCard);
            stHideFront.setFromX(1);
            stHideFront.setToX(0);

            backCard.setScaleX(0);

            ScaleTransition stShowBack = new ScaleTransition(Duration.millis(250), backCard);
            stShowBack.setFromX(0);
            stShowBack.setToX(1);

            stHideFront.setOnFinished((ActionEvent t) -> {
                stShowBack.play();
            });
            if(count == 2){
                move.setOnFinished(((event) -> {
                    takeCard(1);
                }));
            }
            cardIndex ++;
            move.play();
            stHideFront.play();        
    }
    private void makeSplit(){
        TranslateTransition move1 = new TranslateTransition(Duration.millis(100), paneList.get(0));
           move1.setToX(paneList.get(0).getLayoutX()-60);
           
        TranslateTransition move2= new TranslateTransition(Duration.millis(100), paneList.get(1));
           move2.setToX(paneList.get(1).getLayoutX()+60);
           move2.setToY(y+20);
           
        move1.play();
        move2.play();
     
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
