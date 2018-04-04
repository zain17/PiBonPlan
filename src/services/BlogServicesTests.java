/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Article;
import entites.CommentaireB;
import java.util.Date;

/**
 *
 * @author aminos
 */
public class BlogServicesTests {
    public static void main(String[] args) {
        //Article art1 = new Article("<p><b>Un article de test</b></p>", 0, "Titre article", 5, "Zin", new Date(), new Date());
        ArticleService aServ = new ArticleService();
        //aServ.ajouter(art1);
        
        CommentaireB cB = new CommentaireB("Un commentaire", "Zin", 5);
        cB.setArticle(aServ.find(36));
        CommentaireBService cServ = new CommentaireBService();
        System.out.println(aServ.find(36));
        cServ.ajouter(cB);
        
    }
}
