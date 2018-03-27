package gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ListUtilisateursController implements Initializable{
    
    @FXML private TableView tabview_users;
    @FXML private TableColumn tabcol_id;
    @FXML private TableColumn tabcol_photo;
    @FXML private TableColumn tabcol_lang;
    @FXML private TableColumn tabcol_lat;
    @FXML private TableColumn tabcol_idetab;
    @FXML private TableColumn tabcol_nom;
    @FXML private TableColumn tabcol_prenom;
    @FXML private TableColumn tabcol_email;
    @FXML private TableColumn tabcol_lastlogin;
    @FXML private TableColumn tabcol_role;
    
    private Main app;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setApp(Main app) {
        this.app = app;
    }
}
