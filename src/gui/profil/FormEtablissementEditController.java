package gui.profil;

import com.jfoenix.controls.*;
import gui.Main;
import gui.Routers.RoutingGestionProfilContainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class FormEtablissementEditController implements Initializable {
    private Main application;
    RoutingGestionProfilContainer routingGestionProfilContainer= new RoutingGestionProfilContainer(this.application,this);
    @FXML private AnchorPane container_formEtabEdit;
    @FXML private JFXTimePicker tpiker_ouvert;
    @FXML private JFXTimePicker tpiker_fermer;
    @FXML private JFXTextField txt_nom;
    @FXML private JFXTextField photo_frontale;
    @FXML private JFXTextField txt_lang;
    @FXML private JFXTextField txt_lat;
    @FXML private JFXComboBox cmb_type;
    @FXML private JFXComboBox cmb_ville;
    @FXML private JFXComboBox cmb_gouv;
    @FXML private JFXTextArea txt_adresse;
    @FXML private JFXButton btn_Anuuler;
    @FXML private JFXButton btn_modifier;

    public FormEtablissementEditController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void onAnnulerModif(ActionEvent actionEvent) {
    }
    @FXML
    public void onModifEtab(ActionEvent actionEvent) {
    }
}
