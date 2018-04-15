package gui.profil;

import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import entites.Etablissement;
import gui.Main;
import gui.Routers.RoutingGestionProfilContainer;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import services.EtablissementService;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import static com.lynden.gmapsfx.javascript.object.MapTypeIdEnum.ROADMAP;
import static javafx.application.Application.launch;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListetablissementController implements MapComponentInitializedListener,Initializable {

    public Label lbl_selectedGouv;
    public Label lbl_selectedVille;
    public JFXButton btn_refrech;
    public JFXButton btn_restaurant;
    public JFXButton btn_cafe;
    public JFXButton btn_shopping;
    public JFXButton btn_loisirs;
    @FXML private AnchorPane ancchild;
    @FXML private Label lbl_oubFermer;
    @FXML private GoogleMapView mapView;
    GoogleMap map;
    @FXML private TableColumn<Etablissement, String> tablecol_desc;
    @FXML private TableColumn<Etablissement, String> tablecol_type;
    @FXML private Label lbl_nbexp;
    @FXML private Label lbl_nbRevue;
    @FXML private Label lbl_note;
    @FXML private Hyperlink hlink_visiter;
    @FXML private TableColumn<Etablissement, String> tablecol_horrairef;
    @FXML private TableColumn<Etablissement,Integer> tablecol_id;
    @FXML private TableColumn<Etablissement,String> tablecol_nom;
    @FXML private TableColumn<Etablissement,String> tablecol_adresse;
    @FXML private TableColumn<Etablissement,String> tablecol_gouvernorat;
    @FXML private TableColumn<Etablissement,String> tablecol_ville;
    @FXML private TableColumn<Etablissement,Double> tablecol_note;
    @FXML private TableColumn<Etablissement,String> tablecol_horraire;
    @FXML private TableColumn<Etablissement,Double> tablecol_long;
    @FXML private TableColumn<Etablissement,Double> tablecol_lati;
    @FXML private TableColumn<Etablissement,Boolean> tablecol_active;
    @FXML private TableColumn<Etablissement,String> tablecol_photo;
    @FXML private ImageView image_photoetab;
    @FXML private TableView<Etablissement> tableView_listetab;
    private Etablissement selectedEtab=new Etablissement();
    private Main app;
    private String var_selectedGouv;
    private String var_selectedVille;

    public AnchorPane getAncchild() {
        return ancchild;
    }

    public void setAncchild(AnchorPane ancchild) {
        this.ancchild = ancchild;
    }

    public String getVar_selectedGouv() {
        return var_selectedGouv;
    }

    public void setVar_selectedGouv(String var_selectedGouv) {
        this.var_selectedGouv = var_selectedGouv;
    }

    public String getVar_selectedVille() {
        return var_selectedVille;
    }

    public void setVar_selectedVille(String var_selectedVille) {
        this.var_selectedVille = var_selectedVille;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapView.addMapInializedListener(this);
        EtablissementService etabServ=new EtablissementService();
        ArrayList<Etablissement> dataToshow=new ArrayList<>();
        dataToshow=etabServ.selectAll();
        ObservableList<Etablissement> etablissements= FXCollections.observableList(dataToshow);//Selon le filtre
        tablecol_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tablecol_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tablecol_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tablecol_gouvernorat.setCellValueFactory(new PropertyValueFactory<>("Gouvernorat"));
        tablecol_ville.setCellValueFactory(new PropertyValueFactory<>("Ville"));
        tablecol_note.setCellValueFactory(new PropertyValueFactory<>("Note"));
        tablecol_horraire.setCellValueFactory(new PropertyValueFactory<>("Horraire"));
        tablecol_long.setCellValueFactory(new PropertyValueFactory<>("Longitude"));
        tablecol_lati.setCellValueFactory(new PropertyValueFactory<>("Latitude"));
        tablecol_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        tablecol_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        tablecol_active.setCellValueFactory(new PropertyValueFactory<>("estActive"));
        tablecol_photo.setCellValueFactory(new PropertyValueFactory<>("Photo"));
        tablecol_horrairef.setCellValueFactory(new PropertyValueFactory<>("horraire_f"));
        tableView_listetab.setItems(etablissements);
        Etablissement firstSelected=new Etablissement();
        tableView_listetab.getSelectionModel().selectFirst();
        firstSelected=tableView_listetab.getSelectionModel().getSelectedItem();
        lbl_nbexp.setText(String.valueOf(etabServ.nbExperiences(firstSelected.getId())));
        lbl_nbRevue.setText(String.valueOf(etabServ.nbRevues(firstSelected.getId())));
        lbl_note.setText(firstSelected.getNote().toString());
    }
    public ArrayList<Etablissement> returnFiltredStreamByGouv(){
        EtablissementService etablissementService=new EtablissementService();
        return etablissementService.selectByGouvernorat(lbl_selectedGouv.getText());
    }
    public ArrayList<Etablissement> returnFiltredStreamByVille(){
        EtablissementService etablissementService=new EtablissementService();
        return etablissementService.selectByVille(lbl_selectedVille.getText());
    }
    public void setApp(Main app) {
        this.app = app;
    }

    @Override
    public void mapInitialized() {
        System.out.println(selectedEtab);
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(36.812504, 10.177631))
                .mapType(ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);

        //Add a marker to the map
        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position( new LatLong(36.8, 10.1) )
                .visible(Boolean.TRUE)
                .title("My Marker");

        Marker marker = new Marker( markerOptions );

        map.addMarker(marker);
    }
    @FXML
    public void takeSelectedEtab(MouseEvent mouseEvent) {
        selectedEtab = tableView_listetab.getSelectionModel().getSelectedItem();
        EtablissementService etabServ=new EtablissementService();
        lbl_nbexp.setText(String.valueOf(etabServ.nbExperiences(selectedEtab.getId())));
        lbl_nbRevue.setText(String.valueOf(etabServ.nbRevues(selectedEtab.getId())));
        lbl_note.setText(selectedEtab.getNote().toString());
        reloadMapOne(selectedEtab);
    }
    @FXML
    public void gotoEtablissentProfile(ActionEvent actionEvent) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/profil/profile.fxml"));
