/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.blog;

import gui.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import security.Authenticator;

/**
 * FXML Controller class
 *
 * @author aminos
 */
public class ListeArticlesController implements Initializable {

    public Main app;
    @FXML
    private AnchorPane blogWidget;
    @FXML
    private AnchorPane listeContainer;

    public void setListeContainer(AnchorPane listeContainer) {
        listeContainer.setMinWidth(600);
        listeContainer.setMinHeight(600);
        System.out.println(Authenticator.getCurrentAuth().getEmail());
        ScrollPane scroller = new ScrollPane(listeContainer);
        scroller.setMinWidth(600);
        scroller.setMinHeight(600);
        scroller.setStyle("-fx-background:color: white;");
        listeContainer.setTopAnchor(scroller, 0.0);
        listeContainer.setLeftAnchor(scroller, 0.0);
        listeContainer.setRightAnchor(scroller, 0.0);
        listeContainer.setBottomAnchor(scroller, 0.0);
        
        this.listeContainer.getChildren().addAll(scroller);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setApp(Main app) {
     this.app = app;
    }
    
}
