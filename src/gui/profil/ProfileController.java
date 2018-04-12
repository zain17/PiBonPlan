package gui.profil;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawersStack;
import entites.Utilisateur;
import gui.Main;
import java.io.IOException;
import gui.Routers.RoutingGestionProfilContainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import services.UtilisateurService;


import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.Authenticator;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import post.CommentaireExFXMLController;

public class ProfileController implements Initializable {
    RoutingGestionProfilContainer routingGestionProfilContainer= new RoutingGestionProfilContainer(this.app,this);
    @FXML
    private AnchorPane contneurProfil;
    @FXML
    private  Label lbl_nbExperience;
    @FXML
    private  Label lbl_nbRevue;
    @FXML
    private  Label lbl_nbEvents;
    @FXML
    private  Hyperlink H_gotoMyEtab;
    @FXML
    private Label lbl_nom;
    @FXML
    private Label lbl_email;
    @FXML
    private Label lbl_prenom;
    @FXML
    private ImageView image_photoprofil;
 @FXML
 private Button experie ;
 @FXML
 private Button revuess ;
    private AnchorPane contained;
    private Main app;
    private Utilisateur userHand;

    public ProfileController() throws IOException {

    }

    public AnchorPane getContneurProfil() {
        return contneurProfil;
    }

    public void setContneurProfil(AnchorPane contneurProfil) {
        this.contneurProfil = contneurProfil;
    }

    public void setApp(Main app) {
        System.out.println("setApp prompt");
        this.app = app;
    }
    public void processLogout(ActionEvent actionEvent) {

    }
     private Main main;
    
      public Stage stage;
 //private  RoutingPost eeee = new RoutingPost(this);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUserInfo(security.Authenticator.getCurrentAuth());
         
       
        
    }
    
       public void setNode(Node node) {
        contained.getChildren().clear();
        contained.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    public void loadUserInfo(Utilisateur u){
        UtilisateurService usev=new UtilisateurService();
        boolean hasEtablissement=usev.hasEtablissement(u.getId());
        if(u.getRoles().equals("ROLE_ETABLISSEMENT")){
            if(!hasEtablissement){
                H_gotoMyEtab.setText("Voir l'établissement");
            }
            else
                H_gotoMyEtab.setText("Créer mon établissement");
        }
        if(u.getRoles().equals("ROLE_CLIENT")){
            H_gotoMyEtab.setVisible(false);
        }
        lbl_nom.setText(u.getUsername());
        lbl_prenom.setText(u.getPrenom());
        lbl_email.setText(u.getEmail());
        lbl_nbEvents.setText(String.valueOf(usev.nbEvents(u.getId())));
        lbl_nbExperience.setText(String.valueOf(usev.nbExperiences(u.getId())));
        lbl_nbRevue.setText(String.valueOf(usev.nbRevues(u.getId())));
    }
    @FXML
    public void voirEtablissement(ActionEvent actionEvent) throws IOException {
        if(H_gotoMyEtab.getText().equals("Voir l'établissement"))
        routingGestionProfilContainer.profileToEtablissementEdit();
        if(H_gotoMyEtab.getText().equals("Créer mon établissement"))
            routingGestionProfilContainer.profileToEtablissement();

    }

    @FXML
    private void loadexper(ActionEvent event) throws IOException {
        FXMLLoader fxmlex = new FXMLLoader(getClass().getResource("/post/PostFXML.fxml"));
        AnchorPane a  = (AnchorPane) fxmlex.load();
        contneurProfil.getChildren().setAll(a);
        
    }

    @FXML
    private void loadrevue(ActionEvent event) throws IOException {
          FXMLLoader fxmlex = new FXMLLoader(getClass().getResource("/post/Revue.fxml"));
        AnchorPane a  = (AnchorPane) fxmlex.load();
        contneurProfil.getChildren().setAll(a);
    }
    
   
    

}
