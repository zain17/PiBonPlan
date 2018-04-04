package gui;

import entites.Utilisateur;
import gui.Routers.RoutingBlog;
import gui.Routers.RoutingGestionProfil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.UtilisateurService;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ListUtilisateursController implements Initializable{
    @FXML private MenuItem menu_profil;
    @FXML private MenuItem menu_deconnecte;
    @FXML private MenuButton menubtn_NomUser;
    @FXML private MenuItem menu_Reserver_Place;
    @FXML private MenuItem menu_blogArticles;
    @FXML private MenuBar menuBar;
    @FXML private Label lbl_nbevents;
    @FXML private Label lbl_nbrevues;
    @FXML private Label lbl_nbexp;
    @FXML private Label lbl_role;
    @FXML private AnchorPane anchorpane_global;
    @FXML private TableView<Utilisateur> tabview_users;
    @FXML private TableColumn<Utilisateur,Integer> tabcol_id;
    @FXML private TableColumn<Utilisateur,String> tabcol_photo;
    @FXML private TableColumn<Utilisateur,Double> tabcol_lang;
    @FXML private TableColumn<Utilisateur,Double>  tabcol_lat;
    @FXML private TableColumn<Utilisateur,Integer> tabcol_idetab;
    @FXML private TableColumn<Utilisateur,String> tabcol_nom;
    @FXML private TableColumn<Utilisateur,String> tabcol_prenom;
    @FXML private TableColumn<Utilisateur,String> tabcol_email;
    @FXML private TableColumn<Utilisateur,Date> tabcol_lastlogin;
    @FXML private TableColumn<Utilisateur,String>  tabcol_role;
    private Utilisateur selectedUser  = new Utilisateur();
    private UtilisateurService userServ=new UtilisateurService();

    private Main app;
    @FXML
    private Menu menu_file1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Utilisateur> utilisateurs= FXCollections.observableList(userServ.selectAll());
        tabcol_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabcol_photo.setCellValueFactory(new PropertyValueFactory<>("photoProfil"));
        tabcol_lang.setCellValueFactory(new PropertyValueFactory<>("langitude"));
        tabcol_lat.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        tabcol_idetab.setCellValueFactory(new PropertyValueFactory<>("username"));
        tabcol_nom.setCellValueFactory(new PropertyValueFactory<>("username"));
        tabcol_prenom.setCellValueFactory(new PropertyValueFactory<>("usernameCanonical"));
        tabcol_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tabcol_lastlogin.setCellValueFactory(new PropertyValueFactory<>("lastLogin"));
        tabcol_role.setCellValueFactory(new PropertyValueFactory<>("roles"));
        tabview_users.setItems(utilisateurs);
        tabview_users.getSelectionModel().selectFirst();//le premier row est selectionner au début


    }
    @FXML
    public void onMenuBlogChoose(ActionEvent event) throws Exception {
        System.out.println(this.app != null);
        RoutingBlog rgB = new RoutingBlog(this.app);
        rgB.gotoAjouterArticle();
    }
    @FXML
    public void onMenuEventsChoose(Event event) throws Exception {
        System.out.println(this.app != null);
        RoutingGestionProfil rgP = new RoutingGestionProfil(this.app);
        rgP.gotoProfile();
    }
    public void onMenuEtablissementsChoose(ActionEvent event) throws Exception {
        System.out.println(this.app != null);
        RoutingGestionProfil rgP = new RoutingGestionProfil(this.app);
        rgP.gotoProfile();
    }
    public void onMenuReserverEventChoose(ActionEvent event) throws Exception {
        System.out.println(this.app != null);
        RoutingGestionProfil rgP = new RoutingGestionProfil(this.app);
        rgP.gotoProfile();
    }
    public void onMenuReserverTicketChoose(ActionEvent event) throws Exception {

    }
    @FXML
    public void onSelectedChange(MouseEvent event){
        selectedUser=tabview_users.getSelectionModel().getSelectedItem();
        lbl_nbexp.setText("Expériences:");
        lbl_nbrevues.setText("Révues:");
        lbl_role.setText("");
        loadUserNodes();
    }
    public void setApp(Main app) {
        this.app = app;
    }

    public void loadUserNodes(){
        System.out.println("Load new information of  the selected user");
        System.out.println(selectedUser);
        lbl_nbexp.setText(lbl_nbexp.getText()+userServ.nbExperiences(selectedUser.getId()));
        lbl_nbrevues.setText(lbl_nbrevues.getText()+userServ.nbRevues(selectedUser.getId()));
        if(selectedUser.getRoles().equals("ROLE_ETABLISSEMENT"))
        lbl_role.setText("Proprietaire de l'établissement X(redirection)");
        if(selectedUser.getRoles().equals("ROLE_CLIENT"))
            lbl_role.setText("Visiteur");
       // TODO: Warning We must add Relation One User can do Many Events
       // lbl_nbevents.setText(lbl_nbevents.getText()+userServ.nbEvents(selectedUser.getId()));//
    }
    public void loadTab(){
        // TODO: Warning - EAGER fetch related items of the selected USER
    }
    public void rechercheParNom(){
        // TODO: Warning - refrech table view with the input in the textfiled
    }

    public void onClick_nomUser(ActionEvent actionEvent) {
        System.out.println(this.app != null);
        RoutingGestionProfil rgP = new RoutingGestionProfil(this.app);
        rgP.gotoProfile();
    }
}
