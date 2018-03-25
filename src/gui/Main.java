package gui;

import entites.Utilisateur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import security.Authenticator;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    private Stage stage;
    private Utilisateur loggedUser;
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            stage.setTitle("Bienvenue à Bon Plan");
            stage.setMinWidth(300);
            stage.setMinHeight(500);
            gotoListEtablissement();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[])null);
    }
    public Utilisateur getLoggedUser() {
        return loggedUser;
    }

    public boolean userLogging(String userIdentity, String password){
        if (Authenticator.validate(userIdentity, password)) {
            loggedUser = Utilisateur.of(userIdentity);
            gotoProfile();
            return true;
        } else {
            return false;
        }
    }

    void userLogout(){
        loggedUser = null;
        gotoLogin();
    }

    private void gotoProfile() {
        try {
            ProfileController profile = (ProfileController) replaceSceneContent("profile.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void gotoListEtablissement(){
        try {
            ListetablissementController lstEtabC = (ListetablissementController) replaceSceneContent("/gui/listetablissement.fxml");
            //Charger le controlleur dans l'application
            lstEtabC.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, 800, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
}
