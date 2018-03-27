package security;

import com.sun.javafx.collections.MappingChange;

import java.util.HashMap;
import java.util.Map;

public class Authenticator {
    // TODO: Warning - el class hadi ta5ou les utlisateurs inscrits, el password bech tkoun crypter bel algorithm SHA512 kif el FOSUSER
    private static final Map<String, String> USERS = new HashMap<String, String>();
    static {
        //Ici USERS contient tous les utilisateurs de la base de donnés le login et le password (en clair et static pour le moment)
        USERS.put("Amri", "amrispassword");
        USERS.put("Housseme", "houssemspassword");
        USERS.put("Karim", "karimspassword");
        USERS.put("Ouni", "Ounispassword");
        USERS.put("Zain", "myPassword");
        USERS.put("admin", "admin");
    }
    public static boolean validate(String user, String password ){
        String validUserPassword = USERS.get(user);
        return validUserPassword != null && validUserPassword.equals(password);
    }
}
