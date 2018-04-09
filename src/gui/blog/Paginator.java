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
import services.ArticleService;

public class Paginator  {
  private Pagination pagination;
  private ArrayList<Article> list;
  private int itemsPerPage;
  int pages = 1;
  
  public Paginator(ArrayList<Article> list, int itemsNumber) {
      this.list = list;
      this.itemsPerPage = itemsNumber;
  }

  public static void main(String[] args) throws Exception {
  
  }

  

  public VBox createPage(int pageIndex) {
    VBox box = new VBox(5);
    box.setStyle("-fx-background-color: white;");
    int page = pageIndex * itemsPerPage;
    System.out.println("PAGGGGGE " + page);
    if (pages - 1 == page) {
        itemsPerPage = list.size() - (itemsPerPage * (pages - 1));
    }
    for (int i = page; i < page + itemsPerPage; i++) {
      
       
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/blog/articleElement.fxml"));
        VBox element;
        
            element = loader.load();
            ArticleElementController c = (ArticleElementController) loader.getController();
            c.setTitre(list.get(i).getTitre());
            c.setAuthor(list.get(i).getAuteurn(), list.get(i).getCreated().toString());
                        String aText = list.get(i).getTexte();
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

 
  public AnchorPane getPaginator() throws Exception {
    
      
      if (list.size() > itemsPerPage ) {
          if (list.size() % itemsPerPage != 0) {
              pages  = (list.size() / itemsPerPage) + 1;
              
          }
          else
              pages = (list.size() / itemsPerPage);
      }
      else
          itemsPerPage = list.size();
   
    pagination = new Pagination(pages, 0);
   // pagination.setStyle("-fx-border-color:red;");
     pagination.setStyle("-fx-background-color: white;");
    pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex));
  
    AnchorPane anchor = new AnchorPane();
    anchor.setStyle("-fx-background-color: white;");
    AnchorPane.setTopAnchor(pagination, 10.0);
    AnchorPane.setRightAnchor(pagination, 10.0);
    AnchorPane.setBottomAnchor(pagination, 10.0);
    AnchorPane.setLeftAnchor(pagination, 10.0);
    anchor.getChildren().addAll(pagination);
    return anchor;
  }
}
