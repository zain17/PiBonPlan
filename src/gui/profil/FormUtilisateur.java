package gui.profil;

import entites.Utilisateur;
import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.UtilisateurService;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ResourceBundle;

public class FormUtilisateur implements Initializable {
    private Main app;
    public CheckBox chb_roleClient;
    @FXML
    private Button btn_enregistrer;
    @FXML
    private  Button btn_annuler;
    @FXML
    private  TextField txt_nom;
    @FXML
    private  TextField txt_prenom;
    @FXML
    private  TextField txt_email;
    @FXML
    private  CheckBox chb_roleEtab;
    @FXML
    private  PasswordField txt_password;
    @FXML
    private  PasswordField txt_confirmpass;

    public void setApp(Main app) {
        this.app = app;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUserInfo(security.Authenticator.getCurrentAuth());
    }
    public void loadUserInfo(Utilisateur u){
        UtilisateurService usev=new UtilisateurService();
        txt_nom.setText(u.getUsername());
        txt_prenom.setText(u.getUsername());
        txt_email.setText(u.getEmail());
        txt_password.setText("");
        txt_confirmpass.setText("");
    }
    public void onClickEnregistrer(ActionEvent actionEvent) {

    }

    public void onAnnuller(ActionEvent actionEvent) {
    }
}
