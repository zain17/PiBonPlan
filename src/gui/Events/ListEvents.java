package gui.Events;

import gui.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ListEvents implements Initializable {
    private Main app;
     
    @FXML
    private AnchorPane contained;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void setApp(Main app)  {
        this.app = app;
    }
       private void setNode(Node node) {
        contained.getChildren().clear();
        contained.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
        @FXML
    private void ajout(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Events/AjoutEvents.fxml"));

        AnchorPane parentContent = fxmlLoader.load();
           AjoutEvents c = (AjoutEvents) fxmlLoader.getController();
      
        c.setApp(app);
         // System.out.println((c.app.getLoggedUser().getUsername() != null) + "****");
        setNode(parentContent);
        
    }
    
    
}
