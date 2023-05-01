package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.PersistentData;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MaterialViewController extends ViewUtilities implements Initializable {
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textStock;
    @FXML
    private ComboBox comboBenef;
    @FXML
    private TextArea textDesc;
    @FXML
    private Label textErr;
    @FXML
    private Button btnNext_mod;
    @FXML
    private Button btnAdd_del;
    private boolean isMod = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBenef.setItems(FXCollections.observableArrayList("M", "F", "E", "T"));
        if (PersistentData.getMaterialesMod()!=null){
            isMod = true;
            btnNext_mod.setText("Modificar");
            btnAdd_del.setText("Borrar");
            textNombre.setText(PersistentData.getMaterialesMod().getMatNombre());
            textStock.setText(PersistentData.getMaterialesMod().getMatStock() +"");
            textDesc.setText(PersistentData.getMaterialesMod().getMatDescripcion());
            comboBenef.getSelectionModel().select(PersistentData.getMaterialesMod().getMatBeneficiario());
        }else{
            btnNext_mod.setVisible(false);
        }
    }

    public void actionNext_mod(ActionEvent actionEvent) {
    }

    public void actionAdd_del(ActionEvent actionEvent) {
    }

    public void actionCancel(ActionEvent actionEvent) {
    }
}
