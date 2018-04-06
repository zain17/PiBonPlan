package security;

import entites.Utilisateur;
import services.UtilisateurService;

import javax.rmi.CORBA.Util;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Authenticator {


    private static final Map<String, Utilisateur> USERS = new HashMap<String, Utilisateur>();

//    public Map<String, Utilisateur> getUSERS() {
//        for (Utilisateur utilisateur:
//                this.users) {
//            USERS.put(utilisateur.getUsername(),utilisateur);
//        }
//        return USERS;
//    }

    public static boolean validate(String userName, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        loadAuthentificators();
        Utilisateur validUserPassword = USERS.get(userName);
        //  Cette méthode prend l'utilisateur qui veut s'authentifier et encrypt mot de pass en claire et puis elle vérifie si la hash générer et égale au hash original
        return FOSJCrypt.checkPassword(validUserPassword.getPassword(),password,validUserPassword.getSalt());
    }
    public static void loadAuthentificators(){
        UtilisateurService useserv=new UtilisateurService();
        ArrayList<Utilisateur> users=useserv.selectAll();
        for (Utilisateur utilisateur:
                users) {
            USERS.put(utilisateur.getUsername(),utilisateur);
        }
        //USERS.forEach((key, value) -> System.out.println(key + ", " + value));
        System.out.println(USERS.size());
    }

    public static void main(String[] args) {

    }
}
