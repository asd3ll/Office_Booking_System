/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package toimistolinko;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hanna
 */
public class VarausFXMLController implements Initializable {

    private Varaus m_varaus = new Varaus();
    private Palveluvaraus m_palveluvaraus = new Palveluvaraus();

    ObservableList<Map<String, Object>> paTiedot = FXCollections.<Map<String, Object>>observableArrayList();

    @FXML
    private TextField txtVarausNumero;
    @FXML
    private TextField txtVarausVuokra;
    @FXML
    private TextField txtVarausPalveluVuokra;
    @FXML
    private DatePicker dpAloitusPvm;
    @FXML
    private DatePicker dpLopetusPvm;
    @FXML
    private TextField txtVarausVuokraAlv;
    @FXML
    private TextField txtVarausPalvelunAlv;
    @FXML
    private TextField txtVarausTiedot;
    @FXML
    private TextField txtVarausToimistoID;
    @FXML
    private TextField txtVarausAsiakasNumero;
    @FXML
    private Button btnHaeVaraus;
    @FXML
    private Button btnLisaaVaraus;
    @FXML
    private Button btnMuokkaaVaraus;
    @FXML
    private Button btnPoistaVaraus;
    @FXML
    private Button btnPalaaPaavalikkoon;
    @FXML
    private Button btnLisaaPa;
    @FXML
    private Button btnMuutaPa;
    @FXML
    private Button btnPoistaPa;
    
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        paLista.setPlaceholder(new Label("Pahus, laihalta näyttää :(\nMyykää enemmän palveluita."));
        paId.setCellValueFactory(new MapValueFactory<>("paId"));
        paNimi.setCellValueFactory(new MapValueFactory<>("paNimi"));
        paKpl.setCellValueFactory(new MapValueFactory<>("paKpl"));
    }    

    @FXML
    private void btn_haeVaraus(ActionEvent event) {
        haeTiedot();
    }

    @FXML
    private void btn_lisaaVaraus(ActionEvent event) {
        lisaaTiedot();
    }

    @FXML
    private void btn_muokkaaVaraus(ActionEvent event) {
        muutaTiedot();
    }

    @FXML
    private void btn_poistaVaraus(ActionEvent event) {
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

    
    @FXML
    private TableView<Map> paLista;

    @FXML
    private TableColumn<Map, String> paId;

    @FXML
    private TableColumn<Map, String> paNimi;

    @FXML
    private TableColumn<Map, String> paKpl;

    @FXML
    private TextField txtLipaId;

    @FXML
    private TextField txtLipaKpl;

    @FXML
    private void btn_lisaaPa(ActionEvent event) {
        lisaaPa();
    }

    @FXML
    void btn_muutaPa(ActionEvent event) {
        muutaPa();
    }

    @FXML
    void btn_poistaPa(ActionEvent event) {
        poistaPa();
    }
    
    public void haeTiedot() {

        m_varaus = null;

        try {
            m_varaus = Tietokanta.haeVaraus(Integer.parseInt(txtVarausNumero.getText()));
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Varauksen tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Varausta ei voitu hakea.");
            alert.showAndWait();

        }
        if (m_varaus.getVarausTehty() == null) {

            txtVarausNumero.setText("");
            txtVarausVuokra.setText("");
            txtVarausPalveluVuokra.setText("");
            dpAloitusPvm.setValue(null);
            dpLopetusPvm.setValue(null);
            txtVarausVuokraAlv.setText("");
            txtVarausPalvelunAlv.setText("");
            txtVarausTiedot.setText("");
            txtVarausToimistoID.setText("");
            txtVarausAsiakasNumero.setText("");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Varauksen tietojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Varausta ei loydy.");
            alert.showAndWait();

        } else {

            txtVarausNumero.setText(Integer.toString(m_varaus.getVarausNro()));
            txtVarausVuokra.setText(Double.toString(m_varaus.getVuokra()));
            txtVarausPalveluVuokra.setText(Double.toString(m_varaus.getPaVuokra()));
            dpAloitusPvm.setValue(m_varaus.getAlkuPvm());
            dpLopetusPvm.setValue(m_varaus.getLoppuPvm());
            txtVarausVuokraAlv.setText(Integer.toString(m_varaus.getVuokraAlv()));
            txtVarausPalvelunAlv.setText(Integer.toString(m_varaus.getPaAlv()));
            txtVarausTiedot.setText(m_varaus.getVarausTehty().format(dateTimeFormatter));
            txtVarausToimistoID.setText(Integer.toString(m_varaus.getToId()));
            txtVarausAsiakasNumero.setText(Integer.toString(m_varaus.getAsiakasNro()));

            haePa();
            
        }

    }

    public void lisaaTiedot() {

        boolean varaus_lisatty = true;
        m_varaus = null;
        try {
            m_varaus = Tietokanta.haeVaraus(Integer.parseInt(txtVarausNumero.getText()));
        } catch (Exception e) {

            varaus_lisatty = false;
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Varauksen tietojen hakeminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Tarkistuhaku epaonnistui.");
            alert.showAndWait();
        }
        if (m_varaus.getVarausTehty()!= null) {

            varaus_lisatty = false;
            txtVarausNumero.setText(Integer.toString(m_varaus.getVarausNro()));
            txtVarausVuokra.setText(Double.toString(m_varaus.getVuokra()));
            txtVarausPalveluVuokra.setText(Double.toString(m_varaus.getPaVuokra()));
            dpAloitusPvm.setValue(m_varaus.getAlkuPvm());
            dpLopetusPvm.setValue(m_varaus.getLoppuPvm());
            txtVarausVuokraAlv.setText(Integer.toString(m_varaus.getVuokraAlv()));
            txtVarausPalvelunAlv.setText(Integer.toString(m_varaus.getPaAlv()));
            txtVarausTiedot.setText(m_varaus.getVarausTehty().format(dateTimeFormatter));
            txtVarausToimistoID.setText(Integer.toString(m_varaus.getToId()));
            txtVarausAsiakasNumero.setText(Integer.toString(m_varaus.getAsiakasNro()));

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Varauksen tietojen lisaaminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Varaus on jo olemassa.");
            alert.showAndWait();

        } else {
            
            m_varaus.setVarausNro(Integer.parseInt(txtVarausNumero.getText()));
            m_varaus.setVuokra(Double.parseDouble(txtVarausVuokra.getText()));
            m_varaus.setPaVuokra(Double.parseDouble(txtVarausPalveluVuokra.getText()));
            m_varaus.setAlkuPvm(dpAloitusPvm.getValue());
            m_varaus.setLoppuPvm(dpLopetusPvm.getValue());
            m_varaus.setVuokraAlv(Integer.parseInt(txtVarausVuokraAlv.getText()));
            m_varaus.setPaAlv(Integer.parseInt(txtVarausPalvelunAlv.getText()));
            m_varaus.setVarausTehty(LocalDateTime.now());
            m_varaus.setToId(Integer.parseInt(txtVarausToimistoID.getText()));
            m_varaus.setAsiakasNro(Integer.parseInt(txtVarausAsiakasNumero.getText()));
            m_varaus.setVarausTehty(LocalDateTime.now());
            try {

                Tietokanta.lisaaVaraus(m_varaus);
            } catch (Exception e) {

                varaus_lisatty = false;

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Varauksen tietojen lisaaminen");
                alert.setHeaderText("Tietokantavirhe");
                alert.setContentText("Varauksen tietojen lisääminen ei onnistu.");
                alert.showAndWait();

                e.printStackTrace();
            } finally {
                if (varaus_lisatty == true) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Varauksen tietojen lisaaminen");
                    alert.setHeaderText("Toiminto ok.");
                    alert.setContentText("Varaus lisatty tietokantaan.");
                    alert.showAndWait();
                    txtVarausTiedot.setText(m_varaus.getVarausTehty().format(dateTimeFormatter));
                }
            }

        }

    }

    public void muutaTiedot() {

        boolean varaus_muutettu = true;

        m_varaus.setVarausNro(Integer.parseInt(txtVarausNumero.getText()));
        m_varaus.setVuokra(Double.parseDouble(txtVarausVuokra.getText()));
        m_varaus.setPaVuokra(Double.parseDouble(txtVarausPalveluVuokra.getText()));
        m_varaus.setAlkuPvm(dpAloitusPvm.getValue());
        m_varaus.setLoppuPvm(dpLopetusPvm.getValue());
        m_varaus.setVuokraAlv(Integer.parseInt(txtVarausVuokraAlv.getText()));
        m_varaus.setPaAlv(Integer.parseInt(txtVarausPalvelunAlv.getText()));
        m_varaus.setVarausTehty(LocalDateTime.parse(txtVarausTiedot.getText(), dateTimeFormatter));
        m_varaus.setToId(Integer.parseInt(txtVarausToimistoID.getText()));
        m_varaus.setAsiakasNro(Integer.parseInt(txtVarausAsiakasNumero.getText()));
        m_varaus.setVarausTehty(LocalDateTime.now());

        try {
            Tietokanta.muokkaaVaraus(m_varaus);

        } catch (Exception e) {

            varaus_muutettu = false;

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Varauksen tietojen muuttaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Varauksen tietojen muuttaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        } finally {
            if (varaus_muutettu == true) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Varauksen tietojen muuttaminen");
                alert.setHeaderText("Toiminto ok.");
                alert.setContentText("Varauksen tiedot muutettu.");
                alert.showAndWait();
            }
        }

    }

    public void poistaTiedot() {

        m_varaus = null;
        boolean varaus_poistettu = false;

        try {
            m_varaus = Tietokanta.haeVaraus(Integer.parseInt(txtVarausNumero.getText()));
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Varauksen poistaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Varauksen poistaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        }
        if (m_varaus.getVarausTehty() == null) {

            txtVarausNumero.setText("");
            txtVarausVuokra.setText("");
            txtVarausPalveluVuokra.setText("");
            dpAloitusPvm.setValue(null);
            dpLopetusPvm.setValue(null);
            txtVarausVuokraAlv.setText("");
            txtVarausPalvelunAlv.setText("");
            txtVarausTiedot.setText("");
            txtVarausToimistoID.setText("");
            txtVarausAsiakasNumero.setText("");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Varauksen poistaminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Varausta ei loydy.");
            alert.showAndWait();

            return;
        } else {

            txtVarausNumero.setText(Integer.toString(m_varaus.getVarausNro()));
            txtVarausVuokra.setText(Double.toString(m_varaus.getVuokra()));
            txtVarausPalveluVuokra.setText(Double.toString(m_varaus.getPaVuokra()));
            dpAloitusPvm.setValue(m_varaus.getAlkuPvm());
            dpLopetusPvm.setValue(m_varaus.getLoppuPvm());
            txtVarausVuokraAlv.setText(Integer.toString(m_varaus.getVuokraAlv()));
            txtVarausPalvelunAlv.setText(Integer.toString(m_varaus.getPaAlv()));
            txtVarausTiedot.setText(m_varaus.getVarausTehty().format(dateTimeFormatter));
            txtVarausToimistoID.setText(Integer.toString(m_varaus.getToId()));
            txtVarausAsiakasNumero.setText(Integer.toString(m_varaus.getAsiakasNro()));
            
        }
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Asiakkaan tietojen poisto");
            alert.setHeaderText("Vahvista");
            alert.setContentText("Haluatko todella poistaa asiakkaan tiedot?");

            Optional<ButtonType> vastaus = alert.showAndWait();

            if (vastaus.get() == ButtonType.OK) {
                Tietokanta.poistaVaraus(m_varaus.getVarausNro());
                varaus_poistettu = true;
            }
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Asiakkaan tietojen poisto");
            alert.setHeaderText("Tulos:");
            alert.setContentText("Asiakkaan tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        } finally {
            if (varaus_poistettu == true) {
                txtVarausNumero.setText("");
                txtVarausVuokra.setText("");
                txtVarausPalveluVuokra.setText("");
                dpAloitusPvm.setValue(null);
                dpLopetusPvm.setValue(null);
                txtVarausVuokraAlv.setText("");
                txtVarausPalvelunAlv.setText("");
                txtVarausTiedot.setText("");
                txtVarausToimistoID.setText("");
                txtVarausAsiakasNumero.setText("");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Asiakkaan tietojen poisto");
                alert.setHeaderText("Tulos:");
                alert.setContentText("Asiakkaan tiedot poistettu tietokannasta.");
                alert.showAndWait();

                m_varaus = null;
            }
        }

    }

    public void haePa() {

        paTiedot = null;

        try {
            paTiedot = Tietokanta.haePalveluvarausLista(Integer.parseInt(txtVarausNumero.getText()));
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Palveluvarausten hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Palveluvarauksia ei voitu hakea.");
            alert.showAndWait();

        }
        if (paTiedot.isEmpty()) {
            paLista.getItems().clear();
        } else {
            paLista.getItems().clear();
            paLista.getItems().addAll(paTiedot);
        }

    }

    public void lisaaPa() {

        boolean palveluvaraus_lisatty = true;
        m_palveluvaraus = null;
        try {
            m_palveluvaraus = Tietokanta.haePalveluvaraus(Integer.parseInt(txtVarausNumero.getText()), Integer.parseInt(txtLipaId.getText()));
        } catch (Exception e) {

            palveluvaraus_lisatty = false;
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Palveluvarauksen tietojen hakeminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Tarkistuhaku epaonnistui.");
            alert.showAndWait();
        }
        if (!m_palveluvaraus.toString().equals("0 0 0")) {

            palveluvaraus_lisatty = false;
            txtLipaId.setText(Integer.toString(m_palveluvaraus.getPaId()));
            txtLipaKpl.setText(Integer.toString(m_palveluvaraus.getKpl()));

            System.out.println(m_palveluvaraus);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Palveluvarauksen tietojen lisaaminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Palveluvaraus on jo olemassa.");
            alert.showAndWait();

        } else {
            
            m_palveluvaraus.setVarausNro(Integer.parseInt(txtVarausNumero.getText()));
            m_palveluvaraus.setPaId(Integer.parseInt(txtLipaId.getText()));
            m_palveluvaraus.setKpl(Integer.parseInt(txtLipaKpl.getText()));

            try {
                Tietokanta.lisaaPalveluvaraus(m_palveluvaraus);
            } catch (Exception e) {

                palveluvaraus_lisatty = false;

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Palveluvarauksen tietojen lisaaminen");
                alert.setHeaderText("Tietokantavirhe");
                alert.setContentText("Palveluvarauksen tietojen lisääminen ei onnistu.");
                alert.showAndWait();

                e.printStackTrace();
            } finally {
                if (palveluvaraus_lisatty == true) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Palveluvarauksen tietojen lisaaminen");
                    alert.setHeaderText("Toiminto ok.");
                    alert.setContentText("Palveluvaraus lisatty tietokantaan.");
                    alert.showAndWait();

                }
            }

        }

        haePa();
    }

    public void muutaPa() {

        boolean palveluvaraus_muutettu = true;

        m_palveluvaraus.setVarausNro(Integer.parseInt(txtVarausNumero.getText()));
        m_palveluvaraus.setPaId(Integer.parseInt(txtLipaId.getText()));
        m_palveluvaraus.setKpl(Integer.parseInt(txtLipaKpl.getText()));

        try {
            Tietokanta.muokkaaPalveluvaraus(m_palveluvaraus);

        } catch (Exception e) {

            palveluvaraus_muutettu = false;

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Palveluvarauksen tietojen muuttaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Palveluvarauksen tietojen muuttaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        } finally {
            if (palveluvaraus_muutettu == true) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Palveluvarauksen tietojen muuttaminen");
                alert.setHeaderText("Toiminto ok.");
                alert.setContentText("Palveluvarauksen tiedot muutettu.");
                alert.showAndWait();
            }
        }

        haePa();
    }

    public void poistaPa() {

        m_palveluvaraus = null;
        boolean palveluvaraus_poistettu = false;

        try {
            m_palveluvaraus = Tietokanta.haePalveluvaraus(Integer.parseInt(txtVarausNumero.getText()), Integer.parseInt(txtLipaId.getText()));
            
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Palveluvarauksen poistaminen");
            alert.setHeaderText("Tietokantavirhe");
            alert.setContentText("Palveluvarauksen poistaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        }
        if (m_palveluvaraus.toString().equals("0 0 0")) {

            txtLipaId.setText("");
            txtLipaKpl.setText("");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Palveluvarauksen poistaminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Palveluvarausta ei loydy.");
            alert.showAndWait();

            return;
        } else {

            txtLipaId.setText(Integer.toString(m_palveluvaraus.getPaId()));
            txtLipaKpl.setText(Integer.toString(m_palveluvaraus.getKpl()));
            
        }
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Palveluvarauksen tietojen poisto");
            alert.setHeaderText("Vahvista");
            alert.setContentText("Haluatko todella poistaa palveluvarauksen?");

            Optional<ButtonType> vastaus = alert.showAndWait();

            if (vastaus.get() == ButtonType.OK) {
                Tietokanta.poistaPalveluvaraus(Integer.parseInt(txtVarausNumero.getText()), Integer.parseInt(txtLipaId.getText()));
                palveluvaraus_poistettu = true;
            }
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Palveluvarauksen tietojen poisto");
            alert.setHeaderText("Tulos:");
            alert.setContentText("Palveluvarauksen tietojen poistaminen ei onnistu.");
            alert.showAndWait();

            e.printStackTrace();
        } finally {
            if (palveluvaraus_poistettu == true) {
                txtLipaId.setText("");
                txtLipaKpl.setText("");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Palveluvarauksen tietojen poisto");
                alert.setHeaderText("Tulos:");
                alert.setContentText("Palveluvaraus poistettu tietokannasta.");
                alert.showAndWait();

                m_palveluvaraus = null;
            }
        }

        haePa();
    }

    @FXML
    private void TblLisaaOnMouseExited(MouseEvent event) {
        btnLisaaPa.setStyle("-fx-background-color: #304890;"); 
    }

    @FXML
    private void TblLisaaOnMouseEntered(MouseEvent event) {
        btnLisaaPa.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void TblMuutaOnMouseExited(MouseEvent event) {
        btnMuutaPa.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void TblMuutaOnMouseEntered(MouseEvent event) {
        btnMuutaPa.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void TblPoistaOnMouseExited(MouseEvent event) {
        btnPoistaPa.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void TblPoistaOnMouseEntered(MouseEvent event) {
        btnPoistaPa.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void ntnHaeOnMouseExited(MouseEvent event) {
        btnHaeVaraus.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnHaeOnMouseEntered(MouseEvent event) {
        btnHaeVaraus.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void btnLisaaOnMouseExited(MouseEvent event) {
        btnLisaaVaraus.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnLisaaOnMouseEntered(MouseEvent event) {
        btnLisaaVaraus.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void btnMuokkaaOnMouseExited(MouseEvent event) {
        btnMuokkaaVaraus.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnMuokkaaOnMouseEntered(MouseEvent event) {
        btnMuokkaaVaraus.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void btnPoistaOnMouseExited(MouseEvent event) {
        btnPoistaVaraus.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnPoistaOnMouseEntered(MouseEvent event) {
        btnPoistaVaraus.setStyle("-fx-background-color: #1e2e5a;");
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
