package gui.Routers;

import gui.*;
import gui.profil.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoutingGestionProfilContainer {
    private Main main;
    private ProfileController currentProfileController;
    private FormUtilisateur currentFormUtilisateur;
    //private FormEtablissementController currentFormEtablissement;

    public RoutingGestionProfilContainer(Main main, ProfileController currentProfileController) {
        this.main = main;
        this.currentProfileController = currentProfileController;
    }

    public RoutingGestionProfilContainer(Main main, FormUtilisateur cc) {
        this.main = main;
        this.currentFormUtilisateur=cc;
    }

//    public RoutingGestionProfilContainer(Main application, FormEtablissementController formEtablissementController) {
//        this.main = main;
//        this.currentFormEtablissement=formEtablissementController;
//    }

    public void returnFromEdit() throws IOException {
            currentFormUtilisateur.getConteneurProfil().getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("/gui/profil/profile.fxml")));

    }

    public void profileToEtablissement() throws IOException {
        currentProfileController.getContneurProfil().getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("/gui/profil/formEtablissement.fxml")));
    }
}
