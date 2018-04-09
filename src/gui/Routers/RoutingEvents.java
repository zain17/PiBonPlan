/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Routers;

import gui.Events.AjoutEvents;
import gui.Events.ListEvents;
import gui.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sboula
 */
public class RoutingEvents {
    private Main main;
    private AjoutEvents ajoutEvents;
    private ListEvents listEvents;

    public RoutingEvents(Main main, ListEvents listEvents) throws IOException {
        this.main = main;
        this.listEvents = listEvents;
    }

    public void fromListToAdd() throws IOException {
        listEvents.getContained().getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("/gui/Events/AjoutEvents.fxml")));
    }
    
    
}
