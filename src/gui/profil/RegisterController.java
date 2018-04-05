package gui.profil;

import entites.Utilisateur;
import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable{
    @FXML private CheckBox chb_roleClient;
    @FXML private CheckBox chb_roleEtab;
    @FXML private Hyperlink link_login;
    @FXML private TextField txt_user;
    @FXML private TextField txt_prenom;
    @FXML private TextField txt_email;
    @FXML private TextField txt_password;
    @FXML private TextField txt_confirmpass;
    @FXML private Button btn_enregistrer;
    @FXML private Button btn_annuler;
    private Main app;
    private Utilisateur userToSave;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userToSave=new Utilisateur();
    }

    public Main getApp() {
        return app;
    }

    public void setApp(Main main) {
        this.app = main;
    }

    public void onClickEnregistrer(ActionEvent actionEvent) {
        if (dataValidation()) {
            userToSave.setUsername(txt_prenom.getText());
            userToSave.setUsernameCanonical(txt_user.getText());
            userToSave.setEmail(txt_email.getText());
//            userToSave.set
        }
    }

    public void onAnnuller(ActionEvent actionEvent) {

    }
    public boolean dataValidation(){
        // TODO: Warning - Cette méthode valide tous les donnés taper dans le formulaire (aucun passage sauf qu'elle return true)
        return true;
    }
}
