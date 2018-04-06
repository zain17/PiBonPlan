/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.blog;

import entites.Article;
import entites.Tag;
import gui.Main;
import gui.Routers.RoutingGestionProfil;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import services.ArticleService;
import services.TagService;

/**
 * FXML Controller class
 *
 * @author aminos
 */

public class AjouterArticleController implements Initializable {

     public Main app;
    @FXML
    private Button sauvegarderArticle;
    @FXML
    private HTMLEditor texteArticle;
    @FXML
    private TextField titreArticle;
    @FXML
    private TextField tagsTexte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void enregistrerArticle(ActionEvent event) {
        ArticleService aS = new ArticleService();
        TagService tS = new TagService();
        String texte = texteArticle.getHtmlText();
        Document doc = Jsoup.parseBodyFragment(texte);
        
        String htmlText = doc.body().html();
        String tagsStr = tagsTexte.getText();
        String titre = titreArticle.getText();
        ArrayList<Tag> tagsArray = null;
        if (tS.isValidTags(tagsStr)) {
            System.out.println("TAgj" + tS.tagsStringToTags(tagsStr).size());
            tagsArray = tS.tagsStringToTags(tagsStr);
        }
        //System.out.println("Debug AjoutA***" + (this.app != null));
                 //   Logger.getagsStrtLogger(AjouterArticleController.class.getName()).log(Level.SEVERE, null, this.app != null);

        Article a = new Article(htmlText, 0, titre, 3, "admin", new Date(), new Date());
        aS.ajouter(a);
        if (tagsArray != null && ! tagsArray.isEmpty()) {
            for (Tag t : tagsArray) {
                tS.ajouter(t, a.getId());
                System.out.println("Tag: " + t.getName());
            }
        }
       
      
    }
    
       

    public void setApp(Main app) {
        this.app = app;
    }
}
