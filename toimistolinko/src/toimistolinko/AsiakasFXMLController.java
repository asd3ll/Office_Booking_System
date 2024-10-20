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
 * @author R01
 */
public class AsiakasFXMLController implements Initializable {
    
    private Asiakas m_asiakas = new Asiakas();
    
    @FXML
    private TextField txtAsiakasnro;
    @FXML
    private TextField txtNimi;
    @FXML
    private TextField txtHyTunnus;
    @FXML
    private TextField txtLahiosoite;
    @FXML
    private TextField txtPostiNro;
    @FXML
    private TextField txtPostitoimipaikka;
    @FXML
    private TextField txtPuhNro;
    @FXML
    private TextField txtSahkoposti;
    @FXML
    private TextField txtYhteyshenkilo;
    @FXML
    private Button btnHaeAsiakas;
    @FXML
    private Button btnLisaaAsiakas;
    @FXML
    private Button btnMuokkaaAsiakas;
    @FXML
    private Button btnPoistaAsiakas;
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
    private void btn_lisaaAsiakas(ActionEvent event) {  
        lisaaTiedot();
    }

    @FXML
    private void btn_muokkaaAsiakasta(ActionEvent event) {
        muutaTiedot();
    }

    @FXML
    private void btn_poistaAsiakas(ActionEvent event) {
        poistaTiedot();
    }

    @FXML
    private void btn_haeAsiakas(ActionEvent event) {
        haeTiedot();
    }
    
    
    public void haeTiedot() {

        m_asiakas = null;

        try {
            m_asiakas = Tietokanta.haeAsiakas(Integer.parseInt(txtAsiakasnro.getText()));
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Asiakkaan tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Asiakasta ei loydy.");
            alert.showAndWait();

        }
        if (m_asiakas.getNimi()== null) {

            txtAsiakasnro.setText("");
            txtNimi.setText("");
            txtHyTunnus.setText("");
            txtLahiosoite.setText("");
            txtPostiNro.setText("");
            txtPostitoimipaikka.setText("");
            txtPuhNro.setText("");
            txtSahkoposti.setText("");
            txtYhteyshenkilo.setText("");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Asiakkaan tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Asiakasta ei loydy.");
            alert.showAndWait();

        } else {

            txtAsiakasnro.setText(String.valueOf(m_asiakas.getAsiakasNro()));
            txtNimi.setText(m_asiakas.getNimi());
            txtHyTunnus.setText(m_asiakas.gethyTunnus());
            txtLahiosoite.setText(m_asiakas.getLahiosoite());
            txtPostiNro.setText(m_asiakas.getPostinumero());
            txtPostitoimipaikka.setText(m_asiakas.getPostitoimipaikka());
            txtPuhNro.setText(m_asiakas.getPuhelinnumero());
            txtSahkoposti.setText(m_asiakas.getEmail());
            txtYhteyshenkilo.setText(m_asiakas.getYhteyshenkilo());
        }

    }
    
