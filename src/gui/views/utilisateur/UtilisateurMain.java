package gui.views.utilisateur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class UtilisateurMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader root=FXMLLoader.load(this.getClass().getResource("gui/views/utilisateur/formulaire.fxml"));

    }
}
