package gui.prettyNodes;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import entites.Etablissement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrettyEtabController  implements Initializable {
    Etablissement etablissement;
    @FXML
    private Label lbl_nom;
    @FXML
    private Label lbl_adress;
    @FXML
    private Label lbl_gouv;
    @FXML
    private Label lbl_vill;
    @FXML
    private ImageView img_etab;
    @FXML
    private Label lbl_onOff;
    @FXML
    private AnchorPane anc_etab;
    PrettyEtabController prc;

    public PrettyEtabController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().
                getResource("PrettyEtab.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void bindInfo(Etablissement et){
        lbl_nom.setText(et.getNom());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
