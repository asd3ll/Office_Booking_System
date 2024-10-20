/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package toimistolinko;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author R01
 */
public class LisapalvelutJaLaitteetFXMLController implements Initializable {

    @FXML
    private Button btnPalaaPaavalikkoon;
    @FXML
    private Button btnListaaPaJaLait;
    @FXML
    private TextField txtToimipiste;
    @FXML
    private DatePicker datepickerAloitusPvm;
    @FXML
    private DatePicker datepickerPaattymisPvm;
    @FXML
    private TableView<Palveluvaraus> tblLiJaLa;
    @FXML
    private TableColumn<Palveluvaraus, Integer> col1_varausNro;
    @FXML
    private TableColumn<Palveluvaraus, Integer> col2_palvelunID;
    @FXML
    private TableColumn<Palveluvaraus, Integer> col3_kplMaara;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col1_varausNro.setCellValueFactory(new PropertyValueFactory<Palveluvaraus, Integer>("varausNro"));
        col2_palvelunID.setCellValueFactory(new PropertyValueFactory<Palveluvaraus, Integer>("paId"));
        col3_kplMaara.setCellValueFactory(new PropertyValueFactory<Palveluvaraus, Integer>("kpl"));
    }

    @FXML
    private void btn_listaaPaJaLait(ActionEvent event) {
        listaaTiedot();
        
    }

    @FXML
    private void btn_PalaaPaavalikkoon(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ToimistoLinkoFXML.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    
    public void listaaTiedot() {

        tblLiJaLa.getItems().clear();

        ArrayList<Palveluvaraus> lstVaraukset = null;

        try {
            lstVaraukset = Tietokanta.haeVaratutPalvelut(datepickerAloitusPvm.getValue(), datepickerPaattymisPvm.getValue(), txtToimipiste.getText());
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Vuokrattujen tilojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Vuokrattuja tiloja ei loydy.");
            alert.showAndWait();

        }
        if (lstVaraukset.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Vuokrattujen tilojen hakeminen");
            alert.setHeaderText("Virhe");
            alert.setContentText("Vuokrattuja tiloja ei loydy.");
            alert.showAndWait();

        } else {

            for (Palveluvaraus VuokratutPalvelut : lstVaraukset) {
                tblLiJaLa.getItems().add(VuokratutPalvelut);
            }
        }

    }

    @FXML
    private void btnPalaaOnMouseExited(MouseEvent event) {
        btnPalaaPaavalikkoon.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnPalaaOnMouseEntered(MouseEvent event) {
        btnPalaaPaavalikkoon.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void ntnPaJaLaitOnMouseExited(MouseEvent event) {
        btnListaaPaJaLait.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnPaJaLaitOnMouseEntered(MouseEvent event) {
        btnListaaPaJaLait.setStyle("-fx-background-color: #1e2e5a;");
    }
    
}
