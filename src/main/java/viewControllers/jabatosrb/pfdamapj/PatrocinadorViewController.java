package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.DateFormat;
import jabatosrb.pfdampj.PersistentData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
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
    private boolean isMod=false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (PersistentData.getPatrocinadorMod()!=null){
            isMod=true;
            btnNext_mod.setText("Modificar");
            btnAdd_del.setText("Borrar");
            textNombre.setText(PersistentData.getPatrocinadorMod().getNombre());
            textDesc.setText(PersistentData.getPatrocinadorMod().getDescripcion());
            textAporte.setText(PersistentData.getPatrocinadorMod().getAporte() + "");
            textIBAN.setText(PersistentData.getPatrocinadorMod().getCuentaBancaria());
        }else{
            btnNext_mod.setVisible(false);
        }
    }

    public void actionNext_mod(ActionEvent actionEvent) throws SQLException, ParseException {
        if(!validarCampos().equals("OK")){
            textErr.setText(validarCampos());
        }else{
            PersistentData.getPatrocinadorMod().setNombre(textNombre.getText().trim());
            PersistentData.getPatrocinadorMod().setDescripcion(textDesc.getText().trim());
            PersistentData.getPatrocinadorMod().setAporte(Double.parseDouble(textAporte.getText().trim()));
            PersistentData.getPatrocinadorMod().setCuentaBancaria(textIBAN.getText().trim());

            PatrocinadorController.updatePatrocinador(PersistentData.getPatrocinadorMod());

            cerrarVentana(actionEvent);
        }
    }

    public void actionAdd_del(ActionEvent actionEvent) throws SQLException, ParseException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if (isMod){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Confirmación de borrado");
            alert.setContentText("¿Estás seguro de querer borrar a " + PersistentData.getPatrocinadorMod().getNombre() + "?");
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK){
                PatrocinadorController.deletePatrocinador(PersistentData.getPatrocinadorMod());
                cerrarVentana(actionEvent);
            }
        }else{
            if(!validarCampos().equals("OK"))
                textErr.setText(validarCampos());
            else{
                PersistentData.setPatrocinadorMod(new Patrocinador(0, textNombre.getText().trim(), textDesc.getText().trim(), Double.parseDouble(textAporte.getText().trim()),
                        textIBAN.getText().trim(),1));

                PatrocinadorController.addPatrocinador(PersistentData.getPatrocinadorMod());

                textNombre.setText("");
                textAporte.setText("");
                textIBAN.setText("");
                textDesc.setText("");
                textErr.setText("");

            }
        }
    }

    public void actionCancel(ActionEvent actionEvent) {cerrarVentana(actionEvent);}

    private String validarCampos() {
        if(textNombre.getText().trim().equals("")){
            textNombre.requestFocus();
            return "Nombre no puede ser vacio";
        }
        if(!textAporte.getText().trim().matches("[0-9]+\\.?[0-9]*")){
            textAporte.requestFocus();
            return "El aporte tiene que ser numérico";
        }
        if(!textIBAN.getText().trim().matches("[a-zA-Z]{2}[0-9]{22}")){
            textIBAN.requestFocus();
            return "Cuenta bancaria no válida";
        }
        if(textDesc.getText().trim().equals("")){
            textNombre.requestFocus();
            return "La descripción no puede ser vacia";
        }
        return "OK";
    }
}
