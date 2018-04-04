/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.blog;

import gui.Main;
import gui.Routers.RoutingGestionProfil;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * FXML Controller class
 *
 * @author aminos
 */

public class AjouterArticleController implements Initializable {

     private Main app;
    @FXML
    private Button sauvegarderArticle;
    @FXML
    private HTMLEditor texteArticle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void enregistrerArticle(ActionEvent event) {
        String texte = texteArticle.getHtmlText();
        Document doc = Jsoup.parseBodyFragment(texte);
        
        String htmlText = doc.body().html();
        RoutingGestionProfil rGP = new RoutingGestionProfil(this.app);
        
        rGP.gotoProfile();
      
    }
    
       

    public void setApp(Main app) {
        this.app = app;
    }
}
