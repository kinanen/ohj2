package HT2022;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author otsokinanen
 * @version 2.8.2022
 * Pääohjelma, käynnistää ohjelman. 
 */
public class Ht2022Main extends Application {
    
    
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("Ht2022GUIView.fxml"));
            final Pane root = ldr.load();
            //final Ht2022GUIController ht2022Ctrl = (Ht2022GUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("ht2022.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("ht2022");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {      

        //Ht2022GUIController.Lue();
        
        launch(args);
    }


    
}