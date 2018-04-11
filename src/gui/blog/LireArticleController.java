/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.blog;

import entites.Article;
import entites.CommentaireB;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import services.CommentaireBService;

/**
 * FXML Controller class
 *
 * @author aminos
 */

public class LireArticleController implements Initializable {

    @FXML
    TextArea commentaire;
    @FXML
    WebView texte;
    @FXML
    Label titre;
    
    private Article article;
    @FXML
    private AnchorPane blogWidget;
    @FXML
    private Button commentButton;
    @FXML
    private ScrollPane commentaires;

    public Article getArticle() {
        return article;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setArticle(Article article) {
        this.article = article;
        texte.getEngine().loadContent(this.article.getTexte());
        titre.setText(this.article.getTitre());
        VBox box = new VBox(3);
        CommentaireBService cS = new CommentaireBService();
        for (CommentaireB c : cS.findByArticle(article)) {
            box.getChildren().add(new Label(c.getText()));
        }
        commentaires.setContent(box);
        
        
    }

    @FXML
    private void creerCommentaire(ActionEvent event) {
        CommentaireB com  = new CommentaireB(commentaire.getText(), this.article.getAuteurn(), this.article.getAuteur());
        com.setArticle(article);
        CommentaireBService cS = new CommentaireBService();
        cS.ajouter(com);
        commentaire.clear();
    }
    
}
