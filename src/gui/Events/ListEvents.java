package gui.Events;

import entites.Evenements;
import gui.ContainerController;
import gui.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.Routers.RoutingEvents;
import java.sql.Date;
import java.sql.SQLException;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import services.EvenementService;

public class ListEvents implements Initializable {
    private Main app;

     @FXML
     private Button ae ;
      @FXML
    private TableColumn<Evenements, Integer>  eveIdColumn;
    @FXML
    private TableColumn<Evenements, String>  eveNameColumn;
    @FXML
    private TableColumn<Evenements, String> eveLieuColumn;
    @FXML
    private TableColumn<Evenements, String> evePrixColumn;
    @FXML
 
    private TableColumn<Evenements, Date> eveDateColumn;
     @FXML
    private TableView Evtable;
  
    private Evenements esav  ;
    private EvenementService eservice=new EvenementService();
  @FXML 
    RoutingEvents routingEvents=new RoutingEvents(this.app,this);
    @FXML

    private AnchorPane contained;
    

    public ListEvents() throws IOException {

    }
    
    
     @FXML
    private void populateEmployees (ObservableList<Evenements> empData) throws ClassNotFoundException {
        //Set items to the employeeTable
        Evtable.setItems(empData);
    }
    
    
    @FXML
    private void searchEmployees(ActionEvent actionEvent) throws IOException  , ClassNotFoundException {
        //Get all Employees information
        ObservableList<Evenements> empData = eservice.selectAll();
        //Populate Employees on TableView
        populateEmployees(empData);
        
    }

    public AnchorPane getContained() {
        return contained;
    }

    public void setContained(AnchorPane contained) {
        this.contained = contained;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eveIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        eveNameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        eveLieuColumn.setCellValueFactory(new PropertyValueFactory<>("lieu"));
       evePrixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        eveDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
    public void setApp(Main app)  {
        this.app = app;
    }
        @FXML

    private void ajout(ActionEvent event) throws IOException {
           routingEvents.fromListToAdd();
    }
}



