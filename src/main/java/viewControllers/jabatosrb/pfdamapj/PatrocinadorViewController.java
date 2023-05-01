package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.PersistentData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PatrocinadorViewController extends ViewUtilities implements Initializable {

    @FXML
    private TextField textNombre;
    @FXML
    private TextField textAporte;
    @FXML
    private TextArea textDesc;
    @FXML
    private Label textErr;
    @FXML
    private TextField textIBAN;
    @FXML
    private Button btnNext_mod;
    @FXML
    private Button btnAdd_del;
    private boolean isMod=true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (PersistentData.getPatrocinadorMod()!=null){
            btnNext_mod.setText("Modificar");
            btnAdd_del.setText("Borrar");
            textNombre.setText(PersistentData.getMaterialesMod().getMatNombre());
            textDesc.setText(PersistentData.getPatrocinadorMod().getDescripcion());
            textAporte.setText(PersistentData.getPatrocinadorMod().getAporte() + "");
            textIBAN.setText(PersistentData.getPatrocinadorMod().getCuentaBancaria());
        }else{
            btnNext_mod.setVisible(false);
        }
    }
}
