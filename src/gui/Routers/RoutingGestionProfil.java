package gui.Routers;

import gui.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RoutingGestionProfil {
    private Main main;

    public RoutingGestionProfil(Main main) {
        this.main = main;
    }

    public void gotoProfile() {
        try {
            ProfileController profile = (ProfileController) main.replaceSceneContent("profile.fxml");
            profile.setApp(main);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotoListEtablissement(){
        try {
            ListetablissementController lstEtabC = (ListetablissementController)main.replaceSceneContent("/gui/listetablissement.fxml");
            //Charger le controlleur dans l'application
            lstEtabC.setApp(main);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotoListUser(){
        try {
            ListUtilisateursController lstUserC = (ListUtilisateursController) main.replaceSceneContent("/gui/listutilisateurs.fxml");
            //Charger le controlleur dans l'application
            lstUserC.setApp(main);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotoLogin() {
        try {
            LoginController login = (LoginController) main.replaceSceneContent("login.fxml");
            login.setApp(main);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
