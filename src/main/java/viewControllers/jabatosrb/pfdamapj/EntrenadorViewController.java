package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.DateFormat;
import jabatosrb.pfdampj.PersistentData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class EntrenadorViewController extends ViewUtilities implements Initializable {
    @FXML
    private Label textErr;
    @FXML
    private Button btnNext_mod;
    @FXML
    private Button btnAdd_del;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textApellidos;
    @FXML
    private TextField textTelefono;
    @FXML
    private TextField textDni;
    @FXML
    private TextField textIBAN;
    @FXML
    private TextField textSalario;
    @FXML
    private TextField textCategoria;
    @FXML
    private DatePicker dateNacimiento;
    private boolean isMod = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(PersistentData.getEntrenadorMod()!=null){
            isMod=true;
            btnNext_mod.setText("Modificar");
            btnAdd_del.setText("Borrar");
            textNombre.setText(PersistentData.getEntrenadorMod().getNombre());
            textApellidos.setText(PersistentData.getEntrenadorMod().getApellidos());
            textDni.setText(PersistentData.getEntrenadorMod().getDni());
            textIBAN.setText(PersistentData.getEntrenadorMod().getCuentaBancaria());
            textSalario.setText(PersistentData.getEntrenadorMod().getSalario() + "");
            textTelefono.setText(PersistentData.getEntrenadorMod().getTelefono());
            textCategoria.setText(PersistentData.getEntrenadorMod().getCategoria());
            try {
                dateNacimiento.setValue(DateFormat.toLocalDate(PersistentData.getAdminMod().getFechaNacimientoaDate()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
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
