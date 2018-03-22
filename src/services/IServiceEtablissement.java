/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Etablissement;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IServiceEtablissement extends IService<Etablissement>{
    public ArrayList<Etablissement> selectByNom();
    public ArrayList<Etablissement> selectByGouvernorat();
    public ArrayList<Etablissement> selectByVille();
    public ArrayList<Etablissement> selectBest();
    public ArrayList<Etablissement> selectBestByType(String type);//Select les 5 meilleurs etablissement par type(café,loisir.etc..)
    public ArrayList<Etablissement> selectNear();//Select les établissements qui sont proches de cordonnés de l'utilisateur
}
