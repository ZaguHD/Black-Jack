/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack.src;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

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

    
    /*Constructor*/

    

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
