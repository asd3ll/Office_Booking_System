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
public class ToimistoFXMLController implements Initializable {

    private Toimisto m_toimisto = new Toimisto();

    @FXML
    private TextField txtToimistoNimi;
    @FXML
    private TextField txtToimistoKoko;
    @FXML
    private TextField txtToimistoHenkiloMaara;
    @FXML
    private TextField txtToimistoVuokra;
    @FXML
    private TextField txtToimistoKuvaus;
    @FXML
    private TextField txtToimistoALV;
    @FXML
    private TextField txtToimipisteNimi;
    @FXML
    private TextField txtToimistoID;
    @FXML
    private Button btnPalaaPaavalikkoon;
    @FXML
    private Button btnHaeToimipiste;
    @FXML
    private Button btnLisaaToimipiste;
    @FXML
    private Button btnMuokkaaToimipiste;
    @FXML
    private Button btnPoistaToimipiste;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btn_haeToimipiste(ActionEvent event) {
        haeTiedot();
    }

    @FXML
    private void btn_lisaaPalvelut(ActionEvent event) {
        lisaaTiedot();
    }

    @FXML
    private void btn_muokkaaToimipiste(ActionEvent event) {
        muutaTiedot();
    }

    @FXML
    private void btn_poistaToimipiste(ActionEvent event) {
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

        m_toimisto = null;

        try {
            m_toimisto = Tietokanta.haeToimisto(Integer.parseInt(txtToimistoID.getText()));
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toimiston tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Toimistoa ei loydy.");
            alert.showAndWait();
        }

        if (m_toimisto.getNimi() == null) {
            txtToimistoID.setText("");
            txtToimistoNimi.setText("");
            txtToimistoKoko.setText("");
            txtToimistoHenkiloMaara.setText("");
            txtToimistoVuokra.setText("");
            txtToimistoKuvaus.setText("");
            txtToimistoALV.setText("");
            txtToimipisteNimi.setText("");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toimiston tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Toimistoa ei loydy.");
            alert.showAndWait();
        } else {
            txtToimistoID.setText(String.valueOf(m_toimisto.getToId()));
            txtToimistoNimi.setText(String.valueOf(m_toimisto.getNimi()));
            txtToimistoKoko.setText(String.valueOf(m_toimisto.getKoko()));
            txtToimistoHenkiloMaara.setText(String.valueOf(m_toimisto.getHenkilomaara()));
            txtToimistoVuokra.setText(String.valueOf(m_toimisto.getVuokra()));
            txtToimistoKuvaus.setText(String.valueOf(m_toimisto.getKuvaus()));
            txtToimistoALV.setText(String.valueOf(m_toimisto.getAlv()));
            txtToimipisteNimi.setText(String.valueOf(m_toimisto.getToimipiste()));
        }
    }

