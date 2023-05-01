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
    @FXML
    private TextField textEmail;
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
            textEmail.setText(PersistentData.getEntrenadorMod().getEmail());
            try {
                dateNacimiento.setValue(DateFormat.toLocalDate(PersistentData.getEntrenadorMod().getFechaNacimientoDate()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }else{
            btnNext_mod.setVisible(false);
        }
    }

    public void actionNext_mod(ActionEvent actionEvent) throws SQLException, ParseException {
        if(!validarCampos().equals("OK")){
            textErr.setText(validarCampos());//POR HACER
        }else{

            PersistentData.getEntrenadorMod().setNombre(textNombre.getText().trim());
            PersistentData.getEntrenadorMod().setApellidos(textApellidos.getText().trim());
            PersistentData.getEntrenadorMod().setDni(textDni.getText().trim());
            PersistentData.getEntrenadorMod().setCuentaBancaria(textIBAN.getText().trim());
            PersistentData.getEntrenadorMod().setSalario(Double.parseDouble(textSalario.getText().trim()));
            PersistentData.getEntrenadorMod().setCuentaBancaria(textIBAN.getText().trim());
            PersistentData.getEntrenadorMod().setTelefono(textTelefono.getText().trim());
            PersistentData.getEntrenadorMod().setCategoria(textCategoria.getText().trim());
            PersistentData.getEntrenadorMod().setEmail(textEmail.getText().trim());

            EntrenadoresController.updateEntrenadores(PersistentData.getEntrenadorMod());
            cerrarVentana(actionEvent);
        }
    }

    public void actionAdd_del(ActionEvent actionEvent) throws SQLException, ParseException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if (isMod){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Confirmación de borrado");
            alert.setContentText("¿Estás seguro de querer borrar a " + PersistentData.getEscuelaMod().getNombre() + "?");
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK){
                EntrenadoresController.deleteEntrenadores(PersistentData.getEntrenadorMod());
                cerrarVentana(actionEvent);
            }
        }else{
            if(!validarCampos().equals("OK"))
                textErr.setText(validarCampos());
            else{
                PersistentData.setEntrenadorMod(new Entrenadores(0, textNombre.getText().trim(), textApellidos.getText().trim(), textTelefono.getText().trim(),Double.parseDouble(textSalario.getText().trim()),
                        new Date(),null,DateFormat.toDate(dateNacimiento.getValue()),textCategoria.getText().trim(),textDni.getText().trim(),
                        1,textIBAN.getText().trim(), textEmail.getText().trim()));

                EntrenadoresController.addEntrenadores(PersistentData.getEntrenadorMod());

                textNombre.setText("");
                textApellidos.setText("");
                textTelefono.setText("");
                textSalario.setText("");
                textCategoria.setText("");
                textIBAN.setText("");
                textDni.setText("");
                textEmail.setText("");
                dateNacimiento.setValue(null);
            }
        }
    }


    public void actionCancel(ActionEvent actionEvent) {
        cerrarVentana(actionEvent);
    }

    private String validarCampos() {
        return "OK";
    }
}
