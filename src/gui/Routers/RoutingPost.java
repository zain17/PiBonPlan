/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Routers;

import gui.ContainerController;
import gui.Main;
import gui.blog.AjouterArticleController;
import java.util.logging.Level;
import java.util.logging.Logger;
import post.PostFXMLController;

/**
 *
 * @author asus
 */
public class RoutingPost {
  
     private Main main;
    
    public RoutingPost(Main main) {
        this.main = main;
    }
    
     public void gotoAjouterArticle() {
        try {
            System.out.println(main != null);
            PostFXMLController ajouterArticleC = (PostFXMLController) main.replaceSceneContent("/post/PostFXML.fxml");
            ajouterArticleC.setApp(main);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoContainer() {
        try {
            System.out.println(main != null);
            ContainerController ajouterArticleC = (ContainerController) main.replaceSceneContent("/gui/container.fxml");
            ajouterArticleC.setApp(main);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
