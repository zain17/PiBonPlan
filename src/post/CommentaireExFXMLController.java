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
import entites.CommentaireEx;
import entites.Experiences;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import services.CommentaireExservice;
import services.ExperienceServices;

/**
 * FXML Controller class
 *
 * @author azerty
 */
public class CommentaireExFXMLController implements Initializable {
@FXML
    private VBox commentContainer;
    @FXML
    private TextArea commentBox;
    @FXML
    private Button addCommentbtn;
    @FXML
    private Button clearbtn;
    @FXML
    private JFXDrawersStack stackpane;
    private ArrayList<CommentaireEx> l;
      private int editComment ;
      private String url_uploads;
      CommentaireExservice cs = new CommentaireExservice() ;
        ExperienceServices  articleser = new ExperienceServices();
      List<Experiences> ListeProjet = articleser.getAllArticle();
      public Experiences annonce = new Experiences();
      int idexee = annonce.getId();
 
   
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         stackpane.toBack();
         System.out.println(""+idexee);
          this.refresh();   
    } 
     public void InitNow(){
        //System.out.println(annonce.getId());
        this.refresh();   
    }
      public void setAnnonce(int idexee) {
        this.idexee = idexee;
    }
    public void refresh(){
         commentContainer.getChildren().clear();
         
         l=cs.findByAnnonce(annonce.getId());
        if(!l.isEmpty()){
            for(CommentaireEx c : l){
                //init
                VBox actions = new VBox();
                    //load profile picutre
                     actions.setStyle("-fx-background-color: rgb(255.0, 255.0, 255.0);"
                        + "-fx-background-radius: 4.0;"
                        + "-fx-effect: dropshadow(gaussian, rgb(0.0, 0.0, 0.0, 0.15), 6.0, 0.7, 0.0,1.5);"
                        + "-fx-padding: 16 16 16 16;");
                
               
                
                
                 Image  profile_pic=new Image(getClass().getResource("no_picture.png").toString());
                
                ImageView profile_picture = new ImageView();
                profile_picture.setSmooth(true);
                profile_picture.setImage(profile_pic);
                profile_picture.setFitHeight(65);
                profile_picture.setFitWidth(80);
                profile_picture.setStyle("-fx-padding : 5px; -fx-border-insets: 4px;");
                Pane miniPane = new Pane();
                profile_picture.setFitWidth(80);
                miniPane.getChildren().add(profile_picture);
                miniPane.setStyle("-fx-padding : 10px;");
                
                
                VBox vb = new VBox();
                 vb.setStyle("-fx-background-color: rgb(255.0, 255.0, 255.0);"
                        + "-fx-background-radius: 4.0;"
                        + "-fx-effect: dropshadow(gaussian, rgb(0.0, 0.0, 0.0, 0.15), 6.0, 0.7, 0.0,1.5);"
                        + "-fx-padding: 16 16 16 16;");
              //  cs.getCommenterUser(c.getUser_id()).getUsername()
                Label nom = new Label("Amine Ouni");
                nom.setTextFill(Color.DARKBLUE);
                nom.setFont(Font.font("System", FontWeight.BOLD, 15));
                
                DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("E dd/LLL/yyyy");
                
                Label time = new Label( "  Edited" +"06/04/2018" );
                time.setTextFill(Color.GREY);
                time.setFont(Font.font("System", FontWeight.THIN, 9));
                
                Text content = new Text(c.getContenu());
                
                //Likes to do
                              
                vb.getChildren().add(nom);
                vb.getChildren().add(time);
                vb.getChildren().add(content);
                
                //edit del
                
                            BackgroundSize backgroundSize2 = new BackgroundSize(20, 13, true, true, true, false);

                    Image del = new Image(getClass().getResource("xx.png").toString());
                    Button btdel = new Button();
                    btdel.setBackground(new Background(new BackgroundImage(del, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize2)));
                    btdel.setOnMouseClicked(e -> 
                            {
                                //check before delete
                               JFXDialogLayout cont = new JFXDialogLayout();
                                cont.setHeading(new Text("Are you sure ?"));
                                cont.setBody(new Text("Delete this Comment ?\n"));
                                JFXDialog d = new JFXDialog(stackpane, cont, JFXDialog.DialogTransition.CENTER);
                                JFXButton buttonOK = new JFXButton("OK");
                                JFXButton buttonCancel = new JFXButton("Cancel");
                                buttonOK.setOnAction(new EventHandler<ActionEvent>(){
                                    @Override
                                    public void handle(ActionEvent event) {
                                       d.close();
                                       stackpane.toBack();
                                       cs.delete(c);
                                       refresh();
                                    }

                                });
                                buttonCancel.setOnAction(new EventHandler<ActionEvent>(){
                                    @Override
                                    public void handle(ActionEvent event) {
                                       d.close();
                                        stackpane.toBack();
                                    }

                                });
                                cont.setActions(buttonOK,buttonCancel);
                                stackpane.toFront();
                                d.show();
                                
                               
                            });

                    Image ed = new Image(getClass().getResource("edit.png").toString());
                    Button btEdit = new Button();
                    btEdit.setBackground(new Background(new BackgroundImage(ed, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize2)));
                    btEdit.setOnMouseClicked(e -> 
                            {
                                editComment=c.getId();
                                
                               this.refresh();
                            });
                    
                    actions.getChildren().add(btEdit);
                    actions.getChildren().add(btdel);
                    actions.setAlignment(Pos.TOP_RIGHT);
             
                 
            //add
            HBox CommentH = new HBox();
            CommentH.setStyle("-fx-padding : 5px;");
            CommentH.getChildren().add(miniPane);
            CommentH.getChildren().add(vb);
            CommentH.getChildren().add(actions);
            commentContainer.getChildren().add(CommentH);
            }

        }
        else {
            Label lf= new Label("No comments submitted yet on this post , Feel free to add one...");
            lf.setTextFill(Color.GRAY);
            commentContainer.getChildren().add(lf);
        }
         
     
         
    }
      @FXML
    private void clearBox(MouseEvent event) {
        if(editComment!=0){
            addCommentbtn.setText("Add");
            clearbtn.setText("Clear");
            editComment=0;
        }
        commentBox.clear();
    }
   
        
    
        @FXML
    private void addComment(MouseEvent event) {
           
           CommentaireEx c= new CommentaireEx(1,commentBox.getText().toString(),annonce.getId());
           cs.add(c);
           this.refresh();
          
     
    }
    
}