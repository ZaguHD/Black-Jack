/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.jack.src;

import java.io.IOException;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author emazi
 */
public class BlackJack extends Application {
    private Stage gameStage;
    private GameCr gameCr = new GameCr();
    private Scene sceneGameCr;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.gameStage = new Stage();
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/FXMLGame.fxml"));
        Parent root = loader.load();
        
        GameCr controller = loader.getController();
        controller.setMainApp(this);
        gameCr = controller.getMe(); //Klasse bekannt machen
        
        this.sceneGameCr = new Scene(root);
        this.gameStage.setTitle("BlackJack");
        this.gameStage.setScene(sceneGameCr);
        this.gameStage.setResizable(false); // Benutzer kann die Grösse des Fensters nicht ändern
        /*Need to be change */
        gameCr.resultPane.setDisable(true);
        gameCr.resultPane.setOpacity(0);
        /* */
        this.gameStage.show();      
                gameCr.setBackground();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
