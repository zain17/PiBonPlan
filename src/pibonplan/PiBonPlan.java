/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pibonplan;


import services.ArticleService;
import services.EtablissementService;
import services.EvenementService;
import services.IServiceEtablissement;
import services.UtilisateurService;


/**
 *
 * @author ASUS
 */
public class PiBonPlan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//

        EvenementService es = new EvenementService ();
        ArticleService as = new ArticleService();
        System.out.println(as.findAll());
        System.out.println(es.selectAll());

    }
    
}
