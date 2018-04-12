package gui.Routers;

import gui.*;
import gui.profil.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class RoutingGestionProfilContainer {
    private Main main;
    private ProfileController currentProfileController;
    private FormUtilisateur currentFormUtilisateur;
    private FormEtablissementController currentFormEtablissement;
    private FormEtablissementEditController currentformEtablissementEditController;
    private ListetablissementController curreListEtab;

    public RoutingGestionProfilContainer(Main main, ListetablissementController curreListEtab) {
        this.main = main;
        this.curreListEtab = curreListEtab;
    }

    public RoutingGestionProfilContainer(Main main, ProfileController currentProfileController) {
        this.main = main;
        this.currentProfileController = currentProfileController;
    }

    public RoutingGestionProfilContainer(Main main, FormUtilisateur cc) {
        this.main = main;
        this.currentFormUtilisateur=cc;
    }

    public RoutingGestionProfilContainer(Main main, FormEtablissementController currentFormEtablissement) {
        this.main = main;
        this.currentFormEtablissement = currentFormEtablissement;
    }

    public RoutingGestionProfilContainer(Main application, FormEtablissementEditController formEtablissementEditController) {
        this.main = main;
        this.currentformEtablissementEditController = formEtablissementEditController;
    }

    public void returnFromEdit() throws IOException {
            currentFormUtilisateur.getConteneurProfil().getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("/gui/profil/profile.fxml")));
    }
    public void profileToEtablissement() throws IOException {
        currentProfileController.getContneurProfil().getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("/gui/profil/formEtablissement.fxml")));
    }
    public void profileToEtablissementEdit() throws IOException {
        currentProfileController.getContneurProfil().getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("/gui/profil/formEtablissementEdit.fxml")));
    }
    public void returnFromEditEtabVersProfile() throws IOException {
        currentformEtablissementEditController.getContainer_formEtabEdit().getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("/gui/profil/profile.fxml")));
    }
    public void returnFromEtabVersProfile() throws IOException {
        currentFormEtablissement.getContainer_formEtab().getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("/gui/profil/profile.fxml")));
    }
}
