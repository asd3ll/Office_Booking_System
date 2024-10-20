/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package toimistolinko;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hanna
 */
public class PalvelutFXMLController implements Initializable {

    private Palvelu m_palvelu = new Palvelu();
    
    @FXML
    private TextField txtPalvelunNimi;
    @FXML
    private TextField txtPalvelunKuvaus;
    @FXML
    private TextField txtPalvelunVuokra;
    @FXML
    private TextField txtPalvelunALV;
    @FXML
    private TextField txtPalvelunID;
    @FXML
    private TextField txtPalvelunToimipiste;
    @FXML
    private Button btnHaePalvelut;
    @FXML
    private Button btnLisaaPalvelut;
    @FXML
    private Button btnMuokkaaPalvelut;
    @FXML
    private Button btnPoistaPalvelut;
    @FXML
    private Button btnPalaaPaavalikkoon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void btn_PalaaPaavalikkoon(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ToimistoLinkoFXML.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void btn_haePalvelut(ActionEvent event) {
        haeTiedot();
    }

    @FXML
    private void btn_lisaaPalvelut(ActionEvent event) {
        lisaaTiedot();
    }

    @FXML
    private void btn_muokkaaPalvelut(ActionEvent event) {
        muutaTiedot();
    }

    @FXML
    private void btn_poistaPalvelut(ActionEvent event) {
        poistaTiedot();
    }
    
    public void haeTiedot() {

       m_palvelu = null;
       
       try {
       m_palvelu = Tietokanta.haePalvelu(Integer.parseInt(txtPalvelunID.getText()));
       } catch (Exception e) {
       
       Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setTitle("Palvelun tietojen hakeminen");
       alert.setHeaderText("Virhe");
       alert.setContentText("Palvelua ei loydy.");
       alert.showAndWait();
       }

       if(m_palvelu.getNimi() == null){
           txtPalvelunID.setText("");
           txtPalvelunNimi.setText("");
           txtPalvelunKuvaus.setText("");
           txtPalvelunVuokra.setText("");
           txtPalvelunALV.setText("");
           txtPalvelunToimipiste.setText("");
           
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Palvelun tietojen hakeminen");
           alert.setHeaderText("Virhe");
           alert.setContentText("Palvelua ei loydy.");
           alert.showAndWait();
       }
       
       else{
           txtPalvelunID.setText(String.valueOf(m_palvelu.getPaId()));
           txtPalvelunNimi.setText(String.valueOf(m_palvelu.getNimi()));
           txtPalvelunKuvaus.setText(String.valueOf(m_palvelu.getKuvaus()));
           txtPalvelunVuokra.setText(String.valueOf(m_palvelu.getVuokra()));
           txtPalvelunALV.setText(String.valueOf(m_palvelu.getAlv()));
           txtPalvelunToimipiste.setText(String.valueOf(m_palvelu.getToimipiste()));
        }   
    }
    
    public void lisaaTiedot(){
        boolean palvelu_lisatty = true;
        m_palvelu = null;
        
        try {
        m_palvelu = Tietokanta.haePalvelu(Integer.parseInt(txtPalvelunID.getText()));
        } catch (Exception e) {
        
        palvelu_lisatty = false;
        e.printStackTrace();
        
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Palvelun tietojen lisaaminen");
          alert.setHeaderText("Tietokantavirhe");
          alert.setContentText("Palvelun tietojen lisääminen ei onnistu.");
          alert.showAndWait();
    }
        if(m_palvelu.getNimi()!=null){
           
           palvelu_lisatty = false;
            
           txtPalvelunID.setText(String.valueOf(m_palvelu.getPaId()));
           txtPalvelunNimi.setText(m_palvelu.getNimi());
           txtPalvelunKuvaus.setText(m_palvelu.getKuvaus());
           txtPalvelunVuokra.setText(String.valueOf(m_palvelu.getVuokra()));
           txtPalvelunALV.setText(String.valueOf(m_palvelu.getAlv()));
           txtPalvelunToimipiste.setText(m_palvelu.getToimipiste());
           
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Palvelun tietojen lisaaminen");
           alert.setHeaderText("Virhe");
           alert.setContentText("Palvelu on jo olemassa.");
           alert.showAndWait();
        } 
        
        else{
            m_palvelu.setPaId(Integer.parseInt(txtPalvelunID.getText()));
            m_palvelu.setNimi(txtPalvelunNimi.getText());
            m_palvelu.setKuvaus(txtPalvelunKuvaus.getText());
            m_palvelu.setVuokra(Double.parseDouble(txtPalvelunVuokra.getText()));
            m_palvelu.setAlv(Integer.parseInt(txtPalvelunALV.getText()));
            m_palvelu.setToimipiste(txtPalvelunToimipiste.getText());
            
             try {

                Tietokanta.lisaaPalvelu(m_palvelu);
            } catch (Exception e) {

                palvelu_lisatty = false;

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Palvelun tietojen lisaaminen");
                alert.setHeaderText("Tietokantavirhe");
                alert.setContentText("Palvelun tietojen lisääminen ei onnistu.");
                alert.showAndWait();

                e.printStackTrace();
            } finally {
                if (palvelu_lisatty == true) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Palvelun tietojen lisaaminen");
                    alert.setHeaderText("Toiminto ok.");
                    alert.setContentText("Palvelun tiedot lisatty tietokantaan.");
                    alert.showAndWait();
                }
            }
        }
    }
    
