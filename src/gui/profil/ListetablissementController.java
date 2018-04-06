package gui.profil;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import entites.Etablissement;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import services.EtablissementService;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import javafx.application.Application;

import static com.lynden.gmapsfx.javascript.object.MapTypeIdEnum.ROADMAP;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ListetablissementController implements MapComponentInitializedListener,Initializable {
    @FXML
    GoogleMapView mapView;
    GoogleMap map;
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
    @FXML private WebView webview_map;
    @FXML private TableView<Etablissement> tableView_listetab;
    private Main app;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EtablissementService etabServ=new EtablissementService();
        ObservableList<Etablissement> etablissements= FXCollections.observableList(etabServ.selectAll());
        tablecol_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tablecol_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tablecol_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tablecol_gouvernorat.setCellValueFactory(new PropertyValueFactory<>("Gouvernorat"));
        tablecol_ville.setCellValueFactory(new PropertyValueFactory<>("Ville"));
        tablecol_note.setCellValueFactory(new PropertyValueFactory<>("Note"));
        tablecol_horraire.setCellValueFactory(new PropertyValueFactory<>("Horraire"));
        tablecol_long.setCellValueFactory(new PropertyValueFactory<>("Longitude"));
        tablecol_lati.setCellValueFactory(new PropertyValueFactory<>("Latitude"));
        tablecol_active.setCellValueFactory(new PropertyValueFactory<>("estActive"));
        tablecol_photo.setCellValueFactory(new PropertyValueFactory<>("Photo"));
        tableView_listetab.setItems(etablissements);

    }

    public void setApp(Main app) {
        this.app = app;
    }

    @Override
    public void mapInitialized() {
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(47.6097, -122.3331))
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

        markerOptions.position( new LatLong(47.6, -122.3) )
                .visible(Boolean.TRUE)
                .title("My Marker");

        Marker marker = new Marker( markerOptions );

        map.addMarker(marker);
    }
}
