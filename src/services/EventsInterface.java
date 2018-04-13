/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Evenements;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public interface EventsInterface<T> {
     public void ajouter(T t);
     public void supprimer(int id);
     public void modifier(T t);
     public Evenements find( int id) ; 
     public ObservableList<T> selectAll();
     public ObservableList<T> selectALL ();

 
}
