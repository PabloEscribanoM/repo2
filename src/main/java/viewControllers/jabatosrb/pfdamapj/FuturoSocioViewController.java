package jabatosrb.pfdamapj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FuturoSocioViewController extends ViewUtilities {
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

    public void actionSend(ActionEvent actionEvent) {
        if(!validarCampos().equals("OK")){
            textErr.setText(validarCampos());
        }else{

        }
    }

    public void actionCancel(ActionEvent actionEvent) {
    }

    private String validarCampos(){
        return "OK";
    }
}
