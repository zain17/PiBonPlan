/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Events;

import entites.Evenements;
import entites.Gouvernorat;
import gui.Main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import security.Authenticator;
import services.EvenementService;
import services.ServiceGouvernorat;

/**
 * FXML Controller class
 *
 * @author Sboula
 */
public class ModifyEventsController implements Initializable {

      FileChooser saveFileChooser = new FileChooser();    
    File saveFile;
    File srcFile, destFile;
    
     private  Main app ;
     @FXML
     private ComboBox<String> cb ;
    @FXML 
    private TextField n;
    @FXML private TextArea des ;
    @FXML private TextField a;
    @FXML private TextField t ;
    @FXML private TextField p ;
    @FXML private Button e ; 
    @FXML private DatePicker dd ; 
    @FXML private Button img ;
    @FXML private TextField id ;
    public int bo ;
    @FXML private TextField ty ;
        private Evenements esav  ;
    private EvenementService eservice=new EvenementService();

   public void fill (String idd){
       
       id.setText(idd);
        
    }
   
    
      @Override
    public void initialize(URL location, ResourceBundle resources) {
        esav=new Evenements();
        ServiceGouvernorat serviceGouvernorat=new ServiceGouvernorat();
        ArrayList<Gouvernorat> gouvernorats=serviceGouvernorat.selectAllEager();
        ArrayList<String>gouvernoratsStream=new ArrayList<>();
        gouvernorats.stream().forEach(gouvernorat -> gouvernoratsStream.add(gouvernorat.getName()));
        ArrayList<String>gouvernoratsStreamVilles=new ArrayList<>();
       ObservableList<String> gouvernoratObservableList = FXCollections.observableArrayList(gouvernoratsStream);

        cb.setItems(gouvernoratObservableList);
    }

    public Main getApp() {
        return app;
    }

    public void setApp(Main main) {
        this.app = main;
    }
    
    
    public void setUser(int user_id){
    this.bo = user_id;
    }
    
 
    @FXML
    private void UploadImage(ActionEvent event) {
        
                File file = saveFileChooser.showOpenDialog(null);
                
        if (file != null) {                
            srcFile = file;
             
            
              if (srcFile != null) {
                try {
                    String p = System.getProperty("user.dir")+"/src/images/evenement/"+srcFile.getName();
                    copyFile(srcFile, new File(p));
                } catch (IOException ex) {
                    Logger.getLogger(AjoutEvents.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }
    
     public void copyFile(File sourceFile, File destFile) throws IOException {
        if ( !destFile.exists() ) { destFile.createNewFile(); }

    try (
        FileChannel in = new FileInputStream( sourceFile ).getChannel();
        FileChannel out = new FileOutputStream( destFile ).getChannel(); ) {

        out.transferFrom( in, 0, in.size() );
    }
     }
     
     private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
	        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
	        return sDate;
	    }
    
     
     
    public void enregistrer(ActionEvent actionEvent) {
       
       if(dd.getValue()== null){
           Alert alert1 = new Alert(Alert.AlertType.INFORMATION);   
    alert1.setTitle("Information Dialog");
    alert1.setContentText("Date !!");
    alert1.showAndWait();
           
       }else {
    LocalDate localDate = dd.getValue();
    Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
    Date date;
        date = Date.from(instant);
            
        try {
        SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ymd = ymdFormat.format(date);
          
         
             esav.setDate( convertUtilToSql(ymdFormat.parse(ymd)) );
             System.out.print(convertUtilToSql(ymdFormat.parse(ymd)));
        }catch(ParseException exp) {
    exp.printStackTrace();
}}
            

            
            
        
     
    


    
    
//tel controle

            
           if(t.getText().length()!=8 || Pattern.matches("[a-zA-Z]", t.getText())) {
              Alert alert1 = new Alert(Alert.AlertType.INFORMATION);   
    alert1.setTitle("Information Dialog");
    alert1.setContentText("TEL !!");
    alert1.showAndWait();
           }
           else if (srcFile.getName().isEmpty()){
               
    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);   
    alert1.setTitle("Information Dialog");
    alert1.setContentText("Choisir une Image !!");
    alert1.showAndWait();
           }
           
           else if(Pattern.matches("[a-z]", p.getText()) || p.getText().isEmpty() ) {
               Alert alert1 = new Alert(Alert.AlertType.INFORMATION);   
    alert1.setTitle("Information Dialog");
    alert1.setContentText("Prix  !!");
    alert1.showAndWait();
               
           } 
           else if ((String)cb.getValue() == null){
               
               
           
    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);   
    alert1.setTitle("Information Dialog");
    alert1.setContentText(" Selectionnez le Lieu  !");
    alert1.showAndWait();
           }else if (id.getText().isEmpty()){
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);   
    alert1.setTitle("Information Dialog");
    alert1.setContentText(" L'id de l'evenement  !");
    alert1.showAndWait();
           }
           
           
           
           else  {
               
               
               System.out.println(Integer.parseInt(id.getText())) ; 
               
            Evenements e =  eservice.find(bo) ;
             
    esav.setId(Integer.parseInt(id.getText()));
  n.setText(e.getNom());
    esav.setAdresse(a.getText());
    esav.setTel(t.getText());
    esav.setBrochure(srcFile.getName());
    esav.setDescription(des.getText());
    esav.setPrix(Integer.parseInt(p.getText()));
    esav.setLieu((String)cb.getValue());
               
               eservice.modifier(esav);
           
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information Dialog");
    alert.setContentText("Modification reussite !!");
    alert.showAndWait();
           } 
    
   
}









}
