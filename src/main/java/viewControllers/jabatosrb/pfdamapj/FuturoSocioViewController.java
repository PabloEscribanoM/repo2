package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.DateFormat;
import jabatosrb.pfdampj.PersistentData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class FuturoSocioViewController extends ViewUtilities {
    @FXML
    private DatePicker dateNacimiento;
    @FXML
    private Label textErr;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textApellidos;
    @FXML
    private TextField textEmail;
    @FXML
    private TextField textTelefono;
    @FXML
    private TextField textIBAN;

    public void actionSend(ActionEvent actionEvent) throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, ParseException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if(!validarCampos().equals("OK")){
            textErr.setText(validarCampos());
        }else{
            PersistentData.setFuturoSocioMod(new FuturoSocio(0, textNombre.getText().trim(), textApellidos.getText().trim(),
                    textTelefono.getText().trim(), textEmail.getText().trim(), DateFormat.toDate(dateNacimiento.getValue()), textIBAN.getText().trim(), 1));
            FuturoSocioController.addFuturoSocio(PersistentData.getFuturoSocioMod());
            cerrarVentana(actionEvent);
        }
    }

    public void actionCancel(ActionEvent actionEvent) {
        cerrarVentana(actionEvent);
    }

    private String validarCampos(){
        if(textNombre.getText().trim().equals("")){
            textNombre.requestFocus();
            return "Nombre no puede ser vacio";
        }
        if(textApellidos.getText().trim().equals("")){
            textApellidos.requestFocus();
            return "Apellidos no puede ser vacio";
        }
        if(!textEmail.getText().trim().matches("[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}")){
            textEmail.requestFocus();
            return "Email no válido";
        }
        if(!textTelefono.getText().trim().matches("[0-9]{9}")){
            textTelefono.requestFocus();
            return "Teléfono no válido";
        }
        if(!textIBAN.getText().trim().matches("[a-zA-Z]{2}[0-9]{22}")){
            textIBAN.requestFocus();
            return "Cuenta bancaria no válida";
        }
        if(DateFormat.anyos(DateFormat.toDate(dateNacimiento.getValue()))<18){
            dateNacimiento.requestFocus();
            return "Los socios tienen que ser mayores de edad";
        }
        return "OK";
    }
}
