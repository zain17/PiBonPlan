package gui;

import entites.Utilisateur;
import gui.Routers.RoutingBlog;
import gui.Routers.RoutingGestionProfil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import security.Authenticator;
import services.UtilisateurService;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    public static Stage stage;
    private Utilisateur loggedUser=new Utilisateur();
    private RoutingGestionProfil routGP=new RoutingGestionProfil(this);
    private RoutingBlog routeBlog = new RoutingBlog(this);
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            stage.setTitle("Bienvenue à Bon Plan");
//            stage.setWidth(500);
//            stage.setHeight(300);
            routGP.gotoLogin();
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

    public void setLoggedUser(Utilisateur loggedUser) {
        this.loggedUser = loggedUser;
    }

    public boolean userLogging(String userIdentity, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UtilisateurService us=new UtilisateurService();
        if (Authenticator.validate(userIdentity, password)) {
            loggedUser=Authenticator.getCurrentAuth();
            this.stage.setHeight(700);
            this.stage.setWidth(1000);
            this.stage.setResizable(false);
            routeBlog.gotoContainer();
            return true;
        } else {
            return false;
        }
    }

    public void userLogout(){
        loggedUser = null;
        routGP.gotoLogin();
    }
    public Initializable replaceSceneContent(String fxml) throws Exception {
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
        Scene scene = new Scene(page, 1000, 700);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
}