/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Routers;

import gui.AjouterArticleController;
import gui.Main;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aminos
 */
public class RoutingBlog {
    private Main main;
    
    public RoutingBlog(Main main) {
        this.main = main;
    }
    
     public void gotoAjouterArticle() {
        try {
            System.out.println(main != null);
            AjouterArticleController ajouterArticleC = (AjouterArticleController) main.replaceSceneContent("/gui/ajouterArticle.fxml");
            ajouterArticleC.setApp(main);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
