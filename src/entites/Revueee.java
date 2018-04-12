/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.File;

/**
 *
 * @author asus
 */
public class Revueee {
   public int id ;
   public int iduser ;
   private byte [] photo ;
    private File image;
      private String description ;

    public Revueee(int iduser,  File image, String description) {
        this.iduser = iduser;
        this.image = image;
        this.description = description;
    }

    public Revueee() {
    }

      
      
      
      
      
      
      
      
    public Revueee(int iduser, byte[] photo, File image, String description) {
        this.iduser = iduser;
        this.photo = photo;
        this.image = image;
        this.description = description;
    }

    public Revueee(int id, int iduser, byte[] photo, String description) {
        this.id = id;
        this.iduser = iduser;
        this.photo = photo;
        this.description = description;
    }

 
      

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
      

    public Revueee(int id, int iduser, byte[] photo, File image) {
        this.id = id;
        this.iduser = iduser;
        this.photo = photo;
        this.image = image;
    }

    public Revueee(int iduser, byte[] photo, File image) {
        this.iduser = iduser;
        this.photo = photo;
        this.image = image;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
    
    
}
