/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack.src;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
    private ArrayList<Person> person = new ArrayList<>();
    private Cardset cardset;
    private boolean endOfGame;
    private BlackJack manager;
    //BooleanProperty a = new SimpleBooleanProperty(false);
    
    @FXML
    ImageView background;
    
    @FXML 
    Button btnSplit,btnDouble,btnSetMoney,btnTakeACard,btnPass;


    
    /*Constructor*/

    public GameCr() {
        person.add(new Croupier());
        person.add(new Player());
 
    }
    
    

    /*Getter & Setter*/
    
    public ArrayList<Person> getPerson(){    
        return person;
        
    }

    public void setPerson(ArrayList<Person> person) {
        this.person = person;
    }

    public Cardset getCardset() {
        return cardset;
    }
    
    public void setCardset(Cardset cardset) {    
        this.cardset = cardset;
    }

    public boolean isEndOfGame() {
        return endOfGame;
    }

    public void setEndOfGame(boolean endOfGame) {
        this.endOfGame = endOfGame;

    }
    
    
    /*Methods*/
    public void startAGame() {
        cardset = new Cardset();
        for(Person p : person){
            p.getCard();
        }
        person.get(1).setTurn(true);
        setListener();
    }
    
    

    public void setBackground(){
        Image image = new Image("/black/jack/gui/blackjacktable.png");
        background.setImage(image);
    }
    
    @FXML 
    private void takeACard(ActionEvent event){
             
    }
    
    @FXML
    private void setMoney(ActionEvent event){
        
    }
    
    @FXML
    private void doDouble(ActionEvent event){
        
    }
    
    @FXML
    private void doPass(ActionEvent event){
        
    }
   
    @FXML
    private void doSplit(ActionEvent event){
        
    }
    
    
    //Listener Methods to disable and able Buttons
    public void setListener(){
        btnTakeACard.visibleProperty().bindBidirectional(person.get(1).getTurn());
        btnSplit.visibleProperty().bindBidirectional(person.get(1).getTurn());
        btnDouble.visibleProperty().bindBidirectional(person.get(1).getTurn());
        btnSetMoney.visibleProperty().bindBidirectional(person.get(1).getTurn());
        btnTakeACard.visibleProperty().bindBidirectional(person.get(1).getTurn());
        btnPass.visibleProperty().bindBidirectional(person.get(1).getTurn());
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
