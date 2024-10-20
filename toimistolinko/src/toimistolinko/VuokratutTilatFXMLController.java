/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package toimistolinko;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class VuokratutTilatFXMLController implements Initializable {

    @FXML
    private Button btnPalaaPaavalikkoon;
    @FXML
    private TableView<Varaus> tblVuokratutTilat;
    @FXML
    private TableColumn<Varaus, Integer> col1_varausNro;
    @FXML
    private TableColumn<Varaus, Double> col2_tilanVuokra;
    @FXML
    private TableColumn<Varaus, Double> col3_palvVuokra;
    @FXML
    private TableColumn<Varaus, LocalDate> col4_aloitusPvm;
    @FXML
    private TableColumn<Varaus, LocalDate> col5_paattymisPvm;
    @FXML
    private TableColumn<Varaus, Integer> col6_vuokranAlv;
    @FXML
    private TableColumn<Varaus, Integer> col7_PalvAlv;
    @FXML
    private TableColumn<Varaus, LocalDateTime> col8_varausTehty;
    @FXML
    private TableColumn<Varaus, Integer> col9_ToimistonID;
    @FXML
    private TableColumn<Varaus, Integer> col10_asiakasNro;
    @FXML
    private Button btnListaa;
    @FXML
    private TextField txtToimipiste;
    @FXML
    private DatePicker datepickerAloitusPvm;
    @FXML
    private DatePicker datepickerPaattymisPvm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TableView
        col1_varausNro.setCellValueFactory(new PropertyValueFactory<Varaus, Integer>("varausNro"));
        col2_tilanVuokra.setCellValueFactory(new PropertyValueFactory<Varaus, Double>("vuokra"));
        col3_palvVuokra.setCellValueFactory(new PropertyValueFactory<Varaus, Double>("paVuokra"));
        col4_aloitusPvm.setCellValueFactory(new PropertyValueFactory<Varaus, LocalDate>("alkuPvm"));
        col5_paattymisPvm.setCellValueFactory(new PropertyValueFactory<Varaus, LocalDate>("loppuPvm"));
        col6_vuokranAlv.setCellValueFactory(new PropertyValueFactory<Varaus, Integer>("vuokraAlv"));
        col7_PalvAlv.setCellValueFactory(new PropertyValueFactory<Varaus, Integer>("paAlv"));
        col8_varausTehty.setCellValueFactory(new PropertyValueFactory<Varaus, LocalDateTime>("varausTehty"));
        col9_ToimistonID.setCellValueFactory(new PropertyValueFactory<Varaus, Integer>("toId"));
        col10_asiakasNro.setCellValueFactory(new PropertyValueFactory<Varaus, Integer>("asiakasNro"));
    }

    @FXML
    private void btn_listaaVuokratutTilat(ActionEvent event) {
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
        
        tblVuokratutTilat.getItems().clear();

        ArrayList<Varaus> lstVaraukset = null;

        try {
            lstVaraukset = Tietokanta.haeVaratutTilat(datepickerAloitusPvm.getValue(), datepickerPaattymisPvm.getValue(), txtToimipiste.getText());
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

            for (Varaus VuokratutTilat : lstVaraukset) {
                tblVuokratutTilat.getItems().add(VuokratutTilat);
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
    private void ntnHaeOnMouseExited(MouseEvent event) {
        btnListaa.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void btnHaeOnMouseEntered(MouseEvent event) {
        btnListaa.setStyle("-fx-background-color: #1e2e5a;");
    }

}
