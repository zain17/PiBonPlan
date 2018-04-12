package gui.Events;

import entites.Evenements;
import gui.Main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.fxml.Initializable;

import java.net.URL;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import services.EvenementService;

public class AjoutEvents implements Initializable {
    
    
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
   
    @FXML private TextField ty ;
        private Evenements esav  ;
    private EvenementService eservice=new EvenementService();
     ObservableList<String> list = FXCollections.observableArrayList("hei","kk","bla"
    )   ;

   
    
      @Override
    public void initialize(URL location, ResourceBundle resources) {
        esav=new Evenements();
        cb.setItems(list);
    }

    public Main getApp() {
        return app;
    }

    public void setApp(Main main) {
        this.app = main;
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
}
            

            
            
        
        esav.setNom(n.getText());

    esav.setAdresse(a.getText());
    esav.setTel(t.getText());
 
    esav.setBrochure(srcFile.getName());
    esav.setDescription(des.getText());
    esav.setPrix(Integer.parseInt(p.getText()));
    
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information Dialog");
    alert.setContentText("Succes d'Ajout !!");

alert.showAndWait();
    
    

    eservice.ajouter(esav);
    
    
    }
    
   
}
