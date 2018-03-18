/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Etablissement;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IServiceEtablissement extends IService<Etablissement>{
    public List<Etablissement> selectByNom();
    public List<Etablissement> selectByGouvernorat();
    public List<Etablissement> selectByVille();
    public List<Etablissement> selectBest();
    public List<Etablissement> selectBestByType(String type);//Select les 5 meilleurs etablissement par type(café,loisir.etc..)
    public List<Etablissement> selectNear();//Select les établissements qui sont proches de cordonnés de l'utilisateur
}
