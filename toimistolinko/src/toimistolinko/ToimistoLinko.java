package toimistolinko;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ToimistoLinko extends Application {
    // Pääohjelma.
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ToimistoLinkoFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("ToimistoLinko | Toimistotilojen varausjarjestelma");
        stage.setScene(scene);
        stage.show();
        
        /*
        
        // Tietokannan ja taulujen luonti, testaus
        Tietokanta.luoTietokanta();
        Tietokanta.luoTaulut();
        Tietokanta.lisaaTestidata();
        
        */
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
