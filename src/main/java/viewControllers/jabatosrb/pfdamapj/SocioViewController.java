package jabatosrb.pfdamapj;

import jabatosrb.pfdampj.DateFormat;
import jabatosrb.pfdampj.PassGenerator;
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

public class SocioViewController extends ViewUtilities implements Initializable {

    @FXML
    private Button btnNext_mod, btnAdd_del;
    @FXML
    private TextField textNombre, textApellidos, textMote, textEmail, textTelefono, textIBAN, textAporte, textAdeudo;
    @FXML
    private DatePicker dateNacimiento;
    @FXML
    private Label textErr;
    private boolean isMod=false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(PersistentData.getSocioMod()!=null){
            isMod=true;
            btnNext_mod.setText("Modificar");
            btnAdd_del.setText("Borrar");
            textNombre.setText(PersistentData.getSocioMod().getSocNombre());
            textApellidos.setText(PersistentData.getSocioMod().getSocApellido());
            textMote.setText(PersistentData.getSocioMod().getSocMote());
            textEmail.setText(PersistentData.getSocioMod().getSocEmail());
            textTelefono.setText(PersistentData.getSocioMod().getSocNumTel());
            textIBAN.setText(PersistentData.getSocioMod().getSocCuentaBancaria());
            textAporte.setText(PersistentData.getSocioMod().getSocAporte() + "");
            textAdeudo.setText(PersistentData.getSocioMod().getSocAdeudo() + "");
            try {
                dateNacimiento.setValue(DateFormat.toLocalDate(PersistentData.getSocioMod().getSocFechaNacimientoDate()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void actionNext_mod(ActionEvent actionEvent) throws SQLException, ParseException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if (isMod){
            if (!validarCampos().equals("OK"))
                textErr.setText(validarCampos());
            else{
                PersistentData.getSocioMod().setSocNombre(textNombre.getText().trim());
                PersistentData.getSocioMod().setSocApellido(textApellidos.getText().trim());
                PersistentData.getSocioMod().setSocMote(textMote.getText().trim());
                PersistentData.getSocioMod().setSocEmail(textEmail.getText().trim());
                PersistentData.getSocioMod().setSocNumTel(textTelefono.getText().trim());
                PersistentData.getSocioMod().setSocCuentaBancaria(textIBAN.getText().trim());
                PersistentData.getSocioMod().setSocAporte(Double.parseDouble(textAporte.getText().trim()));
                PersistentData.getSocioMod().setSocAdeudo(Double.parseDouble(textAdeudo.getText().trim()));
                PersistentData.getSocioMod().setSocFechaNacimiento(DateFormat.toDate(dateNacimiento.getValue()));
                SociosController.updateSocio(PersistentData.getSocioMod());
                cerrarVentana(actionEvent);
            }
        }else{
            System.out.println("hola");
            if (PersistentData.getFuturoSocioMod() != null){
                FuturoSocioController.deleteFuturoSocio(PersistentData.getFuturoSocioMod());
                FuturoSocioController.addFuturoSocio(PersistentData.getFuturoSocioMod());
                PersistentData.setFuturoSocioMod(null);
            }
            PersistentData.setFuturoSocioMod(FuturoSocioController.getNext());
            if(PersistentData.getFuturoSocioMod() == null)
                textErr.setText("No quedan mas socios que registrar");
            else{
                textNombre.setText(PersistentData.getFuturoSocioMod().getNombre());
                textApellidos.setText(PersistentData.getFuturoSocioMod().getApellidos());
                textEmail.setText(PersistentData.getFuturoSocioMod().getEmail());
                textTelefono.setText(PersistentData.getFuturoSocioMod().getTelefono());
                textIBAN.setText(PersistentData.getFuturoSocioMod().getCuentaBancaria());
                textAporte.setText("0");
                textAdeudo.setText("0");
                dateNacimiento.setValue(DateFormat.toLocalDate(PersistentData.getFuturoSocioMod().getFechaNacimientoaDate()));
            }
        }
    }

    public void actionAdd_del(ActionEvent actionEvent) throws SQLException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, ParseException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if (isMod){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Confirmación de borrado");
            alert.setContentText("¿Estás seguro de querer borrar a " + PersistentData.getSocioMod().getSocMote() + "?");
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK){
                SociosController.deleteSocio(PersistentData.getSocioMod());
                cerrarVentana(actionEvent);
            }
        }else{
            if(!validarCampos().equals("OK"))
                textErr.setText(validarCampos());
            else{
                PersistentData.setSocioMod(new Socio(0, textNombre.getText().trim(), textApellidos.getText().trim(),
                        textMote.getText().trim(), textIBAN.getText().trim(), textEmail.getText().trim(),
                        PassGenerator.generatePass(12, PassGenerator.NUMBERS_LOWER_UPPER), textTelefono.getText().trim(),
                        Double.parseDouble(textAporte.getText().trim()), Double.parseDouble(textAdeudo.getText().trim()),
                        DateFormat.toDate(dateNacimiento.getValue()), new Date(), 1));

                SociosController.addSocio(PersistentData.getSocioMod());

                textNombre.setText("");
                textApellidos.setText("");
                textMote.setText("");
                textEmail.setText("");
                textTelefono.setText("");
                textIBAN.setText("");
                textAporte.setText("");
                textAdeudo.setText("");
                dateNacimiento.setValue(null);

                if(PersistentData.getFuturoSocioMod()!= null){
                    FuturoSocioController.deleteFuturoSocio(PersistentData.getFuturoSocioMod());
                    PersistentData.setFuturoSocioMod(null);
                }
            }
        }
    }

    public void actionCancel(ActionEvent actionEvent) {
        cerrarVentana(actionEvent);
    }

    private String validarCampos() {
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
        if(textIBAN.getText().trim().matches("[a-zA-Z]{2}\\d{22}")){
            textIBAN.requestFocus();
            return "Cuenta bancaria no válida";
        }
        if(!textAporte.getText().trim().matches("[0-9]+\\.?[0-9]*")){
            textAporte.requestFocus();
            return "El aporte tiene que ser numérico";
        }
        if(!textAdeudo.getText().trim().matches("[0-9]+\\.?[0-9]*")){
            textAdeudo.requestFocus();
            return "El aporte tiene que ser numérico";
        }
        if(DateFormat.anyos(DateFormat.toDate(dateNacimiento.getValue()))<18){
            dateNacimiento.requestFocus();
            return "Los socios tienen que ser mayores de edad";
        }
        return "OK";
    }

}
