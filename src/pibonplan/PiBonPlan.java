/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pibonplan;


import entites.Etablissement;
import services.EtablissementService;

/**
 *
 * @author ASUS
 */
public class PiBonPlan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EtablissementService etab_serv=new EtablissementService();
        Etablissement etablissement = new Etablissement();
        etablissement=etab_serv.selectOne(4);


    }
    
}
