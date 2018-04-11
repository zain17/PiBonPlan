/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.blog;

/**
 *
 * @author aminos
 */
import entites.Article;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import security.Authenticator;
import services.ArticleService;

public class Paginator  {
  private Pagination pagination;
  private ArrayList<Article> list;
  private int itemsPerPage;
  public BlogContainerController blogContainerController;
  int pages = 1;
  private AnchorPane paginatorParent;

    public Paginator(ArrayList<Article> list, int itemsPerPage, AnchorPane parent, BlogContainerController c) {
        this.pagination = pagination;
        this.list = list;
        this.itemsPerPage = itemsPerPage;
        paginatorParent = parent;
        this.blogContainerController = c;
    }

    public Paginator() {
    }
  
  public Paginator(ArrayList<Article> list, int itemsNumber, AnchorPane parent) {
      this.list = list;
      this.itemsPerPage = itemsNumber;
      paginatorParent = parent;
  }

  public static void main(String[] args) throws Exception {
  
  }

  

  public VBox createPage(int pageIndex) {
    VBox box = new VBox(5);
    box.setStyle("-fx-background-color: white;");
    int page = pageIndex * itemsPerPage;
    System.out.println("PAGGGGGE " + page);

    int toIndex = Math.min(page + itemsPerPage, list.size());
    for (int i = page; i <  toIndex; i++) {
      
       
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/blog/articleElement.fxml"));
        VBox element;
        
            element = loader.load();
            ArticleElementController c = (ArticleElementController) loader.getController();
            c.setBlogController(this.blogContainerController);
            c.setBoxParent(box);
            c.setPaginatorParent(this.paginatorParent);
            c.setArticle(list.get(i));
            c.setTitre(list.get(i).getTitre());
            c.setAuthor(list.get(i).getAuteurn(), list.get(i).getCreated().toString());
                        String aText = list.get(i).getTexte();
            if (!Authenticator.getCurrentAuth().getUsername().equals("admin")) {
                c.supprimer.setVisible(false);
                c.supprimer.setManaged(false);
                c.modifier.setVisible(false);
                c.modifier.setManaged(false);
            };
            aText = Jsoup.parse(aText).text();
            c.setText(aText.substring(0, aText.length()/10) + "...");
            System.out.println(list.get(i).getTexte() + "*****");
            box.getChildren().addAll(element);
            /*
            Hyperlink link = new Hyperlink(list.get(i).getTitre());
            link.setVisited(true);
            Label date = new Label("Crée par " + list.get(i).getAuteurn() + " le " + list.get(i).getCreated());
            Label separator = new Label("--------------------");
            String aText = list.get(i).getTexte();
            aText = Jsoup.parse(aText).text();
            Label text = new Label(aText.substring(0, aText.length()/10) + "...");
             */
        } catch (IOException ex) {
            Logger.getLogger(Paginator.class.getName()).log(Level.SEVERE, null, ex);
        }
      
     // element.getChildren().addAll(link, date, text);
      
    }
    return box;
  }

 
  public Pagination getPaginator() throws Exception {
    
      
   
   
    pagination = new Pagination(list.size() / itemsPerPage + 1, 0);
   // pagination.setStyle("-fx-border-color:red;");
     pagination.setStyle("-fx-background-color: white;");
    pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex));
  /*
    AnchorPane anchor = new AnchorPane();
    anchor.setStyle("-fx-background-color: white;");
    
    AnchorPane.setTopAnchor(pagination, 10.0);
    AnchorPane.setRightAnchor(pagination, 10.0);
    AnchorPane.setBottomAnchor(pagination, 10.0);
    AnchorPane.setLeftAnchor(pagination, 10.0);
    anchor.getChildren().addAll(pagination);
*/
    return pagination;
  }
}
