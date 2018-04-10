/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pibonplan;


import services.*;


/**
 *
 * @author ASUS
 */
public class PiBonPlan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServiceGouvernorat serviceGouvernorat=new ServiceGouvernorat();
        System.out.println(serviceGouvernorat.selectAllEager());
       // ServiceVille serviceVille=new ServiceVille();
//        System.out.println(serviceVille.selectAllByGouvernorat(1));
//

//        EvenementService es = new EvenementService ();
//        ArticleService as = new ArticleService();
//        System.out.println(as.findAll());
//        System.out.println(es.selectAll());

    }
    
}
