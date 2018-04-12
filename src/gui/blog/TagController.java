/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.blog;

import entites.Article;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import services.ArticleService;

/**
 * FXML Controller class
 *
 * @author aminos
 */
public class TagController implements Initializable {

    BlogContainerController blogContainer;
    AnchorPane listeContainer;
    @FXML
    public Hyperlink tag;
    ListeArticlesController listArtC;
    entites.Tag tg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void rechArticles(ActionEvent event) throws Exception {
        ArticleService aS = new ArticleService();
        ArrayList<Article> list = aS.findByTag(tg);
//        System.out.println("listeController, 52 " + list.size() + "titre " + list.get(0).getTexte());
        Paginator p = new Paginator(list, 3, this.listArtC, this.blogContainer);
        this.listArtC.setPaginatorContainer(p.getPaginator(), this.blogContainer);

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
