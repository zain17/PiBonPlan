package gui.profil;

import com.jfoenix.controls.*;
import entites.Etablissement;
import entites.Gouvernorat;
import entites.Ville;
import gui.Main;
import gui.Routers.RoutingGestionProfilContainer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import security.Authenticator;
import services.EtablissementService;
import services.ServiceGouvernorat;
import services.ServiceVille;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    public AnchorPane getContainer_formEtabEdit() {
        return container_formEtabEdit;
    }

    public void setContainer_formEtabEdit(AnchorPane container_formEtabEdit) {
        this.container_formEtabEdit = container_formEtabEdit;
    }

    public FormEtablissementEditController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EtablissementService es=new EtablissementService();
        Etablissement etablissement=es.selectOne(Authenticator.getCurrentAuth().getEtablissement().getId());
        loadFormData(etablissement);
    }
    @FXML
    public void onAnnulerModif(ActionEvent actionEvent) throws IOException {
        routingGestionProfilContainer.returnFromEditEtabVersProfile();

    }
    @FXML
    public void onModifEtab(ActionEvent actionEvent) throws IOException {
        EtablissementService ets=new EtablissementService();
        Etablissement etabTomodif=new Etablissement();
        etabTomodif=Authenticator.getCurrentAuth().getEtablissement();
        readFormData(etabTomodif);
        ets.modifier(etabTomodif.getId(),etabTomodif);
        routingGestionProfilContainer.returnFromEditEtabVersProfile();
    }

    public void loadFormData(Etablissement etabuser){
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
        txt_nom.setText(etabuser.getNom());
        txt_adresse.setText(etabuser.getAdresse());
        cmb_gouv.getSelectionModel().select(etabuser.getGouvernorat().toString());
        ArrayList<String> villess=new ArrayList<String>();
        villess.add(etabuser.getVille());
        ObservableList<String> vils = FXCollections.observableArrayList(villess);
        cmb_ville.setItems(vils);
        cmb_ville.getSelectionModel().select(0);
        ArrayList<String> typesS=new ArrayList<String>();
        typesS.add(etabuser.getType());
        ObservableList<String> typeObserSS = FXCollections.observableArrayList(typesS);
        cmb_type.setItems(typeObserSS);
        cmb_type.getSelectionModel().select(0);
        //cmb_type.setItems().select(etabuser.getType().toString());
        txt_lat.setText(etabuser.getLatitude().toString());
        txt_lang.setText(etabuser.getLongitude().toString());
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
    @FXML
    public void loadTypes(MouseEvent mouseEvent) {
                cmb_type.getSelectionModel().clearSelection();
        ArrayList<String> types=new ArrayList<String>();
        types.add("restaurant");types.add("shopping");types.add("cafe");types.add("loisirs");
        ObservableList<String> typeObser = FXCollections.observableArrayList(types);
        cmb_type.setItems(typeObser);
    }
}
