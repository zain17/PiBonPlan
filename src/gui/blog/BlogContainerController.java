/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.blog;

import gui.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author aminos
 */
public class BlogContainerController implements Initializable {

    public Main app;
    @FXML
    private AnchorPane contained;
    @FXML
    private AnchorPane blogWidget;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void listeArticleAction(ActionEvent event) throws IOException {
        FXMLLoader fXMlLoader = new FXMLLoader(getClass().getResource("/gui/blog/listeArticles.fxml"));
        AnchorPane parentContent = fXMlLoader.load();
        ListeArticlesController c = (ListeArticlesController) fXMlLoader.getController();
        c.setApp(app);
        setNode(parentContent);
    }

    @FXML
    private void creeArticleAction(ActionEvent event) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/blog/ajouterArticle.fxml"));

        AnchorPane parentContent = fxmlLoader.load();
        AjouterArticleController c = (AjouterArticleController) fxmlLoader.getController();
      
        c.setApp(app);
         // System.out.println((c.app.getLoggedUser().getUsername() != null) + "****");
        setNode(parentContent);
    }
    
    public void setApp(Main app) {
        this.app = app;
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
}
