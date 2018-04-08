/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.Events.AjoutEvents;
import gui.Events.ListEvents;
import gui.blog.AjouterArticleController;
import gui.blog.RechercherArticleController;
import gui.profil.ListetablissementController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author aminos
 */
public class ContainerController implements Initializable {

    private Main app;
    @FXML
    private AnchorPane contained;
    @FXML
    private Button etab;
    @FXML
    private Button blog;
    @FXML
    private Button blogRech;
    @FXML
    private Button articles;
    @FXML
    private Button articles1;
    @FXML
    private Button profileB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    

    @FXML
    private void blogAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/blog/ajouterArticle.fxml"));

        AnchorPane parentContent = fxmlLoader.load();
        AjouterArticleController c = (AjouterArticleController) fxmlLoader.getController();
      
        c.setApp(app);
         // System.out.println((c.app.getLoggedUser().getUsername() != null) + "****");
        setNode(parentContent);
        
    }
    
        @FXML
    private void blogArction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/blog/rechercherArticle.fxml"));

        AnchorPane parentContent = fxmlLoader.load();
            RechercherArticleController c = (RechercherArticleController) fxmlLoader.getController();
      
        c.setApp(app);
         // System.out.println((c.app.getLoggedUser().getUsername() != null) + "****");
        setNode(parentContent);
        
    }
    
   private void setNode(Node node) {
        contained.getChildren().clear();
        contained.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    public void setApp(Main main) {
        this.app = main;
    }

    @FXML
    private void blogLsArtcAction(ActionEvent event) {
        
    }

    @FXML
    private void listeEtab(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/profil/listetablissement.fxml"));

        AnchorPane parentContent = fxmlLoader.load();
           ListetablissementController c = (ListetablissementController) fxmlLoader.getController();
      
        c.setApp(app);
         // System.out.println((c.app.getLoggedUser().getUsername() != null) + "****");
        setNode(parentContent);
    }
    
    
     @FXML
    private void ajout(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Events/AjoutEvents.fxml"));

        AnchorPane parentContent = fxmlLoader.load();
           AjoutEvents c = (AjoutEvents) fxmlLoader.getController();
      
        c.setApp(app);
         // System.out.println((c.app.getLoggedUser().getUsername() != null) + "****");
        setNode(parentContent);
        
    }
    
      @FXML
       private void Events(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Events/ListEvents.fxml"));

        AnchorPane parentContent = fxmlLoader.load();
           ListEvents c = (ListEvents) fxmlLoader.getController();
      
        c.setApp(app);
         // System.out.println((c.app.getLoggedUser().getUsername() != null) + "****");
        setNode(parentContent);
        
    }
    
}