//        AnchorPane parentContent = fxmlLoader.load();
//        ProfileController cs = (ProfileController) fxmlLoader.getController();
//        cs.lbl_visiteur.setText(selectedEtab.getId().toString());
//        cs.setApp(app);
//        setNode(parentContent);
    }
    public void setNode(Node node) {
        ancchild.getChildren().clear();
        ancchild.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    @FXML
    public void loadDataRecherche(ActionEvent mouseEvent) {
        tableView_listetab.refresh();
        EtablissementService etabServ=new EtablissementService();
        ArrayList<Etablissement> dataToshow=new ArrayList<>();
        //dataToshow=etabServ.selectAll();
        if((lbl_selectedGouv.getText()!=""&&lbl_selectedVille.getText()==""))
            dataToshow= returnFiltredStreamByGouv();
        if((lbl_selectedGouv.getText()!="")&&(lbl_selectedVille.getText()!=""))
            dataToshow=returnFiltredStreamByVille();
        ObservableList<Etablissement> etablissements= FXCollections.observableList(dataToshow);//Selon le filtre
        tableView_listetab.setItems(etablissements);
        reloadMap(dataToshow);
    }

    public void clickRestaurant(ActionEvent actionEvent) {
        tableView_listetab.refresh();
        EtablissementService etabServ=new EtablissementService();
        ArrayList<Etablissement> dataToshow=new ArrayList<>();
            dataToshow=etabServ.selectBestByType("restaurant");
        ObservableList<Etablissement> etablissements= FXCollections.observableList(dataToshow);//Selon le filtre
        tableView_listetab.setItems(etablissements);
        reloadMap(dataToshow);
    }

    public void clickCafe(ActionEvent actionEvent) {
        tableView_listetab.refresh();
        EtablissementService etabServ=new EtablissementService();
        ArrayList<Etablissement> dataToshow=new ArrayList<>();
        dataToshow=etabServ.selectBestByType("cafe");
        ObservableList<Etablissement> etablissements= FXCollections.observableList(dataToshow);//Selon le filtre
        tableView_listetab.setItems(etablissements);
        reloadMap(dataToshow);
    }

    public void clickShopping(ActionEvent actionEvent) {
        tableView_listetab.refresh();
        EtablissementService etabServ=new EtablissementService();
        ArrayList<Etablissement> dataToshow=new ArrayList<>();
        dataToshow=etabServ.selectBestByType("shopping");
        ObservableList<Etablissement> etablissements= FXCollections.observableList(dataToshow);//Selon le filtre
        tableView_listetab.setItems(etablissements);
        reloadMap(dataToshow);
    }
    public void reloadMap(ArrayList<Etablissement>etablissements){
        for(int i=0;i<etablissements.size();i++){
            MarkerOptions markerOptions = new MarkerOptions();
            if(etablissements.get(i).getLatitude()!=0&&etablissements.get(i).getLongitude()!=0&&etablissements.get(i).getNom()!=null)
            markerOptions.position( new LatLong(etablissements.get(i).getLatitude(), etablissements.get(i).getLongitude()) )
                    .visible(Boolean.TRUE)
                    .title(etablissements.get(i).getNom());
            Marker marker = new Marker( markerOptions );
            map.addMarker(marker);
          
              
        }
    }
    public void reloadMapOne(Etablissement etc){
        map.clearMarkers();
            MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position( new LatLong(etc.getLatitude(), etc.getLongitude()) )
                        .visible(Boolean.TRUE)
                        .title(etc.getNom());
            Marker marker = new Marker( markerOptions );
            map.addMarker(marker);
    }

    public void clickLoisirs(ActionEvent actionEvent) {
        tableView_listetab.refresh();
        EtablissementService etabServ=new EtablissementService();
        ArrayList<Etablissement> dataToshow=new ArrayList<>();
        dataToshow=etabServ.selectBestByType("loisirs");
        ObservableList<Etablissement> etablissements= FXCollections.observableList(dataToshow);//Selon le filtre
        tableView_listetab.setItems(etablissements);
        reloadMap(dataToshow);
    }
}
