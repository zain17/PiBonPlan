/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;

/**
 *
 * @author Nizar Elhraiech
 */
public class Experiences {
    private int id ;
    private String description ;
    private File image;
    private int iduser ;
    private byte [] photo ;
   // private Date date ;
    private int nbJaime ;
    private int nbComment ;
    private ArrayList<Jaime> jaimeList;
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
    

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Experiences(int id) {
        this.id = id;
    }

    public Experiences( String description, File image, int nbJaime, int nbComment,int iduser) {
        this.description = description;
        this.image = image;
        //this.date = date;
        this.nbJaime = nbJaime;
        this.nbComment = nbComment;
           this.iduser = iduser ;
    }

    public Experiences(String description, byte[] photo, int nbJaime, int nbComment,int iduser,int id) {
        this.description = description;
        this.photo = photo;
        this.nbJaime = nbJaime;
        this.nbComment = nbComment;
         this.iduser = iduser ;
             this.id = id ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    /*public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }*/
    public int getNbJaime() {
        return nbJaime;
    }

    public void setNbJaime(int nbJaime) {
        this.nbJaime = nbJaime;
    }

    public int getNbComment() {
        return nbComment;
    }

    public void setNbComment(int nbComment) {
        this.nbComment = nbComment;
    }

    public Experiences() {
    }
    
    
    
    
}
