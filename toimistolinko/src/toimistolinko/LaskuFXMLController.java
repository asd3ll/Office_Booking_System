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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author R01
 */
public class LaskuFXMLController implements Initializable {
    
    private Lasku m_lasku = new Lasku();

    @FXML
    private TextField txtLaskunumero;
    @FXML
    private TextField txtLoppusumma;
    @FXML
    private TextField txtVastaanottaja;
    @FXML
    private TextField txtLahiosoite;
    @FXML
    private TextField txtPostiNro;
    @FXML
    private TextField txtPostitoimipaikka;
    @FXML
    private TextField txtSahkopostiosoite;
    @FXML
    private TextField txtViitenumero;
    @FXML
    private TextField txtErapaiva;
    @FXML
    private TextField txtPvm;
    @FXML
    private TextField txtMaksuehto;
    @FXML
    private TextField txtKustannuspaikka;
    @FXML
    private TextField txtTila;
    @FXML
    private TextField txtAsiakasnumero;
    @FXML
    private TextField txtVarausnumero;
    @FXML
    private RadioButton radiobuttonPaperilasku;
    @FXML
    private ToggleGroup laskutustapa;
    @FXML
    private RadioButton radiobuttonSahkoinenLasku;
    @FXML
    private Button btnHaeLasku;
    @FXML
    private Button btnLisaaLasku;
    @FXML
    private Button btnMuokkaaLaskua;
    @FXML
    private Button btnPoistaLasku;
    @FXML
    private Button btnPalaaPaavalikkoon;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }   
    
    @FXML
    private void btn_haeLasku(ActionEvent event) {
        haeTiedot();
    }

    @FXML
    private void btn_lisaaLasku(ActionEvent event) {
        lisaaTiedot();
    }

    @FXML
    private void btn_muokkaaLaskua(ActionEvent event) {
        muutaTiedot();
    }

    @FXML
    private void btn_poistaLasku(ActionEvent event) {
        poistaTiedot();
    }

    @FXML
    private void btn_PalaaPaavalikkoon(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ToimistoLinkoFXML.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    
      
    public void haeTiedot() {

        m_lasku = null;

        try {
            m_lasku = Tietokanta.haeLasku(Integer.parseInt(txtLaskunumero.getText()));
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Laskun tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Laskua ei loydy.");
            alert.showAndWait();

        }
        if (m_lasku.getVastaanottaja()== null) {

            txtLaskunumero.setText("");
            txtLoppusumma.setText("");
            txtVastaanottaja.setText("");
            txtLahiosoite.setText("");
            txtPostiNro.setText("");
            txtPostitoimipaikka.setText("");
            txtSahkopostiosoite.setText("");
            txtViitenumero.setText("");
            txtErapaiva.setText("");
            radiobuttonPaperilasku.setSelected(false);
            radiobuttonPaperilasku.setSelected(false);
            txtPvm.setText("");
            txtMaksuehto.setText("");
            txtKustannuspaikka.setText("");
            txtTila.setText("");
            txtAsiakasnumero.setText("");
            txtVarausnumero.setText("");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Laskun tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Laskua ei loydy.");
            alert.showAndWait();

        } else {

            txtLaskunumero.setText(String.valueOf(m_lasku.getLaskuNro()));
            txtLoppusumma.setText(String.valueOf(m_lasku.getLoppuSumma()));
            txtVastaanottaja.setText(m_lasku.getVastaanottaja());
            txtLahiosoite.setText(m_lasku.getLahiosoite());
            txtPostiNro.setText(m_lasku.getPostinumero());
            txtPostitoimipaikka.setText(m_lasku.getPostitoimipaikka());
            txtSahkopostiosoite.setText(m_lasku.getEmail());
            txtViitenumero.setText(m_lasku.getViite());
            txtErapaiva.setText(m_lasku.getEraPaiva());
            
            switch (m_lasku.getPaperilasku()) {
                case 1:
                    radiobuttonPaperilasku.setSelected(true);
                    break;
                default:
                    radiobuttonSahkoinenLasku.setSelected(true);
                    break;
            }
            
            txtPvm.setText(m_lasku.getPvm());
            txtMaksuehto.setText(m_lasku.getMaksuehto());
            txtKustannuspaikka.setText(m_lasku.getKustannuspaikka());
            txtTila.setText(String.valueOf(m_lasku.getTila()));
            txtAsiakasnumero.setText(String.valueOf(m_lasku.getAsiakasNro()));
            txtVarausnumero.setText(String.valueOf(m_lasku.getVarausNro()));
        }

    }
    
    public void lisaaTiedot() {

        boolean lasku_lisatty = true;
        m_lasku = null;
        try {
            m_lasku = Tietokanta.haeLasku(Integer.parseInt(txtLaskunumero.getText()));
        } catch (Exception e) {

            lasku_lisatty = false;
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Laskun tietojen lisaaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Laskun tietojen lisääminen ei onnistu.");
            alert.showAndWait();
        }
        if (m_lasku.getVastaanottaja()!= null) {

            lasku_lisatty = false;
            txtLaskunumero.setText(String.valueOf(m_lasku.getLaskuNro()));
            txtLoppusumma.setText(String.valueOf(m_lasku.getLoppuSumma()));
            txtVastaanottaja.setText(m_lasku.getVastaanottaja());
            txtLahiosoite.setText(m_lasku.getLahiosoite());
            txtPostiNro.setText(m_lasku.getPostinumero());
            txtPostitoimipaikka.setText(m_lasku.getPostitoimipaikka());
            txtSahkopostiosoite.setText(m_lasku.getEmail());
            txtViitenumero.setText(m_lasku.getViite());
            txtErapaiva.setText(m_lasku.getEraPaiva());
            
            switch (m_lasku.getPaperilasku()) {
                case 1:
                    radiobuttonPaperilasku.setSelected(true);
                    break;
                default:
                    radiobuttonSahkoinenLasku.setSelected(true);
                    break;
            }
            
            txtPvm.setText(m_lasku.getPvm());
            txtMaksuehto.setText(m_lasku.getMaksuehto());
            txtKustannuspaikka.setText(m_lasku.getKustannuspaikka());
            txtTila.setText(String.valueOf(m_lasku.getTila()));
            txtAsiakasnumero.setText(String.valueOf(m_lasku.getAsiakasNro()));
            txtVarausnumero.setText(String.valueOf(m_lasku.getVarausNro()));

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Laskun tietojen lisaaminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Lasku on jo olemassa.");
            alert.showAndWait();

        } else {

            m_lasku.setLaskuNro(Integer.parseInt(txtLaskunumero.getText()));
            m_lasku.setLoppusumma(Double.parseDouble(txtLoppusumma.getText()));
            m_lasku.setVastaanottaja(txtVastaanottaja.getText());
            m_lasku.setLahiosoite(txtLahiosoite.getText());
            m_lasku.setPostinumero(txtPostiNro.getText());
            m_lasku.setPostitoimipaikka(txtPostitoimipaikka.getText());
            m_lasku.setEmail(txtSahkopostiosoite.getText());
            m_lasku.setViite(txtViitenumero.getText());
            m_lasku.setErapaiva(txtErapaiva.getText());
            
            if (radiobuttonPaperilasku.isSelected()) {
                m_lasku.setPaperilasku(1);
            } else {
                m_lasku.setPaperilasku(0);
            }
            
            m_lasku.setPvm(txtPvm.getText());
            m_lasku.setMaksuehto(txtMaksuehto.getText());
            m_lasku.setKustannuspaikka(txtKustannuspaikka.getText());
            m_lasku.setTila(Integer.parseInt(txtTila.getText()));
            m_lasku.setAsiakasNro(Integer.parseInt(txtAsiakasnumero.getText()));
            m_lasku.setVarausNro(Integer.parseInt(txtVarausnumero.getText()));
            try {

                Tietokanta.lisaaLasku(m_lasku);
            } catch (Exception e) {

                lasku_lisatty = false;

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Laskun tietojen lisaaminen");
                alert.setHeaderText("Tietokantavirhe");
                alert.setContentText("Laskun tietojen lisääminen ei onnistu.");
                alert.showAndWait();

                e.printStackTrace();
            } finally {
                if (lasku_lisatty == true) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Laskun tietojen lisaaminen");
                    alert.setHeaderText("Toiminto ok.");
                    alert.setContentText("Laskun tiedot lisatty tietokantaan.");
                    alert.showAndWait();

                }
            }

        }

    }
    
    public void muutaTiedot() {
        
        //m_lasku = new Lasku();
        boolean lasku_muutettu = true;
        
        m_lasku.setLaskuNro(Integer.parseInt(txtLaskunumero.getText()));
        m_lasku.setLoppusumma(Double.parseDouble(txtLoppusumma.getText()));
        m_lasku.setVastaanottaja(txtVastaanottaja.getText());
        m_lasku.setLahiosoite(txtLahiosoite.getText());
        m_lasku.setPostinumero(txtPostiNro.getText());
        m_lasku.setPostitoimipaikka(txtPostitoimipaikka.getText());
        m_lasku.setEmail(txtSahkopostiosoite.getText());
        m_lasku.setViite(txtViitenumero.getText());
        m_lasku.setErapaiva(txtErapaiva.getText());
        
        if (radiobuttonPaperilasku.isSelected()) {
            m_lasku.setPaperilasku(1);
        } else {
            m_lasku.setPaperilasku(0);
        }

        m_lasku.setPvm(txtPvm.getText());
        m_lasku.setMaksuehto(txtMaksuehto.getText());
        m_lasku.setKustannuspaikka(txtKustannuspaikka.getText());
        m_lasku.setTila(Integer.parseInt(txtTila.getText()));
        m_lasku.setAsiakasNro(Integer.parseInt(txtAsiakasnumero.getText()));
        m_lasku.setVarausNro(Integer.parseInt(txtVarausnumero.getText()));

        try {
            
            Tietokanta.muokkaaLasku(m_lasku);
        } catch (Exception e) {

            lasku_muutettu = false;

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Laskun tietojen muuttaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Laskun tietojen muuttaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        } finally {
            if (lasku_muutettu == true) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Laskun tietojen muuttaminen");
                alert.setHeaderText("Toiminto ok.");
                alert.setContentText("Laskun tiedot muutettu.");
                alert.showAndWait();

            }
        }

    }

    public void poistaTiedot() {

        m_lasku = null;
        boolean lasku_poistettu = false;

        try {
            m_lasku = Tietokanta.haeLasku(Integer.parseInt(txtLaskunumero.getText()));
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Laskun tietojen poistaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Laskun tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        }
        if (m_lasku.getVastaanottaja()== null) {

            txtLoppusumma.setText("");
            txtVastaanottaja.setText("");
            txtLahiosoite.setText("");
            txtPostiNro.setText("");
            txtPostitoimipaikka.setText("");
            txtSahkopostiosoite.setText("");
            txtViitenumero.setText("");
            txtErapaiva.setText("");
            radiobuttonSahkoinenLasku.setSelected(false);
            radiobuttonPaperilasku.setSelected(false);
            txtPvm.setText("");
            txtMaksuehto.setText("");
            txtKustannuspaikka.setText("");
            txtTila.setText("");
            txtAsiakasnumero.setText("");
            txtVarausnumero.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Laskun tietojen poisto");
            alert.setHeaderText("Virhe");
            alert.setContentText("Laskua ei loydy.");
            alert.showAndWait();

            return;
        } else {
            
            txtLaskunumero.setText(String.valueOf(m_lasku.getLaskuNro()));
            txtLoppusumma.setText(String.valueOf(m_lasku.getLoppuSumma()));
            txtVastaanottaja.setText(m_lasku.getVastaanottaja());
            txtLahiosoite.setText(m_lasku.getLahiosoite());
            txtPostiNro.setText(m_lasku.getPostinumero());
            txtPostitoimipaikka.setText(m_lasku.getPostitoimipaikka());
            txtSahkopostiosoite.setText(m_lasku.getEmail());
            txtViitenumero.setText(m_lasku.getViite());
            txtErapaiva.setText(m_lasku.getEraPaiva());
            
            switch (m_lasku.getPaperilasku()) {
                case 1:
                    radiobuttonPaperilasku.setSelected(true);
                    break;
                default:
                    radiobuttonSahkoinenLasku.setSelected(true);
                    break;
            }
            
            txtPvm.setText(m_lasku.getPvm());
            txtMaksuehto.setText(m_lasku.getMaksuehto());
            txtKustannuspaikka.setText(m_lasku.getKustannuspaikka());
            txtTila.setText(String.valueOf(m_lasku.getTila()));
            txtAsiakasnumero.setText(String.valueOf(m_lasku.getAsiakasNro()));
            txtVarausnumero.setText(String.valueOf(m_lasku.getVarausNro()));
        }
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Laskun tietojen poisto");
            alert.setHeaderText("Vahvista");
            alert.setContentText("Haluatko todella poistaa laskun tiedot?");

            Optional<ButtonType> vastaus = alert.showAndWait();

            if (vastaus.get() == ButtonType.OK) {
                Tietokanta.poistaLasku(m_lasku.getLaskuNro());
                lasku_poistettu = true;
            }
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Laskun tietojen poisto");
            alert.setHeaderText("Tulos:");
            alert.setContentText("Laskun tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        } finally {
            if (lasku_poistettu == true) {
                txtLaskunumero.setText("");
                txtLoppusumma.setText("");
                txtVastaanottaja.setText("");
                txtLahiosoite.setText("");
                txtPostiNro.setText("");
                txtPostitoimipaikka.setText("");
                txtSahkopostiosoite.setText("");
                txtViitenumero.setText("");
                txtErapaiva.setText("");
                radiobuttonSahkoinenLasku.setSelected(false);
                radiobuttonPaperilasku.setSelected(false);
                txtPvm.setText("");
                txtMaksuehto.setText("");
                txtKustannuspaikka.setText("");
                txtTila.setText("");
                txtAsiakasnumero.setText("");
                txtVarausnumero.setText("");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Laskun tietojen poisto");
                alert.setHeaderText("Tulos:");
                alert.setContentText("Laskun tiedot poistettu tietokannasta.");
                alert.showAndWait();

                m_lasku = null;
            }
        }

    }

    @FXML
    private void ntnHaeOnMouseExited(MouseEvent event) {
        btnHaeLasku.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnHaeOnMouseEntered(MouseEvent event) {
        btnHaeLasku.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void btnLisaaOnMouseExited(MouseEvent event) {
        btnLisaaLasku.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnLisaaOnMouseEntered(MouseEvent event) {
        btnLisaaLasku.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void btnMuokkaaOnMouseExited(MouseEvent event) {
        btnMuokkaaLaskua.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnMuokkaaOnMouseEntered(MouseEvent event) {
        btnMuokkaaLaskua.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void btnPoistaOnMouseExited(MouseEvent event) {
        btnPoistaLasku.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnPoistaOnMouseEntered(MouseEvent event) {
        btnPoistaLasku.setStyle("-fx-background-color: #1e2e5a;");
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
