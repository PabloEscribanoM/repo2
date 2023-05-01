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

public class AdminViewController extends ViewUtilities implements Initializable {

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
    private TextField textEmail;
    @FXML
    private TextField textDni;
    @FXML
    private TextField textIBAN;
    @FXML
    private TextField textSalario;
    @FXML
    private TextField textArea;
    @FXML
    private DatePicker dateNacimiento;
    private boolean isMod=false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(PersistentData.getAdminMod()!=null){
            isMod=true;
            btnNext_mod.setText("Modificar");
            btnAdd_del.setText("Borrar");
            textNombre.setText(PersistentData.getAdminMod().getNombre());
            textApellidos.setText(PersistentData.getAdminMod().getApellidos());
            textDni.setText(PersistentData.getAdminMod().getDni());
            textIBAN.setText(PersistentData.getAdminMod().getCuentaBancaria());
            textSalario.setText(PersistentData.getAdminMod().getSalario() + "");
            textArea.setText(PersistentData.getAdminMod().getArea());
            textEmail.setText(PersistentData.getAdminMod().getEmail());
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
