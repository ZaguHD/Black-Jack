/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack.src;

import com.jfoenix.controls.JFXButton;
import java.awt.Color;
import java.net.URL;
import java.nio.file.WatchKey;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.Property;
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
    private int x = 475;
    private int y = 318;
    private int xHand2;
    private int yHand2;
    private int xCroupier = 345;
    private int yCroupier = 115;
    private Timeline currentHandAnimation;

    @FXML
    ImageView background;
    
    @FXML 
    Button btnSplit,btnDouble,btnTakeACard,btnPass, okBtn;
    
    @FXML
    Button btn500,btn100,btn25,btn5,btn1;
    
    @FXML
    Label labelBankBalance, pointsHand1, pointsHand2, pointsHand, pointsCroupier, resultHand, resultHand1, resultHand2;
    
    @FXML
    ArrayList<Button> btnArray = new ArrayList<>();
    
    @FXML
    ArrayList<Label> resultArray = new ArrayList<>();
            
    @FXML 
    AnchorPane playPane;
    
    @FXML
    Pane resultPane,menuBar;
    
    @FXML
    ImageView  cardSet;
    
    @FXML
    

    public synchronized int getX() {
        return x;
    }

    public synchronized void setX(int x) {
        this.x = x;
    }

    public synchronized int getY() {
        return y;
    }

    public synchronized void setY(int y) {
        this.y = y;
    }

    public synchronized int getxHand2() {
        return xHand2;
    }

    public synchronized void setxHand2(int xHand2) {
        this.xHand2 = xHand2;
    }

    public synchronized int getyHand2() {
        return yHand2;
    }

    public synchronized void setyHand2(int yHand2) {
        this.yHand2 = yHand2;
    }

    
    
    private ArrayList<Pane> paneList = new ArrayList();
    private ArrayList<Pane> paneListCroupier = new ArrayList();
    
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
            resultArray.add(resultHand);
            resultArray.add(resultHand1);
            resultArray.add(resultHand2);
            cardset = new Cardset();
            currentHand(pointsHand);
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

            while(game.isAlive()||paneListCroupier.size() != (game.getCroupier().getCard()[0].getCards().size())){
                  if(paneList.size() != (game.getPlayer().getCard()[0].getCards().size() + game.getPlayer().getCard()[1].getCards().size())){
                      Platform.runLater(()->{
                        takeCard(game.getPlayer().getCard()[1].getHand());
                    }); 
                  }else if(paneListCroupier.size() != (game.getCroupier().getCard()[0].getCards().size())){
                      Platform.runLater(()->{
                         takeCardCroupier();
                      }); 
                  }
                  //System.out.println("hello: "+game.getCroupier().getCard()[0].getCards().size());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameCr.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameCr.class.getName()).log(Level.SEVERE, null, ex);
            }
            Platform.runLater(()->{
                resultPane.setDisable(false);
                    resultPane.setOpacity(100);
                    setResultLabel();
                });
    

         }
      };
      waitThread.start();
      
     
    }
    
    private void setResultLabel(){
        int count = 1;
        int index = 0;
        int hand = 0;
        if(game.getPlayer().isSplitted()){
            count = 3;
            index = 1;
        }
        while(index < count){
            resultArray.get(index).toFront();
            resultArray.get(index).getStyleClass().clear();
            switch (game.getWinner()[hand++]) {
                case 1:
                    resultArray.get(index).setText("+"+game.getMoneyInHand1().getValue()*2);
                    resultArray.get(index).getStyleClass().add("wonLabel");
                    break;
                case 2:
                    resultArray.get(index).getStyleClass().add("drawLabel");
                    resultArray.get(index).setText("Push");
                    break;
                default:
                    resultArray.get(index).getStyleClass().add("lostLabel");
                    resultArray.get(index).setText("-"+game.getMoneyInHand1().getValue());
                    break;
            }
            resultArray.get(index).setVisible(true);   
            index++;
            }

    }
    
    @FXML
    private void playAgain(ActionEvent event){
        paneList.forEach((pane) -> {
            playPane.getChildren().remove(pane);                 

        });
        paneListCroupier.forEach((pane) -> {
            playPane.getChildren().remove(pane);                 

        });
       paneList = new ArrayList<>();
       paneListCroupier = new ArrayList<>();
       pointsHand.setVisible(false);
       pointsHand1.setVisible(false);
       pointsHand2.setVisible(false);
       pointsCroupier.setVisible(false);
       menuBar.setDisable(false);
       resultArray.forEach((label) -> {
           label.setVisible(false);
        });
       
        cardIndex = 0;
        System.out.println("hello");
        setX(475);
        setY(318);
        xCroupier = 345;
        yCroupier = 115;
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
        String money = ((Button)event.getTarget()).getText(); //Numbers from Button id (Example: btn500 -> 500)
        if(!game.isAlive() && Integer.parseInt(money) <= game.getPlayer().getMoney().getValue()){ //When game isn't started 
            pointsCroupier.setVisible(true);
            startAGame(); //start a game
            waitForAnserw();
            oldValue = game.getPlayer().getMoney().getValue(); 
            newValue = oldValue - Integer.parseInt(money);
            game.getPlayer().setMoney(newValue); 
            game.setMoneyInHand1(Integer.parseInt(money));

        }
    }

    private void takeCardCroupier(){
        paneListCroupier.add(new Pane());        
        ImageView frontCard = new ImageView();
        ImageView backCard = new ImageView(); 
        System.out.println("");
        playPane.getChildren().add(paneListCroupier.get(paneListCroupier.size()-1));
        paneListCroupier.get(paneListCroupier.size()-1).setLayoutX(cardSet.getLayoutX());
        paneListCroupier.get(paneListCroupier.size()-1).setLayoutY(cardSet.getLayoutY());
        paneListCroupier.get(paneListCroupier.size()-1).getChildren().addAll(backCard,frontCard);
        
        backCard.setImage(game.getCroupier().getLastCardImage(0));
        Image image = new Image(getClass().getResource("/black/jack/gui/card_Pictures/cardBack.png").toString());

        frontCard.setImage(image);
        TranslateTransition move = new TranslateTransition(Duration.millis(400), paneListCroupier.get(paneListCroupier.size()-1));

        move.setToX(xCroupier);
        //move.setToY(yCroupier);               

        xCroupier += 100;

        ScaleTransition stHideFront = new ScaleTransition(Duration.millis(200), frontCard);
        stHideFront.setFromX(1);
        stHideFront.setToX(0);

        backCard.setScaleX(0);

        ScaleTransition stShowBack = new ScaleTransition(Duration.millis(200), backCard);
        stShowBack.setFromX(0);
        stShowBack.setToX(1);

        stHideFront.setOnFinished((ActionEvent t) -> {
            stShowBack.play();
        });

        move.play();
        stHideFront.play();
    }
         
    private void takeCard(int hand){
        paneList.add(new Pane());
        ImageView frontCard = new ImageView();
        ImageView backCard = new ImageView();              

        playPane.getChildren().add(paneList.get(cardIndex));
        System.out.println("size :"+ playPane.getChildren().size());
        paneList.get(cardIndex).setLayoutX(cardSet.getLayoutX());
        paneList.get(cardIndex).setLayoutY(cardSet.getLayoutY());
        paneList.get(cardIndex).getChildren().addAll(frontCard,backCard);
        backCard.setImage(game.getPlayer().getLastCardImage(hand));
        Image image = new Image(getClass().getResource("/black/jack/gui/card_Pictures/cardBack.png").toString());

        frontCard.setImage(image);
        TranslateTransition move = new TranslateTransition(Duration.millis(250), paneList.get(cardIndex));
        System.out.println("STAND IN GAMER CR: "+game.getPlayer().getCard()[1].isStand());
        if(game.getPlayer().getCard()[1].isStand() && game.getPlayer().getCard()[1].getCards().size()==2){
             setX(xHand2);
             setY(318-10); 
             currentHandAnimation.stop();
             currentHand(pointsHand2);            
        }

        move.setToX(x);
        move.setToY(y);               

        setX(getX()+12);
        setY(getY()-10);

        ScaleTransition stHideFront = new ScaleTransition(Duration.millis(125), frontCard);
        stHideFront.setFromX(1);
        stHideFront.setToX(0);

        backCard.setScaleX(0);

        ScaleTransition stShowBack = new ScaleTransition(Duration.millis(125), backCard);
        stShowBack.setFromX(0);
        stShowBack.setToX(1);

        stHideFront.setOnFinished((ActionEvent t) -> {
            stShowBack.play();
        });

        cardIndex ++;
        move.play();
        stHideFront.play(); 
      
    }
    private void makeSplit(){
        TranslateTransition move1 = new TranslateTransition(Duration.millis(100), paneList.get(0));
           move1.setToX(getX()+20);
           
        TranslateTransition move2= new TranslateTransition(Duration.millis(100), paneList.get(1));
           move2.setToX(getX()-130);
           move2.setToY(getY()+20);
           
        move1.play();
        move2.play();
        move2.setOnFinished(((event) -> {  
           y = 318-10;
           x = (int)  move1.getToX() + 12;
           xHand2 = (int) move2.getToX() + 12;
           yHand2 = (int) move2.getToY() - 10;

        }));
        currentHandAnimation.stop();
        currentHand(pointsHand1);
     
    }
    
    private void currentHand(Label lb){
    currentHandAnimation = new Timeline(
        new KeyFrame(Duration.seconds(1.0), evt -> lb.getStyleClass().clear()),
        new KeyFrame(Duration.seconds(1.0), evt -> lb.getStyleClass().add("notCurrentPoints")),  
        new KeyFrame(Duration.seconds(2.0), evt -> lb.getStyleClass().clear()),
        new KeyFrame(Duration.seconds(2.0), evt -> lb.getStyleClass().add("currentPoints")) 
    );
    currentHandAnimation.setCycleCount(Animation.INDEFINITE);
    currentHandAnimation.play();  
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
        if(game.getPlayer().isSplitPossible()){
            makeSplit();
        }

    }
    
    
    //Listener Methods to disable and able Buttons
    public void setListener(){
        btnArray.forEach((btn) -> {
          btn.visibleProperty().bindBidirectional(game.getPlayer().getTurn());
        });
        labelBankBalance.textProperty().bind(game.getPlayer().getMoney().asString());
        menuBar.disableProperty().bindBidirectional(game.getPlayer().getFinish());
        pointsHand1.visibleProperty().bindBidirectional(game.getPlayer().getSplit());
        pointsHand2.visibleProperty().bindBidirectional(game.getPlayer().getSplit());
        pointsHand.visibleProperty().bindBidirectional(game.getPlayer().getNoSplit());

        pointsHand.textProperty().bind(game.getPlayer().getCard()[0].getGuiPoints().asString());
        pointsCroupier.textProperty().bind(game.getCroupier().getCard()[0].getGuiPoints().asString());
        pointsHand1.textProperty().bindBidirectional(game.getPlayer().getCard()[0].getGuiPoints(),NumberFormat.getNumberInstance());
        pointsHand2.textProperty().bindBidirectional(game.getPlayer().getCard()[1].getGuiPoints(),NumberFormat.getNumberInstance());
    }
    
//    public void setBackground(){
//        Image image = new Image("/black/jack/gui/blackjacktable.png");
//        background.setImage(image);
//    }
       
    
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
