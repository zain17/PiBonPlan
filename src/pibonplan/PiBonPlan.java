/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pibonplan;


import services.EtablissementService;
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
//        EtablissementService etablissementService= new EtablissementService();
//        System.out.println(etablissementService.selectAll());
        UtilisateurService usrserv= new UtilisateurService();
        System.out.println(usrserv.nbRevues(12));
    }
    
}
