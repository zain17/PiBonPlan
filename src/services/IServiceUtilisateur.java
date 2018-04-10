/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Utilisateur;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IServiceUtilisateur extends IService<Utilisateur>{
    public ArrayList<Utilisateur> selectByName(String nom);
    public int nbRevues(int iduser);
    public int nbExperiences(int iduser);
    public int  nbEvents(int iduser);

    ArrayList<Utilisateur> selectAllEnabled();
    public Utilisateur selectOne(String username);
    boolean hasEtablissement(int id);
}