    public void muutaTiedot(){
        boolean palvelu_muutettu = true;
        
        m_palvelu.setPaId(Integer.parseInt(txtPalvelunID.getText()));
        m_palvelu.setNimi(txtPalvelunNimi.getText());
        m_palvelu.setKuvaus(txtPalvelunKuvaus.getText());
        m_palvelu.setVuokra(Double.parseDouble(txtPalvelunVuokra.getText()));
        m_palvelu.setAlv(Integer.parseInt(txtPalvelunALV.getText()));
        m_palvelu.setToimipiste(txtPalvelunToimipiste.getText());
        
        try {

                Tietokanta.muokkaaPalvelu(m_palvelu);
            } catch (Exception e) {
     
        palvelu_muutettu = false;
           
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Palvelun tietojen muuttaminen");
        alert.setHeaderText("Tietokantavirhe");
        alert.setContentText("Palvelun tietojen muuttaminen ei onnistu.");
        alert.showAndWait();
        
        e.printStackTrace();
            } finally {
                if (palvelu_muutettu == true) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Palvelun tietojen muuttaminen");
                    alert.setHeaderText("Toiminto ok.");
                    alert.setContentText("Palvelun tiedot muutettu.");
                    alert.showAndWait();
                }
             }
         }
    
    public void poistaTiedot(){
        m_palvelu = null;
        boolean palvelu_poistettu = false;
        
        try {
        m_palvelu = Tietokanta.haePalvelu(Integer.parseInt(txtPalvelunID.getText()));
        } catch (Exception e) {
        
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Palvelun tietojen poistaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Palvelun tietojen poistaminen ei onnistu.");
            alert.showAndWait();
        
         e.printStackTrace();
           }
           
        if(m_palvelu.getNimi()==null){
           txtPalvelunNimi.setText("");
           txtPalvelunKuvaus.setText("");
           txtPalvelunVuokra.setText("");
           txtPalvelunALV.setText("");
           txtPalvelunToimipiste.setText("");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Palvelun tietojen poistaminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Palvelua ei loydy.");
            alert.showAndWait();
            
            return;
        }
        
        else{
           txtPalvelunNimi.setText(m_palvelu.getNimi());
           txtPalvelunKuvaus.setText(m_palvelu.getKuvaus());
           txtPalvelunVuokra.setText(String.valueOf(m_palvelu.getVuokra()));
           txtPalvelunALV.setText(String.valueOf(m_palvelu.getAlv()));
           txtPalvelunToimipiste.setText(m_palvelu.getToimipiste());
        }
        
           try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Palvelun tietojen poisto");
            alert.setHeaderText("Vahvista");
            alert.setContentText("Haluatko todella poistaa palvelun tiedot?");

            Optional<ButtonType> vastaus = alert.showAndWait();

            if (vastaus.get() == ButtonType.OK) {
                Tietokanta.poistaPalvelu(Integer.parseInt(txtPalvelunID.getText()));
                palvelu_poistettu = true;
            }
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Palvelun tietojen poisto");
            alert.setHeaderText("Tulos:");
            alert.setContentText("Palvelun tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        } finally {
            if (palvelu_poistettu == true) {
                txtPalvelunID.setText("");
                txtPalvelunNimi.setText("");
                txtPalvelunKuvaus.setText("");
                txtPalvelunVuokra.setText("");
                txtPalvelunALV.setText("");
                txtPalvelunToimipiste.setText("");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Palvelun tietojen poisto");
                alert.setHeaderText("Tulos:");
                alert.setContentText("Palvelun tiedot poistettu tietokannasta.");
                alert.showAndWait();

                m_palvelu = null;
            }
        }
    }

    @FXML
    private void ntnHaeOnMouseExited(MouseEvent event) {
        btnHaePalvelut.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnHaeOnMouseEntered(MouseEvent event) {
        btnHaePalvelut.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void btnLisaaOnMouseExited(MouseEvent event) {
        btnLisaaPalvelut.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnLisaaOnMouseEntered(MouseEvent event) {
        btnLisaaPalvelut.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void btnMuokkaaOnMouseExited(MouseEvent event) {
        btnMuokkaaPalvelut.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnMuokkaaOnMouseEntered(MouseEvent event) {
        btnMuokkaaPalvelut.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void btnPoistaOnMouseExited(MouseEvent event) {
        btnPoistaPalvelut.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnPoistaOnMouseEntered(MouseEvent event) {
        btnPoistaPalvelut.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void btnPalaaOnMouseExited(MouseEvent event) {
        btnPalaaPaavalikkoon.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnPalaaOnMouseEntered(MouseEvent event) {
        btnPalaaPaavalikkoon.setStyle("-fx-background-color: #1e2e5a;");
    }
}
    

