package gui.profil;

import entites.Utilisateur;
import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import security.FOSJCrypt;
import security.Sha512;
import services.UtilisateurService;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class RegisterController implements Initializable{
    @FXML private CheckBox chb_roleClient;
    @FXML private CheckBox chb_roleEtab;
    @FXML private Hyperlink link_login;
    @FXML private TextField txt_nom;
    @FXML private TextField txt_prenom;
    @FXML private TextField txt_email;
    @FXML private PasswordField txt_password;
    @FXML private PasswordField txt_confirmpass;
    @FXML private Button btn_enregistrer;
    @FXML private Button btn_annuler;
    private Main app;
    private Utilisateur userToSave;
    private UtilisateurService usserv=new UtilisateurService();


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
            userToSave.setUsernameCanonical(txt_nom.getText());
            userToSave.setEmail(txt_email.getText());
            userToSave.setEmailCanonical(txt_email.getText());
            String pwclair=txt_confirmpass.getText();
            Sha512 sha512=FOSJCrypt.crypt(pwclair);
            userToSave.setSalt(sha512.getSalt());
            userToSave.setPassword(sha512.getHash());
            userToSave.setLastLogin(new Date());
            if(chb_roleClient.isSelected()) {
                userToSave.setRoles("ROLE_CLIENT");
            }
            if(chb_roleEtab.isSelected()) {
                userToSave.setRoles("ROLE_ETABLISSEMENT");
            }
            userToSave.setEnabled(new Short("1"));
            usserv.ajouter(userToSave);
        }else
            System.out.println("DATA not valid");
    }

    public void onAnnuller(ActionEvent actionEvent) {

    }
    public boolean dataValidation(){
        // TODO: Warning - Cette méthode valide tous les donnés taper dans le formulaire (aucun passage sauf qu'elle return true)
        //if(txt_password.getText()!=txt_confirmpass.getText())return false;
        return true;
    }
}
