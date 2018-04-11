/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.blog;

import entites.Article;
import entites.CommentaireB;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import security.Authenticator;
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
            commenT.setText(commentaire.getText());

            cS.modifier(commenT);
            try {
                cS.con.commit();
                cMTxt.setText(commentaire.getText());
            } catch (SQLException ex) {
                Logger.getLogger(LireArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }
            commenT = null;
            updating = false;
            cMTxt = null;

        } else {
            CommentaireB com = new CommentaireB(commentaire.getText(), Authenticator.getCurrentAuth().getUsername(), Authenticator.getCurrentAuth().getId());
            com.setArticle(article);

            int cId = cS.ajouter(com);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/blog/commentaire.fxml"));
            VBox element;

            element = loader.load();

            CommentaireController cc = (CommentaireController) loader.getController();
            cc.setArticle(article, this, cS.find(cId));
            box.getChildren().add(element);
        }
        commentaire.clear();

    }

}
