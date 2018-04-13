/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Events;

import entites.Evenements;
import gui.Main;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import services.EvenementService;

/**
 *
 * @author DELL
 */
public class SuggestionController implements Initializable {
    @FXML
    private TextField re ;
    @FXML
    private AnchorPane contained;
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
     private Main app ;
     
         private EvenementService eservice=new EvenementService();

     
     
 private void populateEmployees (ObservableList<Evenements> empData) throws ClassNotFoundException {
        //Set items to the employeeTable
        Evtable.setItems(empData);
    }
    
     public void setApp(Main app)  {
        this.app = app;
    }
     
     public AnchorPane getContained() {
        return contained;
    }
    @FXML
    private void lista(ActionEvent actionEvent) throws IOException  , ClassNotFoundException {
        //Get all Employees information
        ObservableList<Evenements> empData = eservice.selectALL();
        //Populate Employees on TableView
        populateEmployees(empData);
        
    }
 public void setContained(AnchorPane contained) {
        this.contained = contained;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eveIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        eveNameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        eveLieuColumn.setCellValueFactory(new PropertyValueFactory<>("lieu"));
       evePrixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        eveDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        
                ObservableList<Evenements> empData = eservice.selectALL();

         FilteredList<Evenements> filteredData = new FilteredList<>(empData, p -> true);
         
        re.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Evenements -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Evenements.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (Evenements.getLieu().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });
         SortedList<Evenements> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(Evtable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        Evtable.setItems(sortedData);
             
             
          }}
