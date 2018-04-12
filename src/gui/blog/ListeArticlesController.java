/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.blog;

import entites.Article;
import gui.Main;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import security.Authenticator;
import services.ArticleService;

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
    public AnchorPane listeContainer;
    @FXML
    private VBox rechContainer;
    private BlogContainerController blogcontroller;
    @FXML
    private TextField recherche;
    @FXML
    public VBox tags;
    @FXML
    public VBox tagLabs;

    public void setPaginatorContainer(Pagination paginator, BlogContainerController c) {
        listeContainer.setMinWidth(600);
        listeContainer.setMinHeight(600);
        System.out.println(Authenticator.getCurrentAuth().getEmail());
        paginator.setMinHeight(600);
        paginator.setMinWidth(600);
       
        ScrollPane scroller = new ScrollPane(paginator);
        scroller.setMinWidth(600);
        scroller.setMinHeight(600);
        scroller.setStyle("-fx-background:color: white;");
        listeContainer.setTopAnchor(scroller, 10.0);
        listeContainer.setLeftAnchor(scroller, 10.0);
        listeContainer.setRightAnchor(scroller, 10.0);
        listeContainer.setBottomAnchor(scroller, 10.0);
       
        this.listeContainer.getChildren().addAll(scroller);
        this.blogcontroller = c;
        this.blogcontroller.app = this.app;

        this.tags.setVisible(false);
        this.tags.setManaged(false);
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

    @FXML
    private void rechercherArticle(KeyEvent event) throws Exception {
        ArticleService aS = new ArticleService();
        ArrayList<Article> list = aS.find(recherche.getText());
//        System.out.println("listeController, 52 " + list.size() + "titre " + list.get(0).getTexte());
        Paginator p = new Paginator(list, 3, this, this.blogcontroller);
        this.setPaginatorContainer(p.getPaginator(), this.blogcontroller);
        this.setApp(app);
        
    }

        public void setNode(Node node) {
        listeContainer.getChildren().clear();
        listeContainer.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
   
    
}