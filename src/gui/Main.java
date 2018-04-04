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

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    private Stage stage;
    private Utilisateur loggedUser;
    private RoutingGestionProfil routGP=new RoutingGestionProfil(this);
    private RoutingBlog routeBlog = new RoutingBlog(this);
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            stage.setTitle("Bienvenue à Bon Plan");
            stage.setMinWidth(300);
            stage.setMinHeight(500);
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

    public boolean userLogging(String userIdentity, String password){
        if (Authenticator.validate(userIdentity, password)) {
            loggedUser = Utilisateur.of(userIdentity);
            routGP.gotoListUser();
            //gotoProfile();
            return true;
        } else {
            return false;
        }
    }

    void userLogout(){
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
        Scene scene = new Scene(page, 800, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
}
