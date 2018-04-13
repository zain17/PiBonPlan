/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import com.ernieyu.feedparser.FeedException;
import com.ernieyu.feedparser.Item;
import gui.Main;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;

/**
 *
 * @author aminos
 */
public class RssThread extends Thread {

    public Main app;
 

    public RssThread(String str, Main app) {
        super(str);
        this.app = app;

    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Rss thread beating!");
         
            if (app.rssList != null) {
                try {
                    List<Item> newI = new RssReader().getStreamElements();
                    int nbr = newer(newI, app.rssList);
                    System.out.println("thread: " + newI.size() + " app " + app.rssList);
                    if (nbr > 0) {
                        System.out.println("Founndd!!");
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                // Update UI here.
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Nouveaux article(s) ajouté(s)");
                                alert.setHeaderText("Allez au blog, il y'a des nouveautés");
                                String s = nbr + " nouveauté(s)";
                                alert.setContentText(s);
                                alert.show();
                                app.rssList = newI;
                            }

                        });

                        
                    }

                    System.out.println("Equal? " + (app.rssList != newI));

                } catch (IOException ex) {
                    Logger.getLogger(RssThread.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FeedException ex) {
                    Logger.getLogger(RssThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    app.rssList = new RssReader().getStreamElements();
                    System.out.println("Affecting!");
                } catch (IOException ex) {
                    Logger.getLogger(RssThread.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FeedException ex) {
                    Logger.getLogger(RssThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {
                sleep(5000);
            } catch (InterruptedException e) {
            }
        }
    }

    private int newer(List<Item> streamElements, List<Item> rssList) {
        int j = 0;
        for (int i = 0; i < streamElements.size(); i++) {
            boolean there = false;
            for (int t = 0; t < rssList.size(); t++) {
                if (streamElements.get(i).getTitle().equals(rssList.get(t).getTitle())) {
                    there = true;
                }
            }
            if (!there) {
                j++;
            }
        }
        return j;
    }

}
