/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pibonplan;


import entites.Etablissement;
import entites.Utilisateur;
import services.EtablissementService;
import services.UtilisateurService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class PiBonPlan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UtilisateurService user_serv=new UtilisateurService();
        Utilisateur us = new Utilisateur();
        //ArrayList<Utilisateur> uss= new ArrayList<>();
         //uss= (ArrayList<Utilisateur>) user_serv.selectAll();
        //System.out.println(uss);

        //etab_serv.ajouter(new Etablissement("pino","Rue","ariana","ville",""));


    }
    
}
