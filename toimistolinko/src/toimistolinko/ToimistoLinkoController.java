package toimistolinko;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ToimistoLinkoController implements Initializable {

    @FXML
    private Button btnToimipisteet;
    @FXML
    private Button btnPalvelut;
    @FXML
    private Button btnToimTilaVaraukset;
    @FXML
    private Button btnAsiakashallinta;
    @FXML
    private Button btnLaskutus;
    @FXML
    private Button btnVuokratutTilat;
    @FXML
    private Button btnLispalvJaLait;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnToimipisteetNakyma(ActionEvent event) throws IOException {
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("ToimistoFXML.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
   
    }

    @FXML
    private void btnPalvelutNakyma(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("PalvelutFXML.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void btnAsiakashallintaNakyma(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AsiakasFXML.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void btnVuokratutTilatNakyma(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("VuokratutTilatFXML.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void btnLisapalvJaLaitteetNakyma(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("LisapalvelutJaLaitteetFXML.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void btnToimistotilavarauksetNakyma(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("VarausFXML.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void btnLaskutusNakyma(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("LaskuFXML.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void ToimipisteetOnMouseExited(MouseEvent event) {
        btnToimipisteet.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void ToimipisteetOnMouseEntered(MouseEvent event) {
        btnToimipisteet.setStyle("-fx-background-color: #1e2e5a;");
    }
    
    @FXML
    private void PalvelutOnMouseExited(MouseEvent event) {
        btnPalvelut.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void PalvelutOnMouseEntered(MouseEvent event) {
        btnPalvelut.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void ToimTilaVarauksetOnMouseExited(MouseEvent event) {
        btnToimTilaVaraukset.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void ToimTilaVarauksetOnMouseEntered(MouseEvent event) {
        btnToimTilaVaraukset.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void AsiakasOnMouseExited(MouseEvent event) {
        btnAsiakashallinta.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void AsiakasOnMouseEntered(MouseEvent event) {
        btnAsiakashallinta.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void LaskutusOnMouseExited(MouseEvent event) {
        btnLaskutus.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void LaskutusOnMouseEntered(MouseEvent event) {
        btnLaskutus.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void VuokratutTilatOnMouseExited(MouseEvent event) {
        btnVuokratutTilat.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void VuokratutTilatOnMouseEntered(MouseEvent event) {
        btnVuokratutTilat.setStyle("-fx-background-color: #1e2e5a;");
    }

    @FXML
    private void LispalvJaLaitOnMouseExited(MouseEvent event) {
        btnLispalvJaLait.setStyle("-fx-background-color: #304890;");
    }

    @FXML
    private void LispalvJaLaitOnMouseEntered(MouseEvent event) {
        btnLispalvJaLait.setStyle("-fx-background-color: #1e2e5a;");
    }

    
}
