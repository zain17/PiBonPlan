/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package post;

import com.jfoenix.controls.JFXDrawersStack;
import entites.Experiences;
import entites.Revueee;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import services.ExperienceServices;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class RevueController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextArea description;
    @FXML
    private ImageView img;
       @FXML
    private Button uploim;
          @FXML
    private Button ajout;
    File s;
    @FXML
    private GridPane Grid;
    @FXML
    private JFXDrawersStack AnchorId, commentPane;
    //  static final String KeyInt = "KEY_INT";
    // Preferences preferences = Preferences.userRoot().node("java-buddy");
    Preferences prefs = Preferences.userNodeForPackage(PostFXMLController.class);
    ExperienceServices articleser = new ExperienceServices();
    Experiences ex = new Experiences();
    Revueee exe = new Revueee();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           AnchorId.getChildren().clear();
      ajout.setStyle("-fx-background-color: rgb(255.0, 255.0, 255.0);"+
              "-fx-background-color: #0000ff;"
              +"-fx-text-fill: #ffff;"
                    + "-fx-background-radius: 6.0;"
                    + "-fx-effect: dropshadow(gaussian, rgb(0.0, 0.0, 0.0, 0.15), 6.0, 0.7, 0.0,1.5);"
                    + "-fx-padding: 16 8 8 8;");
      uploim.setStyle("-fx-background-color: rgb(255.0, 255.0, 255.0);"+
              "-fx-background-color: #0000ff;"
              +"-fx-text-fill: #ffff;"
                    + "-fx-background-radius: 6.0;"
                    + "-fx-effect: dropshadow(gaussian, rgb(0.0, 0.0, 0.0, 0.15), 6.0, 0.7, 0.0,1.5);"
                    + "-fx-padding: 16 8 8 8;");
      
        List<Revueee> ListeProjet = articleser.getAllrevue();
        System.out.println(ListeProjet.toString());

        int k = 0;
        int i = 0;
        
          for (int j = 0; j < ListeProjet.size(); j++) {
              System.out.println(ListeProjet.get(j).getDescription());
                Label l = new Label(ListeProjet.get(j).getDescription());

            l.setFont(new javafx.scene.text.Font("Arial", 30));
            Label l1 = new Label("Resumer");
            l1.setFont(new javafx.scene.text.Font("Arial", 30));
            byte[] imge = ListeProjet.get(j).getPhoto();

            Image imgee = new Image(new ByteArrayInputStream(imge));
             ImageView Iv = new ImageView();
            Iv.setImage(imgee);
            Iv.setFitWidth(100);
            Iv.setStyle("-fx-padding: 1 1 1 1;");
            Iv.setFitHeight(250);
            Iv.setFitWidth(250);
            BackgroundSize backgroundSize2 = new BackgroundSize(20, 13, true, true, true, false);
            BackgroundSize backgroundSize3 = new BackgroundSize(512, 512, true, true, true, false);
            JFXDrawersStack hhhhh = new JFXDrawersStack();
                Label vfg = new Label(String.valueOf(ListeProjet.get(j).getId()));
                      VBox v = new VBox(hhhhh, new Label("Titre"), vfg, l, Iv, l1, new Label(ListeProjet.get(j).getDescription()), new Label("Evolution du financement"),
                    new HBox( new Label("       "), new Label(""),
                            new Label("         "),  new Label("    ")),new Label(" \n   ")
                   );
            v.setStyle("-fx-background-color: rgb(255.0, 255.0, 255.0);"
                    + "-fx-background-radius: 4.0;"
                    + "-fx-effect: dropshadow(gaussian, rgb(0.0, 0.0, 0.0, 0.15), 6.0, 0.7, 0.0,1.5);"
                    + "-fx-padding: 16 16 16 16;");
            if (i == j / 2) {
                k = 0;
                i++;

            }
            Grid.add(v, k, i);
            Grid.setHgap(5); //horizontal gap in pixels => that's what you are asking for
            Grid.setVgap(5); //vertical gap in pixels
            k++;
            // }
          }
          
          
   AnchorId.getChildren().add(Grid);
        // TODO
    }

   ExperienceServices ef = new ExperienceServices();

    public void InitNow() {
        //System.out.println(annonce.getId());
        this.refrech();
    }
    
    public void refrech(){
            AnchorId.getChildren().clear();
      ajout.setStyle("-fx-background-color: rgb(255.0, 255.0, 255.0);"+
              "-fx-background-color: #0000ff;"
              +"-fx-text-fill: #ffff;"
                    + "-fx-background-radius: 6.0;"
                    + "-fx-effect: dropshadow(gaussian, rgb(0.0, 0.0, 0.0, 0.15), 6.0, 0.7, 0.0,1.5);"
                    + "-fx-padding: 16 8 8 8;");
      uploim.setStyle("-fx-background-color: rgb(255.0, 255.0, 255.0);"+
              "-fx-background-color: #0000ff;"
              +"-fx-text-fill: #ffff;"
                    + "-fx-background-radius: 6.0;"
                    + "-fx-effect: dropshadow(gaussian, rgb(0.0, 0.0, 0.0, 0.15), 6.0, 0.7, 0.0,1.5);"
                    + "-fx-padding: 16 8 8 8;");
      
        List<Revueee> ListeProjet = articleser.getAllrevue();

        int k = 0;
        int i = 0;
        
          for (int j = 0; j < ListeProjet.size(); j++) {
                Label l = new Label(ListeProjet.get(j).getDescription());

            l.setFont(new javafx.scene.text.Font("Arial", 30));
            Label l1 = new Label("Resumer");
            l1.setFont(new javafx.scene.text.Font("Arial", 30));
            byte[] imge = ListeProjet.get(j).getPhoto();

            Image imgee = new Image(new ByteArrayInputStream(imge));
             ImageView Iv = new ImageView();
            Iv.setImage(imgee);
            Iv.setFitWidth(100);
            Iv.setStyle("-fx-padding: 1 1 1 1;");
            Iv.setFitHeight(250);
            Iv.setFitWidth(250);
            BackgroundSize backgroundSize2 = new BackgroundSize(20, 13, true, true, true, false);
            BackgroundSize backgroundSize3 = new BackgroundSize(512, 512, true, true, true, false);
            JFXDrawersStack hhhhh = new JFXDrawersStack();
                Label vfg = new Label(String.valueOf(ListeProjet.get(j).getId()));
                      VBox v = new VBox(hhhhh, new Label("Titre"), vfg, l, Iv, l1, new Label(ListeProjet.get(j).getDescription()), new Label("Evolution du financement"),
                    new HBox( new Label("       "), new Label(""),
                            new Label("         "),  new Label("    ")),new Label(" \n   ")
                   );
            v.setStyle("-fx-background-color: rgb(255.0, 255.0, 255.0);"
                    + "-fx-background-radius: 4.0;"
                    + "-fx-effect: dropshadow(gaussian, rgb(0.0, 0.0, 0.0, 0.15), 6.0, 0.7, 0.0,1.5);"
                    + "-fx-padding: 16 16 16 16;");
            if (i == j / 2) {
                k = 0;
                i++;

            }
          }

        // TODO
    }
    private void notifNowWarning(String title, String content) {

        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text("\t" + content)
                .hideAfter(Duration.seconds(4))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    @FXML
    private void btnAction(ActionEvent event) throws FileNotFoundException {
        Revueee experience = new Revueee(1, s, description.getText().toString());
        ef.ajouterreve(experience);
           refrech();
        notifNowWarning("Experience", "Experience ajouter avec succée :D");
    }

    @FXML
    private void uploadBtnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        try {
            s = file;
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image;
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            img.setImage(image);
        } catch (IOException ex) {
        }
    }    
    
}
