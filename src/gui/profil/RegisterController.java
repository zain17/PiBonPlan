package gui.profil;

import gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable{

    @FXML private Hyperlink link_login;
    @FXML private TextField txt_user;
    @FXML private TextField txt_prenom;
    @FXML private TextField txt_email;
    @FXML private CheckBox chb_roleetab;
    @FXML private TextField txt_password;
    @FXML private CheckBox chb_roleclient;
    @FXML private TextField txt_confirmpass;
    @FXML private Button btn_enregistrer;
    @FXML private Button btn_annuler;
    private Main app;

    public void processLogout(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Main getApp() {
        return app;
    }

    public void setApp(Main main) {
        this.app = main;
    }
}
