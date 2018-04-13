/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author azerty
 */
public class CommentaireEx {
    public int id ;
    public int iduser ;
    public String contenu ;
    public int idexp ;
    
    
    
    
    
    
    
    
    public CommentaireEx() {
    }
   


    public int getIdexp() {
        return idexp;
    }

    public void setIdexp(int idexp) {
        this.idexp = idexp;
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

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public CommentaireEx(int id, int iduser, String contenu, int idexp) {
        this.id = id;
        this.iduser = iduser;
        this.contenu = contenu;
        this.idexp = idexp;
    }

    public CommentaireEx(int iduser, String contenu, int idexp) {
        this.iduser = iduser;
        this.contenu = contenu;
        this.idexp = idexp;
    }
    
    
    
}
