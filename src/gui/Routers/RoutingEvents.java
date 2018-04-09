/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Routers;

import gui.Events.AjoutEvents;
import gui.Main;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sboula
 */
public class RoutingEvents {
    private Main main;
    
    public RoutingEvents(Main main) 
    {
        this.main = main;
    }
    
    public void gotoAjoutE() {
        try {
               AjoutEvents ae = (AjoutEvents) main.replaceSceneContent("AjoutEvents.fxml");
            ae.setApp(main);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
