package gui.profil;


import com.jfoenix.controls.*;
import entites.Etablissement;
import entites.Gouvernorat;
import entites.Utilisateur;
import entites.Ville;
import gui.Main;
import gui.Routers.RoutingGestionProfilContainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import security.Authenticator;
import services.EtablissementService;
import services.ServiceGouvernorat;
import services.ServiceVille;
import services.UtilisateurService;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.ArrayList;
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
        loadForm();
    }

    public void onAnnulerAjoutET(ActionEvent actionEvent) throws IOException {
        routingGestionProfilContainer.returnFromEtabVersProfile();
    }
    @FXML
    public void onAjoutEtab(ActionEvent actionEvent) {
        Etablissement etablissementAajouter = new Etablissement();
        readFormData(etablissementAajouter);
        EtablissementService etServ=new EtablissementService();
        System.out.println("BEOFRE PERSIST+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(etablissementAajouter);
        etServ.ajouter(etablissementAajouter);
        UtilisateurService us=new UtilisateurService();
        us.affectEtabToUser(Authenticator.getCurrentAuth(),etServ.getLastInsertedId());
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
    public void loadForm(){
        ServiceGouvernorat serviceGouvernorat=new ServiceGouvernorat();
        ArrayList<Gouvernorat> gouvernorats=serviceGouvernorat.selectAllEager();
        ArrayList<String>gouvernoratsStream=new ArrayList<>();
        gouvernorats.stream().forEach(gouvernorat -> gouvernoratsStream.add(gouvernorat.getName()));
        ArrayList<String>gouvernoratsStreamVilles=new ArrayList<>();
        ObservableList<String> gouvernoratObservableList = FXCollections.observableArrayList(gouvernoratsStream);
        ServiceVille serviceVille=new ServiceVille();
        cmb_gouv.setItems(gouvernoratObservableList);
        ArrayList<String> types=new ArrayList<String>();
        types.add("restaurant");types.add("shopping");types.add("cafe");types.add("loisirs");
        ObservableList<String> typeObser = FXCollections.observableArrayList(types);
        cmb_type.setItems(typeObser);
    }

    private boolean valideData() {
        if(!txt_nom.getText().isEmpty() &&!txt_adresse.getText().isEmpty()&&!cmb_gouv.getSelectionModel().getSelectedItem().toString().isEmpty()&&!cmb_ville.getSelectionModel().getSelectedItem().toString().isEmpty()&&!cmb_type.getSelectionModel().getSelectedItem().toString().isEmpty()){

            return true;

        }
        System.out.println("*************Data NOT VALID");
        return false;
    }
    @FXML
    public void onChangeSelection(ActionEvent actionEvent) {
        ServiceGouvernorat serviceGouvernorat=new ServiceGouvernorat();
        ServiceVille serviceVille=new ServiceVille();
        String gouvname=cmb_gouv.getSelectionModel().getSelectedItem().toString();
        ArrayList<Ville> villes=serviceVille.selectAllByGouvernorat(serviceGouvernorat.getGouvernoratIdByHerName(cmb_gouv.getSelectionModel().getSelectedItem().toString()));
        ArrayList<String>villesStream=new ArrayList<>();
        villes.stream().forEach(gouvernorat -> villesStream.add(gouvernorat.getName()));
        ArrayList<String>gouvernoratsStreamVilles=new ArrayList<>();
        ObservableList<String> villeObs = FXCollections.observableArrayList(villesStream);
        cmb_ville.setItems(villeObs);
    }
}
