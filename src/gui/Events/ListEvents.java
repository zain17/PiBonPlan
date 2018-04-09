package gui.Events;

import gui.ContainerController;
import gui.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.Routers.RoutingEvents;
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
    RoutingEvents routingEvents=new RoutingEvents(this.app,this);
    @FXML
    private AnchorPane contained;

    public ListEvents() throws IOException {

    }

    public AnchorPane getContained() {
        return contained;
    }

    public void setContained(AnchorPane contained) {
        this.contained = contained;
    }

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
           routingEvents.fromListToAdd();
    }


    public void addEvents(ActionEvent actionEvent) {
    }
}