    public void lisaaTiedot() {
        boolean toimisto_lisatty = true;
        m_toimisto = null;

        try {
            m_toimisto = Tietokanta.haeToimisto(Integer.parseInt(txtToimistoID.getText()));
        } catch (Exception e) {

            toimisto_lisatty = false;
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toimiston tietojen lisaaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Toimiston tietojen lisääminen ei onnistu.");
            alert.showAndWait();
        }
        if (m_toimisto.getNimi() != null) {

            toimisto_lisatty = false;

            txtToimistoID.setText(String.valueOf(m_toimisto.getToId()));
            txtToimistoNimi.setText(m_toimisto.getNimi());
            txtToimistoKoko.setText(String.valueOf(m_toimisto.getKoko()));
            txtToimistoHenkiloMaara.setText(String.valueOf(m_toimisto.getHenkilomaara()));
            txtToimistoVuokra.setText(String.valueOf(m_toimisto.getVuokra()));
            txtToimistoKuvaus.setText(m_toimisto.getKuvaus());
            txtToimistoALV.setText(String.valueOf(m_toimisto.getAlv()));
            txtToimipisteNimi.setText(m_toimisto.getToimipiste());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toimiston tietojen lisaaminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Toimisto on jo olemassa.");
            alert.showAndWait();
        } else {
            m_toimisto.setToId(Integer.parseInt(txtToimistoID.getText()));
            m_toimisto.setNimi(txtToimistoNimi.getText());
            m_toimisto.setKoko(Double.parseDouble(txtToimistoKoko.getText()));
            m_toimisto.setHenkilomaara(Integer.parseInt(txtToimistoHenkiloMaara.getText()));
            m_toimisto.setVuokra(Double.parseDouble(txtToimistoVuokra.getText()));
            m_toimisto.setKuvaus(txtToimistoKuvaus.getText());
            m_toimisto.setAlv(Integer.parseInt(txtToimistoALV.getText()));
            m_toimisto.setToimipiste(txtToimipisteNimi.getText());

            try {

                Tietokanta.lisaaToimisto(m_toimisto);
            } catch (Exception e) {

                toimisto_lisatty = false;

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Toimiston tietojen lisaaminen");
                alert.setHeaderText("Tietokantavirhe");
                alert.setContentText("Toimiston tietojen lisääminen ei onnistu.");
                alert.showAndWait();

                e.printStackTrace();
            } finally {
                if (toimisto_lisatty == true) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Toimiston tietojen lisaaminen");
                    alert.setHeaderText("Toiminto ok.");
                    alert.setContentText("Toimiston tiedot lisatty tietokantaan.");
                    alert.showAndWait();
                }
            }
        }
    }

    public void muutaTiedot() {
        boolean toimisto_muutettu = true;

        m_toimisto.setToId(Integer.parseInt(txtToimistoID.getText()));
        m_toimisto.setNimi(txtToimistoNimi.getText());
        m_toimisto.setKoko(Double.parseDouble(txtToimistoKoko.getText()));
        m_toimisto.setHenkilomaara(Integer.parseInt(txtToimistoHenkiloMaara.getText()));
        m_toimisto.setVuokra(Double.parseDouble(txtToimistoVuokra.getText()));
        m_toimisto.setKuvaus(txtToimistoKuvaus.getText());
        m_toimisto.setAlv(Integer.parseInt(txtToimistoALV.getText()));
        m_toimisto.setToimipiste(txtToimipisteNimi.getText());

        try {

            Tietokanta.muokkaaToimisto(m_toimisto);
        } catch (Exception e) {

            toimisto_muutettu = false;

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toimiston tietojen muuttaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Toimiston tietojen muuttaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        } finally {
            if (toimisto_muutettu == true) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Toimiston tietojen muuttaminen");
                alert.setHeaderText("Toiminto ok.");
                alert.setContentText("Toimiston tiedot muutettu.");
                alert.showAndWait();
            }
        }
    }

    public void poistaTiedot() {
        m_toimisto = null;
        boolean toimisto_poistettu = false;

        try {
            m_toimisto = Tietokanta.haeToimisto(Integer.parseInt(txtToimistoID.getText()));
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toimiston tietojen poistaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Toimiston tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        }

        if (m_toimisto.getNimi() == null) {
            txtToimistoNimi.setText("");
            txtToimistoKoko.setText("");
            txtToimistoHenkiloMaara.setText("");
            txtToimistoVuokra.setText("");
            txtToimistoKuvaus.setText("");
            txtToimistoALV.setText("");
            txtToimipisteNimi.setText("");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toimiston tietojen poistaminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Toimistoa ei loydy.");
            alert.showAndWait();

            return;
        } else {
            txtToimistoNimi.setText(m_toimisto.getNimi());
            txtToimistoKoko.setText(String.valueOf(m_toimisto.getKoko()));
            txtToimistoHenkiloMaara.setText(String.valueOf(m_toimisto.getHenkilomaara()));
            txtToimistoVuokra.setText(String.valueOf(m_toimisto.getVuokra()));
            txtToimistoKuvaus.setText(m_toimisto.getKuvaus());
            txtToimistoALV.setText(String.valueOf(m_toimisto.getAlv()));
            txtToimipisteNimi.setText(m_toimisto.getToimipiste());
        }

        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Toimiston tietojen poisto");
            alert.setHeaderText("Vahvista");
            alert.setContentText("Haluatko todella poistaa toimiston tiedot?");

            Optional<ButtonType> vastaus = alert.showAndWait();

            if (vastaus.get() == ButtonType.OK) {
                Tietokanta.poistaToimisto(Integer.parseInt(txtToimistoID.getText()));
                toimisto_poistettu = true;
            }
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Toimiston tietojen poisto");
            alert.setHeaderText("Tulos:");
            alert.setContentText("Toimiston tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        } finally {
            if (toimisto_poistettu == true) {
                txtToimistoID.setText("");
                txtToimistoNimi.setText("");
                txtToimistoKoko.setText("");
                txtToimistoHenkiloMaara.setText("");
                txtToimistoVuokra.setText("");
                txtToimistoKuvaus.setText("");
                txtToimistoALV.setText("");
                txtToimipisteNimi.setText("");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Toimiston tietojen poisto");
                alert.setHeaderText("Tulos:");
                alert.setContentText("Toimiston tiedot poistettu tietokannasta.");
                alert.showAndWait();

                m_toimisto = null;
            }
        }
    }

    @FXML
    private void ntnHaeOnMouseExited(MouseEvent event) {
        btnHaeToimipiste.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnHaeOnMouseEntered(MouseEvent event) {
        btnHaeToimipiste.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void btnLisaaOnMouseExited(MouseEvent event) {
        btnLisaaToimipiste.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnLisaaOnMouseEntered(MouseEvent event) {
        btnLisaaToimipiste.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void btnMuokkaaOnMouseExited(MouseEvent event) {
        btnMuokkaaToimipiste.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnMuokkaaOnMouseEntered(MouseEvent event) {
        btnMuokkaaToimipiste.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void btnPoistaOnMouseExited(MouseEvent event) {
        btnPoistaToimipiste.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnPoistaOnMouseEntered(MouseEvent event) {
        btnPoistaToimipiste.setStyle("-fx-background-color: #1e2e5a;");
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
