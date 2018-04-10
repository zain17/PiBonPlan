package gui.profil;


import com.jfoenix.controls.*;
import gui.Main;
import gui.Routers.RoutingGestionProfilContainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FormEtablissementController implements Initializable {
    private Main application;
    RoutingGestionProfilContainer routingGestionProfilContainer= new RoutingGestionProfilContainer(this.application,this);
    @FXML private JFXButton btn_Anuuler;
    @FXML private JFXButton btn_ajouter;
    @FXML private AnchorPane container_formEtab;
    @FXML private JFXTextField txt_nom;
    @FXML private JFXTextArea txt_adresse;
    @FXML private JFXComboBox cmb_gouv;
    @FXML private JFXComboBox cmb_ville;
    @FXML private JFXComboBox cmb_type;
    @FXML private JFXTextField txt_lat;
    @FXML private JFXTextField txt_lang;
    @FXML private JFXTextField photo_frontale;
    @FXML private JFXTimePicker tpiker_fermer;
    @FXML private JFXTimePicker tpiker_ouvert;

    public FormEtablissementController() {
    }

    public AnchorPane getContainer_formEtab() {
        return container_formEtab;
    }

    public void setContainer_formEtab(AnchorPane container_formEtab) {
        this.container_formEtab = container_formEtab;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onAnnulerAjoutET(ActionEvent actionEvent) throws IOException {
        routingGestionProfilContainer.returnFromEtabVersProfile();
    }

    public void onAjoutEtab(ActionEvent actionEvent) {
    }
}
