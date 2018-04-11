/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.blog;

import entites.Article;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author aminos
 */
public class ArticleElementController implements Initializable {

    @FXML
    private Hyperlink titre;
    @FXML
    private Label author;
    @FXML
    private Label text;
    @FXML
    public Hyperlink modifier;
    @FXML
    public Hyperlink supprimer;

    private Article article;
    private AnchorPane listeContainer;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void setAuthor(String auth, String date) {
        author.setText("Crée par " + auth + " le " + date);
    }
    
    public void setTitre(String titre) {
        this.titre.setText(titre);
    }
    
    public void setText(String text) {
        this.text.setText(text);
    }

    public void setListContainer(AnchorPane listeContainer) {
        this.listeContainer = listeContainer;
    }
    @FXML
    private void loadmodifierArticle(ActionEvent event) throws IOException {
        URL res = getClass().getResource("/gui/blog/modifierArticle.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(res);
          AnchorPane toInsert = (AnchorPane) fxmlLoader.load();
        ModifierArticleController c = (ModifierArticleController)fxmlLoader.getController();
        System.out.println("ArticleNull ? "  + this.article != null);
        System.out.println("ControllerNull ? " + c != null);
        c.setArticle(this.article);
        c.setEditorText(this.article.getTexte());
        c.setTitreText(this.article.getTitre());
      
       listeContainer.getChildren().setAll(toInsert);
    }

    @FXML
    private void loadsupprimerArticle(ActionEvent event) {
    }

    void setPaginatorParent(AnchorPane paginatorParent) {
       this.listeContainer = paginatorParent;
    }

    @FXML
    private void lireArticle(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/blog/lireArticle.fxml"));
        AnchorPane forInserting  = (AnchorPane) fxmlLoader.load();
        LireArticleController c = (LireArticleController) fxmlLoader.getController();
        c.setArticle(this.article);
        listeContainer.getChildren().setAll(forInserting);
        
        
    }
    
}