    public void lisaaTiedot() {

        boolean asiakas_lisatty = true;
        m_asiakas = null;
        try {
            m_asiakas = Tietokanta.haeAsiakas(Integer.parseInt(txtAsiakasnro.getText()));
        } catch (Exception e) {

            asiakas_lisatty = false;
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Asiakkaan tietojen lisaaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Asiakkaan tietojen lisääminen ei onnistu.");
            alert.showAndWait();
        }
        if (m_asiakas.getNimi()!= null) {

            asiakas_lisatty = false;
            txtAsiakasnro.setText(String.valueOf(m_asiakas.getAsiakasNro()));
            txtNimi.setText(m_asiakas.getNimi());
            txtHyTunnus.setText(m_asiakas.gethyTunnus());
            txtLahiosoite.setText(m_asiakas.getLahiosoite());
            txtPostiNro.setText(m_asiakas.getPostinumero());
            txtPostitoimipaikka.setText(m_asiakas.getPostitoimipaikka());
            txtPuhNro.setText(m_asiakas.getPuhelinnumero());
            txtSahkoposti.setText(m_asiakas.getEmail());
            txtYhteyshenkilo.setText(m_asiakas.getYhteyshenkilo());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Asiakkaan tietojen lisaaminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Asiakas on jo olemassa.");
            alert.showAndWait();

        } else {

            m_asiakas.setAsiakasNro(Integer.parseInt(txtAsiakasnro.getText()));
            m_asiakas.setNimi(txtNimi.getText());
            m_asiakas.sethyTunnus(txtHyTunnus.getText());
            m_asiakas.setLahiosoite(txtLahiosoite.getText());
            m_asiakas.setPostinumero(txtPostiNro.getText());
            m_asiakas.setPostitoimipaikka(txtPostitoimipaikka.getText());
            m_asiakas.setPuhelinnumero(txtPuhNro.getText());
            m_asiakas.setEmail(txtSahkoposti.getText());
            m_asiakas.setYhteyshenkilo(txtYhteyshenkilo.getText());
            try {

                Tietokanta.lisaaAsiakas(m_asiakas);
            } catch (Exception e) {

                asiakas_lisatty = false;

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Asiakkaan tietojen lisaaminen");
                alert.setHeaderText("Tietokantavirhe");
                alert.setContentText("Asiakkaan tietojen lisääminen ei onnistu.");
                alert.showAndWait();

                e.printStackTrace();
            } finally {
                if (asiakas_lisatty == true) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Asiakkaan tietojen lisaaminen");
                    alert.setHeaderText("Toiminto ok.");
                    alert.setContentText("Asiakkaan tiedot lisatty tietokantaan.");
                    alert.showAndWait();

                }
            }

        }

    }
    
    public void muutaTiedot() {

        boolean asiakas_muutettu = true;

        m_asiakas.setNimi(txtNimi.getText());
        m_asiakas.sethyTunnus(txtHyTunnus.getText());
        m_asiakas.setLahiosoite(txtLahiosoite.getText());
        m_asiakas.setPostinumero(txtPostiNro.getText());
        m_asiakas.setPostitoimipaikka(txtPostitoimipaikka.getText());
        m_asiakas.setPuhelinnumero(txtPuhNro.getText());
        m_asiakas.setEmail(txtSahkoposti.getText());
        m_asiakas.setYhteyshenkilo(txtYhteyshenkilo.getText());

        try {

            Tietokanta.muokkaaAsiakas(m_asiakas);
        } catch (Exception e) {

            asiakas_muutettu = false;

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Asiakkaan tietojen muuttaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Asiakkaan tietojen muuttaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        } finally {
            if (asiakas_muutettu == true) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Asiakkaan tietojen muuttaminen");
                alert.setHeaderText("Toiminto ok.");
                alert.setContentText("Asiakkaan tiedot muutettu.");
                alert.showAndWait();

            }
        }

    }

    public void poistaTiedot() {

        m_asiakas = null;
        boolean asiakas_poistettu = false;

        try {
            m_asiakas = Tietokanta.haeAsiakas(Integer.parseInt(txtAsiakasnro.getText()));
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Asiakkaan tietojen poistaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Asiakkaan tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        }
        if (m_asiakas.getNimi()== null) {

            txtNimi.setText("");
            txtHyTunnus.setText("");
            txtLahiosoite.setText("");
            txtPostiNro.setText("");
            txtPostitoimipaikka.setText("");
            txtPuhNro.setText("");
            txtSahkoposti.setText("");
            txtYhteyshenkilo.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Asiakkaan tietojen poisto");
            alert.setHeaderText("Virhe");
            alert.setContentText("Asiakasta ei loydy.");
            alert.showAndWait();

            return;
        } else {

            txtNimi.setText(m_asiakas.getNimi());
            txtHyTunnus.setText(m_asiakas.gethyTunnus());
            txtLahiosoite.setText(m_asiakas.getLahiosoite());
            txtPostiNro.setText(m_asiakas.getPostinumero());
            txtPostitoimipaikka.setText(m_asiakas.getPostitoimipaikka());
            txtPuhNro.setText(m_asiakas.getPuhelinnumero());
            txtSahkoposti.setText(m_asiakas.getEmail());
            txtYhteyshenkilo.setText(m_asiakas.getYhteyshenkilo());
        }
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Asiakkaan tietojen poisto");
            alert.setHeaderText("Vahvista");
            alert.setContentText("Haluatko todella poistaa asiakkaan tiedot?");

            Optional<ButtonType> vastaus = alert.showAndWait();

            if (vastaus.get() == ButtonType.OK) {
                Tietokanta.poistaAsiakas(Integer.parseInt(txtAsiakasnro.getText()));
                asiakas_poistettu = true;
            }
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Asiakkaan tietojen poisto");
            alert.setHeaderText("Tulos:");
            alert.setContentText("Asiakkaan tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        } finally {
            if (asiakas_poistettu == true) {
                txtAsiakasnro.setText("");
                txtNimi.setText("");
                txtHyTunnus.setText("");
                txtLahiosoite.setText("");
                txtPostiNro.setText("");
                txtPostitoimipaikka.setText("");
                txtPuhNro.setText("");
                txtSahkoposti.setText("");
                txtYhteyshenkilo.setText("");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Asiakkaan tietojen poisto");
                alert.setHeaderText("Tulos:");
                alert.setContentText("Asiakkaan tiedot poistettu tietokannasta.");
                alert.showAndWait();

                m_asiakas = null;
            }
        }

    }

    @FXML
    private void ntnHaeOnMouseExited(MouseEvent event) {
        btnHaeAsiakas.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnHaeOnMouseEntered(MouseEvent event) {
        btnHaeAsiakas.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void btnLisaaOnMouseExited(MouseEvent event) {
        btnLisaaAsiakas.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnLisaaOnMouseEntered(MouseEvent event) {
        btnLisaaAsiakas.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void btnMuokkaaOnMouseExited(MouseEvent event) {
        btnMuokkaaAsiakas.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnMuokkaaOnMouseEntered(MouseEvent event) {
        btnMuokkaaAsiakas.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void btnPoistaOnMouseExited(MouseEvent event) {
        btnPoistaAsiakas.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnPoistaOnMouseEntered(MouseEvent event) {
        btnPoistaAsiakas.setStyle("-fx-background-color: #1e2e5a;");
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
