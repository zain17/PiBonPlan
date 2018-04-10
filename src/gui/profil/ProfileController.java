package gui.profil;

import entites.Utilisateur;
import gui.Main;
import gui.Routers.RoutingGestionProfilContainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import services.UtilisateurService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.Authenticator;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    RoutingGestionProfilContainer routingGestionProfilContainer= new RoutingGestionProfilContainer(this.app,this);
    @FXML
    private AnchorPane contneurProfil;
    @FXML
    private  Label lbl_nbExperience;
    @FXML
    private  Label lbl_nbRevue;
    @FXML
    private  Label lbl_nbEvents;
    @FXML
    private  Hyperlink H_gotoMyEtab;
    @FXML
    private Label lbl_nometablissement;
    @FXML
    private Label lbl_nom;
    @FXML
    private Label lbl_email;
    @FXML
    private Label lbl_prenom;
    @FXML
    private ImageView image_photoprofil;
    private Main app;
    private Utilisateur userHand;

    public ProfileController() throws IOException {

    }

    public AnchorPane getContneurProfil() {
        return contneurProfil;
    }

    public void setContneurProfil(AnchorPane contneurProfil) {
        this.contneurProfil = contneurProfil;
    }

    public void setApp(Main app) {
        System.out.println("setApp prompt");
        this.app = app;
    }
    public void processLogout(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUserInfo(security.Authenticator.getCurrentAuth());
    }
    public void loadUserInfo(Utilisateur u){
        UtilisateurService usev=new UtilisateurService();
        boolean hasEtablissement=usev.hasEtablissement(u.getId());
        if(u.getRoles().equals("ROLE_ETABLISSEMENT")){
            if(!hasEtablissement){
                H_gotoMyEtab.setText("Voir l'établissement");
            }
            else
                H_gotoMyEtab.setText("Créer mon établissement");
        }
        if(u.getRoles().equals("ROLE_CLIENT")){
            H_gotoMyEtab.setVisible(false);
        }
        lbl_nom.setText(u.getUsername());
        lbl_prenom.setText(u.getPrenom());
        lbl_email.setText(u.getEmail());
        lbl_nbEvents.setText(String.valueOf(usev.nbEvents(u.getId())));
        lbl_nbExperience.setText(String.valueOf(usev.nbExperiences(u.getId())));
        lbl_nbRevue.setText(String.valueOf(usev.nbRevues(u.getId())));
    }
    @FXML
    public void voirEtablissement(ActionEvent actionEvent) throws IOException {
        if(H_gotoMyEtab.getText().equals("Voir l'établissement"))
        routingGestionProfilContainer.profileToEtablissementEdit();
        if(H_gotoMyEtab.getText().equals("Créer mon établissement"))
            routingGestionProfilContainer.profileToEtablissement();

    }

}
