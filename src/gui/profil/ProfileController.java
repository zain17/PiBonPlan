package gui.profil;

import entites.Utilisateur;
import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
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


    public void setApp(Main app) {
        System.out.println("FIRST ONE ?");
        this.app = app;
        userHand = app.getLoggedUser();
        System.out.println("userhand"+userHand);
    }
    public void processLogout(ActionEvent actionEvent) {
        System.out.println(app.getLoggedUser());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("SECOND ONE ?");
        System.out.println(userHand);
    }
}
