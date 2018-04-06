/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.blog;

import gui.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import services.ArticleService;

/**
 * FXML Controller class
 *
 * @author aminos
 */
public class RechercherArticleController implements Initializable {

    @FXML
    private TextField rech;
    @FXML
    private Label resultat;
    private Main app;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void rechercherArticles(KeyEvent event) {
        resultat.setText("" + (new ArticleService()).find(rech.getText()).size());
    }

    public void setApp(Main app) {
       this.app = app;
    }
    
}
