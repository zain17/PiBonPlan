package gui.profil;

import entites.Utilisateur;
import gui.Main;
import gui.Routers.RoutingGestionProfilContainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import security.Authenticator;
import security.FOSJCrypt;
import security.Sha512;
import services.UtilisateurService;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class FormUtilisateur implements Initializable {
    RoutingGestionProfilContainer routingGestionProfilContainer= new RoutingGestionProfilContainer(this.app,this);
    @FXML
    private  AnchorPane conteneurProfil;
    @FXML
    private ToggleButton etat_AD;
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
    private UtilisateurService usserv=new UtilisateurService();
    public void setApp(Main app) {
        this.app = app;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Utilisateur currentUserInfo=Authenticator.getCurrentAuth();
        loadUserInfo(currentUserInfo);

    }
    public void loadUserInfo(Utilisateur u){
        UtilisateurService usev=new UtilisateurService();
        txt_nom.setText(u.getUsername());
        txt_prenom.setText(u.getUsername());
        txt_email.setText(u.getEmail());
        txt_password.setText("");
        txt_confirmpass.setText("");
        if(u.getRoles().equals("ROLE_CLIENT"))
            chb_roleClient.setSelected(true);
        else chb_roleEtab.setSelected(true);
    }
    public void readNewInfoUser(Utilisateur u) throws IOException {
        if (dataValidation()) {
            u.setUsername(txt_prenom.getText());
            u.setUsernameCanonical(txt_nom.getText());
            u.setEmail(txt_email.getText());
            u.setEmailCanonical(txt_email.getText());
            String pwclair=txt_confirmpass.getText();
            if(txt_confirmpass.getText()!="" && txt_password.getText()!=""&&txt_password.getText().equals(txt_confirmpass.getText())) {
                Sha512 sha512 = FOSJCrypt.crypt(pwclair);
                u.setSalt(sha512.getSalt());
                u.setPassword(sha512.getHash());
            }
            u.setLastLogin(new Date());
            if(chb_roleClient.isSelected()) {
                u.setRoles("ROLE_CLIENT");
            }
            if(chb_roleEtab.isSelected()) {
                u.setRoles("ROLE_ETABLISSEMENT");
            }
            u.setEnabled(new Short("1"));
            if(etat_AD.isSelected())
                u.setEnabled((short) 0);
                usserv.modifier(app.getLoggedUser().getId(),u);
              routingGestionProfilContainer.returnFromEdit();
        }else
            System.out.println("DATA not valid");

        //app.userLogout();
    }

    private boolean dataValidation() {
        return true;
    }


    public void onClickEnregistrer(ActionEvent actionEvent) {
        try {
            readNewInfoUser(app.getLoggedUser());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onAnnuller(ActionEvent actionEvent) {
    }
    public void gotoProfile() throws IOException {
        conteneurProfil.getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("/gui/profil/profile.fxml")));
    }

    public AnchorPane getConteneurProfil() {
        return conteneurProfil;
    }

    public void setConteneurProfil(AnchorPane conteneurProfil) {
        this.conteneurProfil = conteneurProfil;
    }
}
