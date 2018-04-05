package gui.Routers;

import gui.*;
import gui.profil.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RoutingGestionProfil {
    private Main main;
    public RoutingGestionProfil(Main main) {
        this.main = main;
    }

    public void gotoProfile() {
        try {
            ProfileController profile = (ProfileController) main.replaceSceneContent("/gui/profil/profile.fxml");
            profile.setApp(main);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotoListEtablissement(){
        try {
            ListetablissementController lstEtabC = (ListetablissementController)main.replaceSceneContent("/gui/profil/listetablissement.fxml");
            lstEtabC.setApp(main);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotoListUser(){
        try {
            ListUtilisateursController lstUserC = (ListUtilisateursController) main.replaceSceneContent("/gui/profil/listutilisateurs.fxml");
            lstUserC.setApp(main);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotoLogin() {
        try {
            LoginController login = (LoginController) main.replaceSceneContent("/gui/profil/login.fxml");
            login.setApp(main);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotoRegistration() {
        try {
            RegisterController login = (RegisterController) main.replaceSceneContent("/gui/profil/register.fxml");
            login.setApp(main);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
