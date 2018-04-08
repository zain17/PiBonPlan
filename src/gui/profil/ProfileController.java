package gui.profil;

import entites.Utilisateur;
import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import services.UtilisateurService;

import javax.annotation.PostConstruct;
import java.net.Authenticator;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
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

    public ProfileController() {

    }
    public void setApp(Main app) {
        System.out.println("setApp prompt");
        this.app = app;
    }
    public void processLogout(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

      //  System.out.println(app.getLoggedUser());
        System.out.println("initialize prompt");

        loadUserInfo(security.Authenticator.getCurrentAuth());
    }
    public void loadUserInfo(Utilisateur u){
        UtilisateurService usev=new UtilisateurService();
        lbl_nom.setText(u.getUsername());
        lbl_prenom.setText(u.getUsername());
        lbl_email.setText(u.getEmail());
        lbl_nbEvents.setText(String.valueOf(usev.nbEvents(u.getId())));
        lbl_nbExperience.setText(String.valueOf(usev.nbExperiences(u.getId())));
        lbl_nbRevue.setText(String.valueOf(usev.nbRevues(u.getId())));
    }

}
