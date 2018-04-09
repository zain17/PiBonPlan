/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.blog;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author aminos
 */
public class ArticleElementController implements Initializable {

    @FXML
    private Hyperlink titre;
    @FXML
    private Label author;
    @FXML
    private Label text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void setAuthor(String auth, String date) {
        author.setText("Crée par " + auth + " le " + date);
    }
    
    public void setTitre(String titre) {
        this.titre.setText(titre);
    }
    
    public void setText(String text) {
        this.text.setText(text);
    }
    
}
