/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package post;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawersStack;
import entites.Experiences;
import entites.Jaime;
import gui.Main;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import services.ExperienceServices;

/**
 * FXML Controller class
 *
 * @author Nizar Elhraiech
 */
public class PostFXMLController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
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
        List<Experiences> ListeProjet = articleser.getAllArticle();

        int k = 0;
        int i = 0;

        //  for(int i =0 ; i<3 ;i++){
        for (int j = 0; j < ListeProjet.size(); j++) {

            // Experiences kg= ListeProjet.get(j);
            // System.out.println(ListeProjet.get(j));
            Label l = new Label(ListeProjet.get(j).getDescription());

            l.setFont(new javafx.scene.text.Font("Arial", 30));
            Label l1 = new Label("Resumer");
            l1.setFont(new javafx.scene.text.Font("Arial", 30));
            byte[] imge = ListeProjet.get(j).getPhoto();

            Image imgee = new Image(new ByteArrayInputStream(imge));
            //  img.setImage(imgee);
            // Image im = new Image("file:///C://Users//azerty//Downloads//5edmetmiboun//image//"+ListeProjet.get(j).getPhoto());
            ImageView Iv = new ImageView();
            Iv.setImage(imgee);
            Iv.setFitWidth(100);
            Iv.setStyle("-fx-padding: 1 1 1 1;");
            Iv.setFitHeight(250);
            Iv.setFitWidth(250);
            BackgroundSize backgroundSize2 = new BackgroundSize(20, 13, true, true, true, false);
            BackgroundSize backgroundSize3 = new BackgroundSize(512, 512, true, true, true, false);
            JFXDrawersStack hhhhh = new JFXDrawersStack();
            HBox actions = new HBox();
            Rating rating = new Rating();
            rating.setUpdateOnHover(true);
            
            //load profile picutre
            Image del = new Image(getClass().getResource("xx.png").toString());
            Button btdel = new Button();
            btdel.setBackground(new Background(new BackgroundImage(del, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize2)));
            Label vfg = new Label(String.valueOf(ListeProjet.get(j).getId()));
            vfg.setVisible(false);

            Image ed = new Image(getClass().getResource("edit.png").toString());
            Button btEdit = new Button();
            btEdit.setBackground(new Background(new BackgroundImage(ed, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize2)));
            actions.setStyle("-fx-background-color: rgb(255.0, 255.0, 255.0);"
                    + "-fx-background-radius: 6.0;"
                    + "-fx-effect: dropshadow(gaussian, rgb(0.0, 0.0, 0.0, 0.15), 6.0, 0.7, 0.0,1.5);"
                    + "-fx-padding: 16 8 8 8;");
            actions.getChildren().add(btEdit);
            actions.getChildren().add(btdel);
            actions.setAlignment(Pos.TOP_RIGHT);
            
                 btEdit.setOnMouseClicked(e -> 
                            {
                              
                               JFXDialogLayout cont = new JFXDialogLayout();
                                cont.setHeading(new Text("Are you sure ?"));
                                cont.setBody(new Text("Update this article ?\n"));
                                JFXDialog d = new JFXDialog(hhhhh, cont, JFXDialog.DialogTransition.CENTER);
                                TextArea textup = new TextArea("");
                                JFXButton buttonOK = new JFXButton("OK");
                                JFXButton buttonCancel = new JFXButton("Cancel");
                              cont.setBody(textup);
                              
                                buttonOK.setOnAction(new EventHandler<ActionEvent>(){
                                    @Override
                                    public void handle(ActionEvent event) {
                                       d.close();
                                       hhhhh.toBack();
                                       ef.edit(textup.getText().toString(),Integer.valueOf(vfg.getText()));
                                      
                                       refrech();
                                       
                                    }

                                });
                                  buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        d.close();
                        hhhhh.toBack();

                    }

                });
                cont.setActions(buttonOK, buttonCancel);
                hhhhh.toFront();
                d.show();

            });
            btdel.setOnMouseClicked(e
                    -> {

                JFXDialogLayout cont = new JFXDialogLayout();
                cont.setHeading(new Text("Are you sure ?"));
                cont.setBody(new Text("Delete this article ?\n"));
                JFXDialog d = new JFXDialog(hhhhh, cont, JFXDialog.DialogTransition.CENTER);
                JFXButton buttonOK = new JFXButton("OK");
                JFXButton buttonCancel = new JFXButton("Cancel");
                buttonOK.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        d.close();
                        hhhhh.toBack();
                        ef.delete(Integer.valueOf(vfg.getText()));

                        articleser.getAllArticle().clear();
                        // System.out.println(kg.getId());
                        // articleser.
                        //  AnchorId.getChildren().clear();

                        refrech();

                    }

                });
                buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        d.close();
                        hhhhh.toBack();

                    }

                });
                cont.setActions(buttonOK, buttonCancel);
                hhhhh.toFront();
                d.show();

            });
            Image comm = new Image(getClass().getResource("chat.png").toString());
            Image jaimeeee = new Image(getClass().getResource("like12.png").toString());
            Image jaime2 = new Image(getClass().getResource("like2.png").toString());
            Button jaime = new Button();
            jaime.setBackground(new Background(new BackgroundImage(jaimeeee, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize3)));
            Button commenter = new Button();
            commenter.setBackground(new Background(new BackgroundImage(comm, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize3)));

            commenter.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        FXMLLoader fXMLLoader = new FXMLLoader();

                        JFXDialogLayout content = new JFXDialogLayout();
                        //  content.setHeading(new Text("Comment "+annonce.getName()+" post"));

                        Pane pane = fXMLLoader.load(getClass().getResource("CommentaireExFXML.fxml").openStream());

                        //load the new controller to inject values
                        CommentaireExFXMLController ctrl = (CommentaireExFXMLController) fXMLLoader.getController();

                        ctrl.annonce.setId(Integer.valueOf(vfg.getText()));

                        System.out.println(Integer.valueOf(vfg.getText()));
                        pane.sceneProperty().addListener((obs, oldScene, newScene) -> {
                            if (newScene == null) {
                                //System.out.println("Error Viewing comment Pane ");
                            } else {
                                ctrl.InitNow();
                            }
                        });
                        content.setBody(pane);
                        JFXDialog dialog = new JFXDialog(hhhhh, content, JFXDialog.DialogTransition.CENTER);
                        JFXButton buttonCancel = new JFXButton("Close");

                        buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                dialog.close();
                                hhhhh.toBack();
                            }
                        });
                        content.setActions(buttonCancel);
                        hhhhh.toFront();
                        dialog.show();
                    } catch (IOException ex) {
                        Logger.getLogger(PostFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                /*     try {
                          
                        Parent   fxmlLoader =  FXMLLoader.load(getClass().getResource("CommentaireExFXML.fxml"));
                            System.out.println(vfg.getText());
      
      Stage s = new Stage();
      s.setScene(new Scene(fxmlLoader));
      s.show();
      
                        }
                        catch (IOException ex) {
                            Logger.getLogger(PostFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }*/
            });

            if (listJaime().size() > 0) {
                for (int s = 0; s < listJaime().size(); s++) {
                    if (listJaime().get(s).getIdEexperience() == Integer.valueOf(vfg.getText())) {
                        jaime.setBackground(new Background(new BackgroundImage(jaime2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize3)));

                    }
                }
            }

            jaime.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int etat = articleser.likeExperience(Integer.valueOf(vfg.getText()), 1);
                    if (etat == 0) {
                        jaime.setBackground(new Background(new BackgroundImage(jaimeeee, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize3)));
                        refrech();
                    } else {
                        jaime.setBackground(new Background(new BackgroundImage(jaime2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize3)));
                        refrech();
                    }

                }
            });

            VBox v = new VBox(hhhhh, actions, new Label("Titre"), vfg, l, Iv, l1, new Label(ListeProjet.get(j).getDescription()), new Label("Evolution du financement"),
                    new HBox(jaime, new Label("       "), new Label(String.valueOf(ListeProjet.get(j).getNbJaime())),
                            new Label("         "), commenter, new Label("    ")),new Label(" \n   "),rating
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
            // k++;

        }
        AnchorId.getChildren().add(Grid);

        // TODO
    }
    ExperienceServices ef = new ExperienceServices();

    public void InitNow() {
        //System.out.println(annonce.getId());
        this.refrech();
    }

    public void refrech() {
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
        List<Experiences> ListeProjet = articleser.getAllArticle();

        int k = 0;
        int i = 0;

        //  for(int i =0 ; i<3 ;i++){
        for (int j = 0; j < ListeProjet.size(); j++) {

            // Experiences kg= ListeProjet.get(j);
            // System.out.println(ListeProjet.get(j));
            Label l = new Label(ListeProjet.get(j).getDescription());

            l.setFont(new javafx.scene.text.Font("Arial", 30));
            Label l1 = new Label("Resumer");
            l1.setFont(new javafx.scene.text.Font("Arial", 30));
            byte[] imge = ListeProjet.get(j).getPhoto();

            Image imgee = new Image(new ByteArrayInputStream(imge));
            //  img.setImage(imgee);
            // Image im = new Image("file:///C://Users//azerty//Downloads//5edmetmiboun//image//"+ListeProjet.get(j).getPhoto());
            ImageView Iv = new ImageView();
            Iv.setImage(imgee);
            Iv.setFitWidth(100);
            Iv.setStyle("-fx-padding: 1 1 1 1;");
            Iv.setFitHeight(250);
            Iv.setFitWidth(250);
            BackgroundSize backgroundSize2 = new BackgroundSize(20, 13, true, true, true, false);
            BackgroundSize backgroundSize3 = new BackgroundSize(512, 512, true, true, true, false);
            JFXDrawersStack hhhhh = new JFXDrawersStack();
            HBox actions = new HBox();
             Rating rating = new Rating();
            rating.setUpdateOnHover(true);
            //load profile picutre
            Image del = new Image(getClass().getResource("xx.png").toString());
            Button btdel = new Button();
            btdel.setBackground(new Background(new BackgroundImage(del, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize2)));
            Label vfg = new Label(String.valueOf(ListeProjet.get(j).getId()));
            vfg.setVisible(false);

            Image ed = new Image(getClass().getResource("edit.png").toString());
            Button btEdit = new Button();
            btEdit.setBackground(new Background(new BackgroundImage(ed, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize2)));
            actions.setStyle("-fx-background-color: rgb(255.0, 255.0, 255.0);"
                    + "-fx-background-radius: 6.0;"
                    + "-fx-effect: dropshadow(gaussian, rgb(0.0, 0.0, 0.0, 0.15), 6.0, 0.7, 0.0,1.5);"
                    + "-fx-padding: 16 8 8 8;");
            actions.getChildren().add(btEdit);
            actions.getChildren().add(btdel);
            actions.setAlignment(Pos.TOP_RIGHT);
            
              btEdit.setOnMouseClicked(e -> 
                            {
                              
                               JFXDialogLayout cont = new JFXDialogLayout();
                                cont.setHeading(new Text("Are you sure ?"));
                                cont.setBody(new Text("Update this article ?\n"));
                                JFXDialog d = new JFXDialog(hhhhh, cont, JFXDialog.DialogTransition.CENTER);
                                TextArea textup = new TextArea("");
                                JFXButton buttonOK = new JFXButton("OK");
                                JFXButton buttonCancel = new JFXButton("Cancel");
                              cont.setBody(textup);
                              
                                buttonOK.setOnAction(new EventHandler<ActionEvent>(){
                                    @Override
                                    public void handle(ActionEvent event) {
                                       d.close();
                                       hhhhh.toBack();
                                       ef.edit(textup.getText().toString(),Integer.valueOf(vfg.getText()));
                                      
                                       refrech();
                                       
                                    }

                                });
                                  buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        d.close();
                        hhhhh.toBack();

                    }

                });
                cont.setActions(buttonOK, buttonCancel);
                hhhhh.toFront();
                d.show();

            });
            btdel.setOnMouseClicked(e
                    -> {

                JFXDialogLayout cont = new JFXDialogLayout();
                cont.setHeading(new Text("Are you sure ?"));
                cont.setBody(new Text("Delete this article ?\n"));
                JFXDialog d = new JFXDialog(hhhhh, cont, JFXDialog.DialogTransition.CENTER);
                JFXButton buttonOK = new JFXButton("OK");
                JFXButton buttonCancel = new JFXButton("Cancel");
                buttonOK.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        d.close();
                        hhhhh.toBack();
                        ef.delete(Integer.valueOf(vfg.getText()));

                        articleser.getAllArticle().clear();
                       

                        refrech();

                    }

                });
                buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        d.close();
                        hhhhh.toBack();

                    }

                });
                cont.setActions(buttonOK, buttonCancel);
                hhhhh.toFront();
                d.show();

            });
            Image comm = new Image(getClass().getResource("chat.png").toString());
            Image jaimeeee = new Image(getClass().getResource("like12.png").toString());
            Image jaime2 = new Image(getClass().getResource("like2.png").toString());
            Button jaime = new Button();
            jaime.setBackground(new Background(new BackgroundImage(jaimeeee, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize3)));
            Button commenter = new Button();
            commenter.setBackground(new Background(new BackgroundImage(comm, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize3)));

            commenter.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        FXMLLoader fXMLLoader = new FXMLLoader();

                        JFXDialogLayout content = new JFXDialogLayout();
                        //  content.setHeading(new Text("Comment "+annonce.getName()+" post"));

                        Pane pane = fXMLLoader.load(getClass().getResource("CommentaireExFXML.fxml").openStream());

                        //load the new controller to inject values
                        CommentaireExFXMLController ctrl = (CommentaireExFXMLController) fXMLLoader.getController();

                        ctrl.annonce.setId(Integer.valueOf(vfg.getText()));

                        System.out.println(Integer.valueOf(vfg.getText()));
                        pane.sceneProperty().addListener((obs, oldScene, newScene) -> {
                            if (newScene == null) {
                                //System.out.println("Error Viewing comment Pane ");
                            } else {
                                ctrl.InitNow();
                            }
                        });
                        content.setBody(pane);
                        JFXDialog dialog = new JFXDialog(hhhhh, content, JFXDialog.DialogTransition.CENTER);
                        JFXButton buttonCancel = new JFXButton("Close");

                        buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                dialog.close();
                                hhhhh.toBack();
                            }
                        });
                        content.setActions(buttonCancel);
                        hhhhh.toFront();
                        dialog.show();
                    } catch (IOException ex) {
                        Logger.getLogger(PostFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                /*     try {
                          
                        Parent   fxmlLoader =  FXMLLoader.load(getClass().getResource("CommentaireExFXML.fxml"));
                            System.out.println(vfg.getText());
      
      Stage s = new Stage();
      s.setScene(new Scene(fxmlLoader));
      s.show();
      
                        }
                        catch (IOException ex) {
                            Logger.getLogger(PostFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }*/
            });

            if (listJaime().size() > 0) {
                for (int s = 0; s < listJaime().size(); s++) {
                    if (listJaime().get(s).getIdEexperience() == Integer.valueOf(vfg.getText())) {
                        jaime.setBackground(new Background(new BackgroundImage(jaime2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize3)));

                    }
                }
            }

            jaime.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int etat = articleser.likeExperience(Integer.valueOf(vfg.getText()), 1);
                    if (etat == 0) {
                        jaime.setBackground(new Background(new BackgroundImage(jaimeeee, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize3)));
                        refrech();
                    } else {
                        jaime.setBackground(new Background(new BackgroundImage(jaime2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize3)));
                        refrech();
                    }

                }
            });

            VBox v = new VBox(hhhhh, actions, new Label("Titre"), vfg, l, Iv, l1, new Label(ListeProjet.get(j).getDescription()), new Label("Evolution du financement"),
                    new HBox(jaime, new Label("       "), new Label(String.valueOf(ListeProjet.get(j).getNbJaime())),
                            new Label("         "), commenter, new Label("    ")),new Label(" \n   "),rating
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
            // k++;

        }
        AnchorId.getChildren().add(Grid);
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
        Experiences experience = new Experiences(description.getText().toString(), s, 0, 0, 1);
        ef.ajouter(experience);
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

    public ArrayList<Jaime> listJaime() {
        return articleser.getAllMyLike(1);
    }
       private Main app;
  public void setApp(Main main) {
        this.app = main;
    }
}
