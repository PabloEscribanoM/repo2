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
        if(!validarCampos().equals("OK")){
            textErr.setText(validarCampos());//POR HACER
        }else{
            PersistentData.getAdminMod().setNombre(textNombre.getText().trim());
            PersistentData.getAdminMod().setApellidos(textApellidos.getText().trim());
            PersistentData.getAdminMod().setDni(textDni.getText().trim());
            PersistentData.getAdminMod().setCuentaBancaria(textIBAN.getText().trim());
            PersistentData.getAdminMod().setSalario(Double.parseDouble(textSalario.getText().trim()));
            PersistentData.getAdminMod().setArea(textArea.getText().trim());
            PersistentData.getAdminMod().setEmail(textEmail.getText().trim());
        }
    }

    public void actionAdd_del(ActionEvent actionEvent) throws SQLException, ParseException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if (isMod){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Confirmación de borrado");
            alert.setContentText("¿Estás seguro de querer borrar a " + PersistentData.getAdminMod().getNombre() + "?");
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK){
                AdministradorController.deleteAdministrador(PersistentData.getAdminMod());
                cerrarVentana(actionEvent);
            }
        }else{
            if(!validarCampos().equals("OK"))
                textErr.setText(validarCampos());
            else{
                PersistentData.setAdminMod(new Administrador(0, textNombre.getText().trim(), textApellidos.getText().trim(), textEmail.getText().trim(),
                        null, textArea.getText().trim(), textDni.getText().trim(), new Date(),null, DateFormat.toDate(dateNacimiento.getValue()),
                         Double.parseDouble(textSalario.getText().trim()),  textIBAN.getText().trim(),1));

                AdministradorController.addAdministrador(PersistentData.getAdminMod());

                textNombre.setText("");
                textApellidos.setText("");
                textEmail.setText("");
                textDni.setText("");
                textArea.setText("");
                dateNacimiento.setValue(null);
                textIBAN.setText("");
                textSalario.setText("");

            }
        }
    }

    public void actionCancel(ActionEvent actionEvent) {cerrarVentana(actionEvent);}

    private String validarCampos() {
        return "OK";
    }
}
