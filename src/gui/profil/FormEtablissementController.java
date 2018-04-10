package gui.profil;


import com.jfoenix.controls.*;
import entites.Etablissement;
import gui.Main;
import gui.Routers.RoutingGestionProfilContainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import services.EtablissementService;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.Date;
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
        Etablissement etablissementAajouter = new Etablissement();
        readFormData(etablissementAajouter);
        EtablissementService etServ=new EtablissementService();
        System.out.println("BEOFRE PERSIST+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(etablissementAajouter);
        etServ.ajouter(etablissementAajouter);
    }
    public void readFormData(Etablissement etabToAdd) {
        if (valideData()){
            etabToAdd.setNom(txt_nom.getText());
        etabToAdd.setAdresse(txt_adresse.getText());
        etabToAdd.setGouvernorat(cmb_gouv.getSelectionModel().getSelectedItem().toString());
        etabToAdd.setVille(cmb_ville.getSelectionModel().getSelectedItem().toString());
        etabToAdd.setType(cmb_type.getSelectionModel().getSelectedItem().toString());
        etabToAdd.setLatitude(Double.valueOf(txt_lat.getText()));
        etabToAdd.setLongitude(Double.valueOf(txt_lang.getText()));
        }
    }

    private boolean valideData() {
        if(!txt_nom.getText().isEmpty() &&!txt_adresse.getText().isEmpty()&&!cmb_gouv.getSelectionModel().getSelectedItem().toString().isEmpty()&&!!cmb_ville.getSelectionModel().getSelectedItem().toString().isEmpty()&&!cmb_type.getSelectionModel().getSelectedItem().toString().isEmpty()){

            return true;

        }
        System.out.println("*************Data NOT VALID");
        return false;
    }
}
