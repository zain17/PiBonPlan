/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Article;
import entites.CommentaireB;
import entites.Tag;
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
        
        //CommentaireB cB = new CommentaireB("Un commentaire", "Zin", 5);
        //cB.setArticle(aServ.find(36));
        //CommentaireBService cServ = new CommentaireBService();
        //System.out.println(aServ.find(36));
        //cServ.ajouter(cB);
        //TagService tS = new TagService();
        //Tag t = new Tag("");
        //tS.ajouter(t, 36);
        
        //System.out.println(aServ.findByTag(t).size());
        //System.out.println(tS.find(new Tag("musique")));
       // tS.supprimer("musique");
       //CommentaireBService cS = new CommentaireBService();
       //CommentaireB c = new CommentaireB("modified",  "admin", 69);
       //c.setId(10);
       //cS.modifier(c);
       ArticleService aS = new ArticleService();
       Article a = new Article();
       a.setId(86);
       System.out.println(aS.getTags(a));
        
    }
}
