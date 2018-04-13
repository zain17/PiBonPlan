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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import services.EvenementService;

public class ListEvents implements Initializable {
    private Main app;
    @FXML
    private TextField eid ;

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
  
    public Evenements esav  ;
    private EvenementService eservice=new EvenementService();
    RoutingEvents routingEvents=new RoutingEvents(this.app,this);
    @FXML
    private AnchorPane contained;
    @FXML
    private Button searchEmpsBtn;
    
    
 

    public ListEvents() throws IOException {

    }
    
    @FXML
    private void delete(ActionEvent actionEvent) throws IOException  , ClassNotFoundException {
   EvenementService e = new EvenementService();
   
   
    if (eid.getText().isEmpty()) {
        
     Alert alert1 = new Alert(Alert.AlertType.INFORMATION);   
    alert1.setTitle("Information Dialog");
    alert1.setContentText("Saisir le ID !!");
    alert1.showAndWait();
        
    }else {
    e.supprimer(Integer.parseInt(eid.getText()));
         Alert alert1 = new Alert(Alert.AlertType.INFORMATION);   
    alert1.setTitle("Information Dialog");
    alert1.setContentText("Evenement Supprimer !!");
    alert1.showAndWait();
    
    }
      
    
    }
    
    
    
    
    
    
    
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
    
    @FXML
    private void modifier(ActionEvent event) throws IOException {
   // routingEvents.fromListToMod();
    
     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Events/modifyEvents.fxml"));

       AnchorPane forInserting = fxmlLoader.load();
        ModifyEventsController c = (ModifyEventsController) fxmlLoader.getController();
        c.setEvenement(esav);
        int eid = Integer.parseInt(this.eid.getText());
        //eid hia l id textfield
        
        c.setUser(eid);
        
        //boutton hedha update ihez interface evenements ! 
            
            contained.getChildren().clear();
            contained.getChildren().add(forInserting);
 

      
    }
    
    @FXML
     private void listall(ActionEvent event) throws IOException {
        routingEvents.listall();
    }
}



