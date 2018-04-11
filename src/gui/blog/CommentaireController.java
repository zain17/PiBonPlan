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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import security.Authenticator;
import services.CommentaireBService;

/**
 * FXML Controller class
 *
 * @author aminos
 */
public class CommentaireController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private Hyperlink modifier;
    @FXML
    private Hyperlink supprimer;
    @FXML
    private Text text;
    @FXML
    private VBox root;
    private Article article;
    private CommentaireB commentaire;
    private LireArticleController con;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     VBox.setVgrow(text, Priority.ALWAYS);
     root.setMinHeight(VBox.USE_PREF_SIZE);

    }    

    @FXML
    private void modifierCommentaire(ActionEvent event) {
        this.con.commentaire.setText(this.commentaire.getText());
        this.con.cMTxt = text;
        this.con.commenT = this.commentaire;
        System.out.println("CommentCont , modif "  + this.con.commenT.getId());
        this.con.updating = true;
    }

    @FXML
    private void supprimerCommentaire(ActionEvent event) {
        CommentaireBService cS = new CommentaireBService();
        cS.supprimer(commentaire);
        root.setVisible(false);
        root.setManaged(false);
    }
    
    public void setArticle(Article article, LireArticleController con, CommentaireB commentaire) {
        this.article = article;
        this.commentaire = commentaire;
        this.con = con;
        
        this.username.setText(commentaire.getAuteurn());
        if (!this.commentaire.getAuteurn().equals(Authenticator.getCurrentAuth().getUsername())) {
            modifier.setVisible(false);
            modifier.setManaged(false);
            supprimer.setVisible(false);
            supprimer.setManaged(false);
        }
        this.text.setText(commentaire.getText());
    }
    
}
