/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.blog;

import entites.Article;
import entites.Tag;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import services.ArticleService;

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
    private VBox boxParent;
    @FXML
    private VBox directParent;
    private BlogContainerController blogController;
    public ListeArticlesController listeArtC;

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
    public void loadmodifierArticle(ActionEvent event) throws IOException {
        URL res = getClass().getResource("/gui/blog/modifierArticle.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(res);
        AnchorPane toInsert = (AnchorPane) fxmlLoader.load();
        ModifierArticleController c = (ModifierArticleController) fxmlLoader.getController();
        System.out.println("ArticleNull ? " + this.article != null);
        System.out.println("ControllerNull ? " + c != null);
        c.setArticle(this.article);
        c.setEditorText(this.article.getTexte());
        c.setTitreText(this.article.getTitre());
        c.blogController = this.blogController;

        listeContainer.getChildren().setAll(toInsert);
    }

    @FXML
    private void loadsupprimerArticle(ActionEvent event) throws Exception {
        ArticleService aS = new ArticleService();
        aS.supprimer(article);
        this.directParent.setVisible(false);
        this.directParent.setManaged(false);
        this.blogController.listeArticleAction(event);
    }

    void setPaginatorParent(AnchorPane paginatorParent) {
        this.listeContainer = paginatorParent;
    }

    @FXML
    private void lireArticle(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/blog/lireArticle.fxml"));
        AnchorPane forInserting = (AnchorPane) fxmlLoader.load();
        LireArticleController c = (LireArticleController) fxmlLoader.getController();
        c.setArticle(this.article);
        c.blogController = blogController;
        System.out.println("ListC " + this.listeArtC);
        ArticleService aS = new ArticleService();
        //System.out.println(aS.getTags(list.get(i)));
        //System.out.println(c.listeArtC);
        ArrayList<Tag> tagList = aS.getTags(this.article);
        if (tagList != null) {
            if (tagList.size()>0) {
            this.listeArtC.tags.setVisible(true);
            this.listeArtC.tags.setManaged(true);
             this.listeArtC.tagLabs.getChildren().clear();
            }
        }

        for (Tag t : aS.getTags(this.article)) {
            System.out.println(t);
            FXMLLoader tagsLoader = new FXMLLoader(getClass().getResource("/gui/blog/tag.fxml"));
            VBox tags = tagsLoader.load();
            TagController tC = tagsLoader.getController();
            tC.blogContainer = this.blogController;
            tC.listeContainer = this.listeContainer;
            tC.listArtC = this.listeArtC;
            tC.tg = t;
                  
            tC.tag.setText(t.getName());
           
            this.listeArtC.tagLabs.getChildren().addAll(tags);

        }//ArticleService aS = new ArticleService();
        //System.out.println(aS.getTags(list.get(i)));
        //System.out.println(c.listeArtC);
        /*
            for (Tag t : aS.getTags(list.get(i))) {
            FXMLLoader tagsLoader = new FXMLLoader(getClass().getResource("/gui/blog/tag.fxml"));
            VBox tags = tagsLoader.load();
            TagController tC = tagsLoader.getController();
            tC.blogContainer = this.blogContainerController;
            tC.listeContainer = this.paginatorParent;
            tC.tag.setText(t.getName());
              c.listeArtC.tagLabs.getChildren().add(tags);
            }
         */

        listeContainer.getChildren().setAll(forInserting);

    }

    void setBoxParent(VBox box) {
        this.boxParent = box;
    }

    void setBlogController(BlogContainerController blogContainerController) {
        this.blogController = blogContainerController;
    }

    void setArtC(ListeArticlesController listC) {
        this.listeArtC = listC;
    }

}
