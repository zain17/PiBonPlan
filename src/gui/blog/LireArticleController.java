/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.blog;

import Util.Html2PDF;
import entites.Article;
import entites.CommentaireB;
import gui.Main;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;
import javafx.util.Duration;
import security.Authenticator;
import services.ArticleService;
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
    boolean updating = false;
    private VBox box;
    CommentaireB commenT;
    Text cMTxt;
    BlogContainerController blogController;
    @FXML
    private Hyperlink modifier;
    @FXML
    private Hyperlink supprimer;
    public Window app;
    ListeArticlesController listeArtC;

    public Article getArticle() {
        return article;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updating = false;
    }

    void setArticle(Article article) throws IOException {
        this.article = article;

        texte.getEngine().loadContent(this.article.getTexte());
        titre.setText(this.article.getTitre());
        if (!Authenticator.getCurrentAuth().getUsername().equals(this.article.getAuteurn())) {
            modifier.setVisible(false);
            modifier.setManaged(false);
            supprimer.setVisible(false);
            supprimer.setManaged(false);
        }
        box = new VBox(3);
        box.setMaxSize(VBox.USE_PREF_SIZE, VBox.USE_PREF_SIZE);
        box.setStyle("-fx-background-color: white;");
        CommentaireBService cS = new CommentaireBService();
        for (CommentaireB c : cS.findByArticle(article)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/blog/commentaire.fxml"));
            VBox element;

            element = loader.load();
            CommentaireController cc = (CommentaireController) loader.getController();
            cc.setArticle(article, this, c);
            box.getChildren().add(element);
        }
        commentaires.setContent(box);

    }

    @FXML
    private void creerCommentaire(ActionEvent event) throws IOException {
        CommentaireBService cS = new CommentaireBService();
        if (commenT != null && updating && cMTxt != null) {
            if (commentaire.getText().length() != 0) {
                commenT.setText(commentaire.getText());

                cS.modifier(commenT);
                cMTxt.setText(commentaire.getText());

                commenT = null;
                updating = false;
                cMTxt = null;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Commentaire vide!", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
            }
        } else {
            if (commentaire.getText().length() != 0) {
                CommentaireB com = new CommentaireB(commentaire.getText(), Authenticator.getCurrentAuth().getUsername(), Authenticator.getCurrentAuth().getId());
                com.setArticle(article);

                int cId = cS.ajouter(com);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/blog/commentaire.fxml"));
                VBox element;

                element = loader.load();

                CommentaireController cc = (CommentaireController) loader.getController();
                cc.setArticle(article, this, cS.find(cId));
                box.getChildren().add(element);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Commentaire vide!", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
            }
        }
        commentaire.clear();

    }

    @FXML
    private void modifierArticle(ActionEvent event) throws IOException {
        URL res = getClass().getResource("/gui/blog/modifierArticle.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(res);
        AnchorPane toInsert = (AnchorPane) fxmlLoader.load();
        ModifierArticleController c = (ModifierArticleController) fxmlLoader.getController();
        System.out.println("ArticleNull ? " + this.article != null);
        System.out.println("ControllerNull ? " + c != null);
        c.setArticle(this.article);
        c.setEditorText(this.article.getTexte());
        c.setTitreText(this.article.getTitre());

        blogWidget.getChildren().setAll(toInsert);
    }

    @FXML
    private void supprimerArticle(ActionEvent event) throws Exception {
        ArticleService aS = new ArticleService();
        aS.supprimer(article);
        FXMLLoader fXMlLoader = new FXMLLoader(getClass().getResource("/gui/blog/listeArticles.fxml"));
        AnchorPane childrenContent = fXMlLoader.load();
        ListeArticlesController c = (ListeArticlesController) fXMlLoader.getController();

        ArrayList<Article> list = aS.findAll();

//        System.out.println("BlogContainerController, 52 " + list.size() + "titre " + list.get(0).getTexte());
        Paginator p = new Paginator(list, 3, c, this.blogController);
        c.setPaginatorContainer(p.getPaginator(), this.blogController);

        setNode(childrenContent);
        //this.blogController.listeArticleAction(event);
    }

    public void setNode(Node node) {
        blogWidget.getChildren().clear();
        blogWidget.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void exporterPDF(ActionEvent event) {
        try {
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Choisir dossier de destination");
            File defaultDirectory = new File(".");
            chooser.setInitialDirectory(defaultDirectory);
            System.out.println(this.blogController.app != null);
            File selectedDirectory = chooser.showDialog(Main.stage);
            String html = "<h1>" + this.article.getTitre() + "</h1>" + "<div>" + this.article.getTexte() + "</div>";
            Html2PDF.convert(html, selectedDirectory.getAbsolutePath() + "/" + LocalDate.now() + ".pdf");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LireArticleController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LireArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
